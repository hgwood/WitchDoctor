package witchdoctor.detect.diff.du.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import difflib.Chunk;
import difflib.PatchFailedException;

public class ChunkMock extends Chunk {
	
	private final String text;
	
	public ChunkMock(String line) {
		super(0, Collections.singletonList(line));
		this.text = line;
	}
	
	public ChunkMock(String[] lines) {
		super(0, Arrays.asList(lines));
		this.text = StringUtils.join(lines, "\n");
	}
	
	@Override
	public void verify(List<?> target) throws PatchFailedException {
		throw new UnsupportedOperationException();
	}
	
	/*@Override
	public int getPosition() {
		throw new UnsupportedOperationException();
    }*/
	
	@Override
	public void setLines(List<?> lines) {
		throw new UnsupportedOperationException();
    }
	
	/*@Override
	public List<?> getLines() {
		throw new UnsupportedOperationException();
    }
	
	@Override
	public int size() {
		throw new UnsupportedOperationException();
    }*/
	
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
	
	public String getText() {
		return text;
	}

}
