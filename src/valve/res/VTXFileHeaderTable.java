package valve.res;

public class VTXFileHeaderTable {

	private int version;
	private int vertCacheSize;
	private char maxBonesPerStrip;
	private char maxBonesPerTri;
	private int maxBonesPerVert;
	private int checksum;
	private int numLODs;
	private int materialReplacementListOffset;
	private int numBodyParts;
	private int bodyPartOffset;

	public VTXFileHeaderTable(int version, int vertCacheSize, char maxBonesPerStrip, char maxBonesPerTri, int maxBonesPerVert, int checksum, int numLODs, int materialReplacementListOffset, int numBodyParts, int bodyPartOffset) {
		this.version = version;
		this.vertCacheSize = vertCacheSize;
		this.maxBonesPerStrip = maxBonesPerStrip;
		this.maxBonesPerTri = maxBonesPerTri;
		this.maxBonesPerVert = maxBonesPerVert;
		this.checksum = checksum;
		this.numLODs = numLODs;
		this.materialReplacementListOffset = materialReplacementListOffset;
		this.numBodyParts = numBodyParts;
		this.bodyPartOffset = bodyPartOffset;
	}

	public int getVersion() {
		return version;
	}

	public int getVertCacheSize() {
		return vertCacheSize;
	}

	public char getMaxBonesPerStrip() {
		return maxBonesPerStrip;
	}

	public char getMaxBonesPerTri() {
		return maxBonesPerTri;
	}

	public int getMaxBonesPerVert() {
		return maxBonesPerVert;
	}

	public int getChecksum() {
		return checksum;
	}

	public int getNumLODs() {
		return numLODs;
	}

	public int getMaterialReplacementListOffset() {
		return materialReplacementListOffset;
	}

	public int getNumBodyParts() {
		return numBodyParts;
	}

	public int getBodyPartOffset() {
		return bodyPartOffset;
	}

}
