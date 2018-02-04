package simulator.model;

import com.glutilities.util.Vertex3f;

public class Node {

	private int id;
	private Vertex3f position;
	private Vertex3f rotation;

	public Node(Vertex3f position, Vertex3f rotation) {
		this.id = -1;
		this.position = position;
		this.rotation = rotation;
	}

	public Node(float px, float py, float pz, float rx, float ry, float rz) {
		this(new Vertex3f(px, py, pz), new Vertex3f(rx, ry, rz));
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the position
	 */
	public Vertex3f getPosition() {
		return position;
	}

	/**
	 * @return the rotation
	 */
	public Vertex3f getRotation() {
		return rotation;
	}

}