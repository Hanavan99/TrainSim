package valve.res;

public class VVDFixupTable {

	private int lod;
	private int sourceVertexID;
	private int numVertexes;

	public VVDFixupTable(int lod, int sourceVertexID, int numVertexes) {
		this.lod = lod;
		this.sourceVertexID = sourceVertexID;
		this.numVertexes = numVertexes;
	}

	/**
	 * @return the lod
	 */
	public int getLod() {
		return lod;
	}

	/**
	 * @param lod the lod to set
	 */
	public void setLod(int lod) {
		this.lod = lod;
	}

	/**
	 * @return the sourceVertexID
	 */
	public int getSourceVertexID() {
		return sourceVertexID;
	}

	/**
	 * @param sourceVertexID the sourceVertexID to set
	 */
	public void setSourceVertexID(int sourceVertexID) {
		this.sourceVertexID = sourceVertexID;
	}

	/**
	 * @return the numVertexes
	 */
	public int getNumVertexes() {
		return numVertexes;
	}

	/**
	 * @param numVertexes the numVertexes to set
	 */
	public void setNumVertexes(int numVertexes) {
		this.numVertexes = numVertexes;
	}

}
