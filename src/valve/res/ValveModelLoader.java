package valve.res;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.glutilities.buffer.VBO;
import com.glutilities.model.AttributeArray;
import com.glutilities.model.Model;
import com.glutilities.model.ModelGroup;
import com.glutilities.resource.ResourceLoader;
import com.glutilities.util.DrawMode;
import com.glutilities.util.Vertex2f;
import com.glutilities.util.Vertex3f;

public class ValveModelLoader extends ResourceLoader<Model, String, File> {

	private static final int MAX_NUM_LODS = 8;

	@Override
	public Model load(File src, String key) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * To read a .vvd file, each file contains a header, followed by a fixup
		 * table for each LOD. Each fixup table is followed by a set of vertices
		 * which contains data about each vertex, normal, tex coord, and
		 * tangent.
		 * 
		 * Valve Links: https://developer.valvesoftware.com/wiki/VVD
		 * https://developer.valvesoftware.com/wiki/VTX
		 * https://developer.valvesoftware.com/wiki/PHY
		 * https://developer.valvesoftware.com/wiki/MDL
		 */
		String rootname = src.getName().substring(0, src.getName().length() - 4);

		// read VVD file
		File vvd = new File(src.getParent(), rootname + ".vvd");
		ByteBuffer vvdbuff = getBufferFromFile(vvd);
		VVDVertexHeaderTable vvdheader = readVertexHeader(vvdbuff);
		System.out.println("Fixups: " + vvdheader.getNumFixups());
		System.out.println("LODs: " + vvdheader.getNumLODs());
		vvdbuff.position(vvdheader.getFixupTableStart());
		VVDFixupTable[] vvdtable = new VVDFixupTable[vvdheader.getNumFixups()];
		for (int i = 0; i < vvdheader.getNumFixups(); i++) {
			vvdtable[i] = readFileFixup(vvdbuff);
		}
		vvdbuff.position(vvdheader.getVertexDataStart());
		List<Float> vertfloats = new ArrayList<Float>();
		List<Vertex> verts = new ArrayList<Vertex>();
		for (int i = 0; i < vvdtable.length; i++) {
			System.out.println("Reading " + vvdtable[i].getNumVertexes() + " vertexes.");
			for (int j = 0; j < vvdtable[i].getNumVertexes(); j++) {
				Vertex v = readVertexData(vvdbuff);
				verts.add(v);
				Vertex3f pos = v.getPosition();
				vertfloats.add(pos.getX() / 10);
				vertfloats.add(pos.getY() / 10);
				vertfloats.add(pos.getZ() / 10);
			}
		}

		// read vtx file
		// XXX INDICES COME FROM .VTX FILE
		File vtx = new File(src.getParent(), rootname + ".sw.vtx");
		ByteBuffer vtxbuff = getBufferFromFile(vtx);
		VTXFileHeaderTable vtxheader = readVTXFileHeader(vtxbuff);
		vtxbuff.position(vtxheader.getBodyPartOffset());
		System.out.println("Body Part Offset: " + vtxheader.getBodyPartOffset());
		VTXBodyPartHeader[] bodyparts = new VTXBodyPartHeader[vtxheader.getNumBodyParts()];
		for (int i = 0; i < bodyparts.length; i++) {
			bodyparts[i] = readVTXBodyPartHeader(vtxbuff);
		}
		for (int i = 0; i < bodyparts.length; i++) {
			VTXModelHeader[] models = new VTXModelHeader[bodyparts[i].getNumModels()];
			vtxbuff.position(bodyparts[i].getModelOffset());
			for (int j = 0; j < models.length; j++) {
				models[i] = readVTXModelHeader(vtxbuff);
			}
			for (int j = 0; j < models.length; j++) {
				VTXModelLODHeader[] lods = new VTXModelLODHeader[models[j].getNumLODs()];
				vtxbuff.position(models[j].getLodOffset());
				for (int k = 0; k < lods.length; k++) {
					lods[k] = readVTXModelLODHeader(vtxbuff);
				}
				for (int k = 0; k < lods.length; k++) {
					System.out.println("Meshes: " + lods[k].getNumMeshes() + ", Offset: " + lods[k].getMeshOffset());
					VTXMeshHeader[] meshes = new VTXMeshHeader[lods[k].getNumMeshes()];
				}
			}
		}

		// build model
		AttributeArray vertarray = new AttributeArray(0, vertfloats.toArray(new Float[0]), 3);
		VBO vbo = new VBO(new AttributeArray[] { vertarray }, vertfloats.size() / 3, DrawMode.POINTS.getValue());
		vbo.create();
		ModelGroup mg = new ModelGroup("default", null, vbo);
		Model m = new Model(key, new ModelGroup[] { mg });
		return m;
	}

	@Override
	public boolean keyReferencesResource(Model resource, String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getKeyFromResource(Model resource) {
		// TODO Auto-generated method stub
		return null;
	}

	private ByteBuffer getBufferFromFile(File f) throws IOException {
		System.out.println("Reading file " + f);
		InputStream stream = new FileInputStream(f);
		byte[] data = new byte[(int) f.length()];
		stream.read(data);
		stream.close();

		ByteBuffer buff = ByteBuffer.wrap(data);
		buff = buff.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println("Byte array size: " + data.length);
		System.out.println("Buffer size: " + buff.capacity());
		return buff;
	}

	private VVDVertexHeaderTable readVertexHeader(ByteBuffer buff) {
		int id = buff.getInt();
		int version = buff.getInt();
		long checksum = buff.getInt();
		int numLODs = buff.getInt();
		int[] numLODVertexes = new int[MAX_NUM_LODS];
		for (int i = 0; i < numLODVertexes.length; i++) {
			numLODVertexes[i] = buff.getInt();
		}
		int numFixups = buff.getInt();
		int fixupTableStart = buff.getInt();
		int vertexDataStart = buff.getInt();
		int tangentDataStart = buff.getInt();
		return new VVDVertexHeaderTable(id, version, checksum, numLODs, numLODVertexes, numFixups, fixupTableStart, vertexDataStart, tangentDataStart);
	}

	private VVDFixupTable readFileFixup(ByteBuffer buff) {
		return new VVDFixupTable(buff.getInt(), buff.getInt(), buff.getInt());
	}

	private VTXFileHeaderTable readVTXFileHeader(ByteBuffer buff) {
		return new VTXFileHeaderTable(buff.getInt(), buff.getInt(), buff.getChar(), buff.getChar(), buff.getInt(), buff.getInt(), buff.getInt(), buff.getInt(), buff.getInt(), buff.getInt());
	}

	private VTXBodyPartHeader readVTXBodyPartHeader(ByteBuffer buff) {
		return new VTXBodyPartHeader(buff.getInt(), buff.getInt());
	}

	private VTXModelHeader readVTXModelHeader(ByteBuffer buff) {
		return new VTXModelHeader(buff.getInt(), buff.getInt());
	}

	private VTXModelLODHeader readVTXModelLODHeader(ByteBuffer buff) {
		return new VTXModelLODHeader(buff.getInt(), buff.getInt(), buff.getFloat());
	}

	private Vertex readVertexData(ByteBuffer buff) {
		float[] weights = new float[Vertex.MAX_NUM_BONES_PER_VERT];
		char[] bones = new char[Vertex.MAX_NUM_BONES_PER_VERT];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = buff.getFloat();
		}
		for (int i = 0; i < bones.length; i++) {
			bones[i] = (char) buff.get();
		}
		byte numbones = buff.get();
		Vertex3f position = readVertex3f(buff);
		Vertex3f normal = readVertex3f(buff);
		Vertex2f tex = readVertex2f(buff);
		return new Vertex(weights, bones, numbones, position, normal, tex);
	}

	private Vertex2f readVertex2f(ByteBuffer buff) {
		return new Vertex2f(buff.getFloat(), buff.getFloat());
	}

	private Vertex3f readVertex3f(ByteBuffer buff) {
		return new Vertex3f(buff.getFloat(), buff.getFloat(), buff.getFloat());
	}

}
