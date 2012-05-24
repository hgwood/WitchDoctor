package witchdoctor.detect.diff;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class CodeDeletion extends CodeChange {
	
	private final CodeChunk code;
	
	public CodeDeletion(CodeChunk deleted) {
		this.code = deleted;
	}
	
	@Override
	public boolean isDeletion() {
		return true;
	}

	@Override
	public boolean isInsertion() {
		return false;
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public CodeChunk getOriginal() {
		return code;
	}

	@Override
	public CodeChunk getRevised() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Iterable<CodeDeletion> explodeLines() {
		return Iterables.transform(code.explodeLines(), 
			new Function<CodeChunk, CodeDeletion>() {
				@Override
				public CodeDeletion apply(CodeChunk input) {
					return new CodeDeletion(input);
				}
			}
		);
	}

}
