package valve.res;

public class VTXBodyPartHeader {

	private int numModels;
	private int modelOffset;

	public VTXBodyPartHeader(int numModels, int modelOffset) {
		this.numModels = numModels;
		this.modelOffset = modelOffset;
	}

	public int getNumModels() {
		return numModels;
	}

	public int getModelOffset() {
		return modelOffset;
	}

}
