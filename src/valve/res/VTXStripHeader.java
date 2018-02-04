package valve.res;

public class VTXStripHeader {

	private int numIndices;
	private int indexOffset;
	private int numVerts;
	private int vertOffset;
	private char numBones;
	private byte flags;
	private int numBoneStateChanges;
	private int boneStateChangeOffset;

	public VTXStripHeader(int numIndices, int indexOffset, int numVerts, int vertOffset, char numBones, byte flags, int numBoneStateChanges, int boneStateChangeOffset) {
		this.numIndices = numIndices;
		this.indexOffset = indexOffset;
		this.numVerts = numVerts;
		this.vertOffset = vertOffset;
		this.numBones = numBones;
		this.flags = flags;
		this.numBoneStateChanges = numBoneStateChanges;
		this.boneStateChangeOffset = boneStateChangeOffset;
	}

	public int getNumIndices() {
		return numIndices;
	}

	public int getIndexOffset() {
		return indexOffset;
	}

	public int getNumVerts() {
		return numVerts;
	}

	public int getVertOffset() {
		return vertOffset;
	}

	public char getNumBones() {
		return numBones;
	}

	public byte getFlags() {
		return flags;
	}

	public int getNumBoneStateChanges() {
		return numBoneStateChanges;
	}

	public int getBoneStateChangeOffset() {
		return boneStateChangeOffset;
	}

}
