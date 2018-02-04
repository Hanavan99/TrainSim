package valve.res;

public class VVDVertexHeaderTable {

	private int id;
	private int version;
	private long checksum;
	private int numLODs;
	private int[] numLODVertexes;
	private int numFixups;
	private int fixupTableStart;
	private int vertexDataStart;
	private int tangentDataStart;

	public VVDVertexHeaderTable(int id, int version, long checksum, int numLODs, int[] numLODVertexes, int numFixups, int fixupTableStart, int vertexDataStart, int tangentDataStart) {
		this.id = id;
		this.version = version;
		this.checksum = checksum;
		this.numLODs = numLODs;
		this.numLODVertexes = numLODVertexes;
		this.numFixups = numFixups;
		this.fixupTableStart = fixupTableStart;
		this.vertexDataStart = vertexDataStart;
		this.tangentDataStart = tangentDataStart;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @return the checksum
	 */
	public long getChecksum() {
		return checksum;
	}

	/**
	 * @return the numLODs
	 */
	public int getNumLODs() {
		return numLODs;
	}

	/**
	 * @return the numLODVertexes
	 */
	public int[] getNumLODVertexes() {
		return numLODVertexes;
	}

	/**
	 * @return the numFixups
	 */
	public int getNumFixups() {
		return numFixups;
	}

	/**
	 * @return the fixupTableStart
	 */
	public int getFixupTableStart() {
		return fixupTableStart;
	}

	/**
	 * @return the vertexDataStart
	 */
	public int getVertexDataStart() {
		return vertexDataStart;
	}

	/**
	 * @return the tangentDataStart
	 */
	public int getTangentDataStart() {
		return tangentDataStart;
	}

}
