package simulator.model.track;

import com.glutilities.util.Vertex3f;

import simulator.model.Node;
import simulator.model.Track;

public class Straight1x extends Track {

	public Straight1x() {
		super("10\" Straight", new Node(0, 0, 0, 0, 0, 0), new Node(10, 0, 0, 0, 0, 0));
	}

	@Override
	protected Vertex3f calculateObjectPosition(float a) {
		return new Vertex3f(a, 0, 0);
	}

	@Override
	protected Vertex3f calculateObjectRotation(float a) {
		return new Vertex3f(0);
	}

}
