package witchdoctor.detect.diff.du;

import witchdoctor.detect.diff.CodeChunk;

public class DuCodeChunk extends CodeChunk {
	
	@SuppressWarnings("unchecked")
	public DuCodeChunk(difflib.Chunk chunk) {
		super(chunk.getPosition(), (Iterable<String>)chunk.getLines());
	}

}