package valve.res;

public class VTXStripGroupHeader {

	private int numVerts;
	private int vertOffset;
	private int numIndices;
	private int indexOffset;
	private int numStrips;
	private int stripOffset;
	private byte flags;

	public VTXStripGroupHeader(int numVerts, int vertOffset, int numIndices, int indexOffset, int numStrips, int stripOffset, byte flags) {
		this.numVerts = numVerts;
		this.vertOffset = vertOffset;
		this.numIndices = numIndices;
		this.indexOffset = indexOffset;
		this.numStrips = numStrips;
		this.stripOffset = stripOffset;
		this.flags = flags;
	}

	public int getNumVerts() {
		return numVerts;
	}

	public int getVertOffset() {
		return vertOffset;
	}

	public int getNumIndices() {
		return numIndices;
	}

	public int getIndexOffset() {
		return indexOffset;
	}

	public int getNumStrips() {
		return numStrips;
	}

	public int getStripOffset() {
		return stripOffset;
	}

	public byte getFlags() {
		return flags;
	}

}
