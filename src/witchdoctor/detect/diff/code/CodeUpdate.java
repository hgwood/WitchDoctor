package witchdoctor.detect.diff.code;

import java.util.Collections;

public class CodeUpdate extends CodeChange {

	public CodeUpdate(CodeChunk original, CodeChunk revised) {
		super(original, revised);
	}

	@Override
	public Iterable<? extends CodeChange> explodeLines() {
		return Collections.singletonList(this);
	}

}
