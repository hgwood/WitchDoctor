package witchdoctor.utils;

public class Range {
	
	private final int start;
	private final int end;
	private final int length;
	
	public Range(int start, int length) {
		this.start = start;
		this.end = start + length;
		this.length = length;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getLength() {
		return length;
	}

}
