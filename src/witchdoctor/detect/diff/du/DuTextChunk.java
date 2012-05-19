package witchdoctor.detect.diff.du;

import difflib.Chunk;
import witchdoctor.detect.diff.TextChunk;
import witchdoctor.utils.Range;
import witchdoctor.utils.LineUtils;

public class DuTextChunk implements TextChunk {
	
	private final Range position;
	private final String text;
	
	public DuTextChunk(Chunk chunk) {
		this.position = new Range(chunk.getPosition(), chunk.size());
		@SuppressWarnings("unchecked")
		Iterable<String> lines = (Iterable<String>)chunk.getLines();
		this.text = LineUtils.fromLines(lines);
	}

	@Override
	public Range getPosition() {
		return position;
	}

	@Override
	public String getText() {
		return text;
	}

}
