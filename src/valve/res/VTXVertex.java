package valve.res;

public class VTXVertex {

	private byte[] boneWeightIndex; // length 3
	private byte numBones;
	private char origMeshVertID;
	private byte[] boneID; // length 3

	public VTXVertex(byte[] boneWeightIndex, byte numBones, char origMeshVertID, byte[] boneID) {
		this.boneWeightIndex = boneWeightIndex;
		this.numBones = numBones;
		this.origMeshVertID = origMeshVertID;
		this.boneID = boneID;
	}

	public byte[] getBoneWeightIndex() {
		return boneWeightIndex;
	}

	public byte getNumBones() {
		return numBones;
	}

	public char getOrigMeshVertID() {
		return origMeshVertID;
	}

	public byte[] getBoneID() {
		return boneID;
	}

}
