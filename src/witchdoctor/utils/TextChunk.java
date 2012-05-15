package witchdoctor.utils;

public class TextChunk {
	
	private final Range position;
	private final String text;
	
	public TextChunk(Range position, String text) {
		this.position = position;
		this.text = text;
	}
	
	public Range getPosition() {
		return position;
	}
	
	public String getText() {
		return text;
	}

}
