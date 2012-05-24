package witchdoctor.detect.diff.du.mock;

import java.util.Collections;
import java.util.List;

import difflib.Chunk;
import difflib.Delta;
import difflib.PatchFailedException;

public class DeltaMock extends Delta {
	
	public static Delta newDeletion(String original) {
		return new DeltaMock(
			Collections.singletonList(original), 
			Collections.<String>emptyList());
	}
	
	public static Delta newDeletion(List<String> original) {
		return new DeltaMock(original, Collections.<String>emptyList());
	}
	
	public static Delta newInsertion(String revised) {
		return new DeltaMock(
			Collections.<String>emptyList(), 
			Collections.singletonList(revised));
	}
	
	public static Delta newInsertion(List<String> revised) {
		return new DeltaMock(Collections.<String>emptyList(), revised);
	}
	
	public static Delta newUpdate(String original, String revised) {
		return new DeltaMock(
			Collections.singletonList(original), 
			Collections.singletonList(revised));
	}
	
	public static Delta newUpdate(List<String> original, List<String> revised) {
		return new DeltaMock(original, revised);
	}
	
	private final TYPE type;
	
	private DeltaMock(List<String> original, List<String> revised) {
		super(new ChunkMock(original), new ChunkMock(revised));
		if (original.isEmpty())
			this.type = TYPE.INSERT;
		else if (revised.isEmpty())
			this.type = TYPE.DELETE;
		else
			this.type = TYPE.CHANGE;
	}

	@Override
	public void applyTo(List<Object> arg0) throws PatchFailedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public TYPE getType() {
		return type;
	}

	@Override
	public void restore(List<Object> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void verify(List<?> arg0) throws PatchFailedException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setOriginal(Chunk original) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void setRevised(Chunk revised) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();
	}

}
