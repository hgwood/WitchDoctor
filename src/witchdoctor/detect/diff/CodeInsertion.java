package witchdoctor.detect.diff;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class CodeInsertion extends CodeChange {
	
	private final CodeChunk code;
	
	public CodeInsertion(CodeChunk inserted) {
		this.code = inserted;
	}
	
	@Override
	public boolean isDeletion() {
		return false;
	}

	@Override
	public boolean isInsertion() {
		return true;
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public CodeChunk getOriginal() {
		throw new UnsupportedOperationException();
	}

	@Override
	public CodeChunk getRevised() {
		return code;
	}
	
	@Override
	public Iterable<CodeInsertion> explodeLines() {
		return Iterables.transform(code.explodeLines(), 
			new Function<CodeChunk, CodeInsertion>() {
				@Override
				public CodeInsertion apply(CodeChunk input) {
					return new CodeInsertion(input);
				}
			}
		);
	}

}
