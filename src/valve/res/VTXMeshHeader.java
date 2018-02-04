package valve.res;

public class VTXMeshHeader {

	private int numStripGroups;
	private int stripGroupHeaderOffset;
	private byte flags;

	public VTXMeshHeader(int numStripGroups, int stripGroupHeaderOffset, byte flags) {
		this.numStripGroups = numStripGroups;
		this.stripGroupHeaderOffset = stripGroupHeaderOffset;
		this.flags = flags;
	}

	public int getNumStripGroups() {
		return numStripGroups;
	}

	public int getStripGroupHeaderOffset() {
		return stripGroupHeaderOffset;
	}

	public byte getFlags() {
		return flags;
	}

}
