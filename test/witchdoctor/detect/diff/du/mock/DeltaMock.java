package witchdoctor.detect.diff.du.mock;

import java.util.List;

import difflib.Chunk;
import difflib.Delta;
import difflib.PatchFailedException;

public class DeltaMock extends Delta {
	
	public static Delta newDeletion(String original) {
		return new DeltaMock(new String[] { original }, new String[] {});
	}
	
	public static Delta newDeletion(String[] original) {
		return new DeltaMock(original, new String[] {});
	}
	
	public static Delta newInsertion(String revised) {
		return new DeltaMock(new String[] {}, new String[] { revised });
	}
	
	public static Delta newInsertion(String[] revised) {
		return new DeltaMock(new String[] {}, revised);
	}
	
	public static Delta newUpdate(String original, String revised) {
		return new DeltaMock(new String[] { original }, new String[] { revised });
	}
	
	public static Delta newUpdate(String[] original, String[] revised) {
		return new DeltaMock(original, revised);
	}
	
	private DeltaMock(String[] original, String[] revised) {
		super(new ChunkMock(original), new ChunkMock(revised));
	}

	@Override
	public void applyTo(List<Object> arg0) throws PatchFailedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public TYPE getType() {
		throw new UnsupportedOperationException();
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
