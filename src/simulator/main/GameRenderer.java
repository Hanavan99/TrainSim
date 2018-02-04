package simulator.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.glutilities.buffer.VBO;
import com.glutilities.model.AttributeArray;
import com.glutilities.model.Model;
import com.glutilities.model.ModelLoader;
import com.glutilities.shader.ShaderProgram;
import com.glutilities.shader.ShaderUtils;
import com.glutilities.ui.MasterRenderer;
import com.glutilities.ui.RenderContext;
import com.glutilities.util.DrawMode;
import com.glutilities.util.OpenGL;
import com.glutilities.util.Vertex3f;
import com.glutilities.util.matrix.Matrix4f;
import com.glutilities.util.matrix.MatrixMath;
import com.glutilities.util.matrix.TransformMatrix;

import simulator.model.Track;
import simulator.model.track.Straight1x;
import valve.res.ValveModelLoader;

public class GameRenderer extends MasterRenderer {

	private Track testTrack = new Straight1x();
	private Model train;
	private List<Model> tracks = new ArrayList<Model>();
	private VBO testVBO;
	private ShaderProgram program;
	private TransformMatrix transform;
	private TransformMatrix view;
	private Matrix4f projection;

	@Override
	public void init(RenderContext context) {
		program = ShaderUtils.loadShaderProgram(new File("res/shaders"), "sample");
		System.out.println("initializing");
		List<Float> verts = new ArrayList<Float>();
		for (float a = 0; a < 10; a += 0.5f) {
			Vertex3f v = testTrack.getObjectPosition(a);
			verts.add(v.getX());
			verts.add(v.getY());
			verts.add(v.getZ());
			System.out.println(v.getX());
		}

		AttributeArray vertices = new AttributeArray(0, verts.toArray(new Float[0]), 3);
		testVBO = new VBO(new AttributeArray[] { vertices }, 10, DrawMode.LINES.getValue());
		testVBO.create();

		projection = MatrixMath.createPerspectiveMatrix(90, context.getAspect(), 0.1f, 300f);
		program.setMatrix4f("projectionMatrix", projection);

		transform = new TransformMatrix(program, "transformMatrix");
		view = new TransformMatrix(program, "viewMatrix");

		ModelLoader loader = new ModelLoader();
		// train = loader.load(new File("res/models/locomotive.obj"), "train");
		ValveModelLoader loader2 = new ValveModelLoader();
		try {
			for (File f : new File("res/models/rails/models/shinji85/train/").listFiles()) {
				if (f.getName().contains(".mdl")) {
					System.out.println(f);
					tracks.add(loader2.load(f, f.getName()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(RenderContext context) {
		OpenGL.setViewport(context.getWidth(), context.getHeight());
		OpenGL.clearBuffer();
		program.enable();

		program.setInt("renderMode", 3);
		program.setMatrix4f("projectionMatrix", projection);

		transform.reset();
		view.reset();
		float time = System.nanoTime() / 100000000f;
		view.setTranslation(-time % 450f, 0, -20);
		view.setRotation(-1.1f, 0, 0);

		testVBO.draw();
		for (Model track : tracks) {
			track.draw(program);
			transform.translate(15, 0, 0);
		}

		// train.draw(program);

		program.disable();
	}

	@Override
	public void update(RenderContext context) {
		// TODO Auto-generated method stub
		super.update(context);
	}

	@Override
	public void exit(RenderContext context) {
		testVBO.delete();
		program.delete();
	}

	@Override
	public void keyPressed(RenderContext context, int key, int action) {

	}

	@Override
	public void mouseClicked(RenderContext context, int button, int action) {

	}

	@Override
	public void mouseMoved(RenderContext context, int xpos, int ypos) {

	}

	@Override
	public void textTyped(RenderContext context, int codepoint) {

	}

}
