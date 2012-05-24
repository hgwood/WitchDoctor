package witchdoctor.detect.diff.code;

import java.util.Collections;

public class CodeUpdate extends CodeChange {
	
	private final CodeChunk original;
	private final CodeChunk revised;
	
	public CodeUpdate(CodeChunk original, CodeChunk revised) {
		this.original = original;
		this.revised = revised;
	}
	
	@Override
	public boolean isDeletion() {
		return false;
	}

	@Override
	public boolean isInsertion() {
		return false;
	}

	@Override
	public boolean isUpdate() {
		return true;
	}

	@Override
	public CodeChunk getOriginal() {
		return original;
	}

	@Override
	public CodeChunk getRevised() {
		return revised;
	}

	@Override
	public Iterable<? extends CodeChange> explodeLines() {
		return Collections.singletonList(this);
	}

}
