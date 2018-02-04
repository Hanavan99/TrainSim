package valve.res;

import com.glutilities.util.Vertex2f;
import com.glutilities.util.Vertex3f;

public class Vertex {

	public static final int MAX_NUM_BONES_PER_VERT = 3;

	private float[] weights;
	private char[] bones;
	private byte numbones;

	private Vertex3f position;
	private Vertex3f normal;
	private Vertex2f texCoord;

	public Vertex(float[] weights, char[] bones, byte numbones, Vertex3f position, Vertex3f normal, Vertex2f texCoord) {
		this.weights = weights;
		this.bones = bones;
		this.numbones = numbones;
		this.position = position;
		this.normal = normal;
		this.texCoord = texCoord;
	}

	/**
	 * @return the weight
	 */
	public float[] getWeights() {
		return weights;
	}

	/**
	 * @return the bone
	 */
	public char[] getBones() {
		return bones;
	}

	/**
	 * @return the numbones
	 */
	public byte getNumBones() {
		return numbones;
	}

	/**
	 * @return the position
	 */
	public Vertex3f getPosition() {
		return position;
	}

	/**
	 * @return the normal
	 */
	public Vertex3f getNormal() {
		return normal;
	}

	/**
	 * @return the texCoord
	 */
	public Vertex2f getTexCoord() {
		return texCoord;
	}

}
