package witchdoctor.detect.diff.du.mock;

import java.util.Collections;
import java.util.List;

import difflib.Chunk;
import difflib.PatchFailedException;

public class ChunkMock extends Chunk {
	
	private static final int position = 0;
	private final List<String> lines;

	public ChunkMock(String line) {
		super(position, Collections.EMPTY_LIST);
		this.lines = Collections.singletonList(line);
	}
	
	public ChunkMock(List<String> lines) {
		super(position, Collections.EMPTY_LIST);
		this.lines = lines;
	}
	
	@Override
	public void verify(List<?> target) throws PatchFailedException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getPosition() {
		return position;
    }
	
	@Override
	public void setLines(List<?> lines) {
		throw new UnsupportedOperationException();
    }
	
	@Override
	public List<?> getLines() {
		return lines;
    }
	
	@Override
	public int size() {
		return lines.size();
    }
	
	@Override
    public int hashCode() {
		throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(Object obj) {
    	throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
    	throw new UnsupportedOperationException();
    }

}
