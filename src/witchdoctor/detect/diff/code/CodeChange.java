package witchdoctor.detect.diff.code;

import witchdoctor.detect.diff.Change;

public abstract class CodeChange implements Change {
	
	private final CodeChunk original;
	private final CodeChunk revised;
	
	protected CodeChange(CodeChunk original, CodeChunk revised) {
		this.original = original;
		this.revised = revised;
	}

	@Override
	public boolean isDeletion() {
		return revised.getSize() == 0;
	}

	@Override
	public boolean isInsertion() {
		return original.getSize() == 0;
	}

	@Override
	public boolean isUpdate() {
		return !isDeletion() && !isInsertion();
	}

	@Override
	public  CodeChunk getOriginal() {
		return original;
	}

	@Override
	public CodeChunk getRevised() {
		return revised;
	}
	
	public abstract Iterable<? extends CodeChange> explodeLines();
}
