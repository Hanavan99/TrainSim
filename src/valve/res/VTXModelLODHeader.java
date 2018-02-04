package valve.res;

public class VTXModelLODHeader {

	private int numMeshes;
	private int meshOffset;
	private float switchPoint;

	public VTXModelLODHeader(int numMeshes, int meshOffset, float switchPoint) {
		this.numMeshes = numMeshes;
		this.meshOffset = meshOffset;
		this.switchPoint = switchPoint;
	}

	public int getNumMeshes() {
		return numMeshes;
	}

	public int getMeshOffset() {
		return meshOffset;
	}

	public float getSwitchPoint() {
		return switchPoint;
	}

}
