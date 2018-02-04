package simulator.model;

import com.glutilities.util.Vertex3f;
import com.glutilities.util.Vertex4f;
import com.glutilities.util.VertexUtils;
import com.glutilities.util.matrix.MatrixMath;
import com.glutilities.util.matrix.TransformMatrix;

public abstract class Track {

	private String name;
	private Node[] nodes;
	private TransformMatrix transform = new TransformMatrix();

	protected Track(String name, Node... nodes) {
		this.nodes = nodes;
	}

	public String getName() {
		return name;
	}

	public Vertex3f getTrackPosition() {
		return nodes[0].getPosition();
	}

	public Vertex3f getTrackRotation() {
		return nodes[0].getRotation();
	}

	public Vertex3f getObjectPosition(float a) {
		Vertex3f position = VertexUtils.dupe(getNodes()[0].getPosition());
		transform.setTranslation(position.getX(), position.getY(), position.getZ());
		return MatrixMath.multiply(new Vertex4f(position.getX(), position.getY(), position.getZ(), 0), transform.getMatrix());
	}

	public Vertex3f getObjectRotation(float a) {
		Vertex3f position = VertexUtils.dupe(getNodes()[0].getPosition());
		transform.setRotation(position.getX(), position.getY(), position.getZ());
		return MatrixMath.multiply((Vertex4f) position, transform.getMatrix());
	}

	public Node[] getNodes() {
		return nodes;
	}

	public void updateNodes() {
		for (int i = 1; i < nodes.length; i++) {
			Vertex3f offsetPosition = VertexUtils.dupe(nodes[0].getPosition());
			offsetPosition.add(nodes[i].getPosition());
			Vertex3f offsetRotation = VertexUtils.dupe(nodes[0].getRotation());
			offsetRotation.add(nodes[i].getRotation());
			nodes[i] = new Node(offsetPosition, offsetRotation);
		}
	}

	protected abstract Vertex3f calculateObjectPosition(float a);

	protected abstract Vertex3f calculateObjectRotation(float a);

}
