package valve.res;

public class VTXModelHeader {

	private int numLODs;
	private int lodOffset;

	public VTXModelHeader(int numLODs, int lodOffset) {
		this.numLODs = numLODs;
		this.lodOffset = lodOffset;
	}

	public int getNumLODs() {
		return numLODs;
	}

	public int getLodOffset() {
		return lodOffset;
	}

}
