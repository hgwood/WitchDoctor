package witchdoctor.detect.diff.code;

import java.util.Collections;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class CodeDeletion extends CodeChange {

	public CodeDeletion(CodeChunk original) {
		super(
			original,
			new CodeChunk(
				original.getPosition(), 
				Collections.<String>emptyList()));
	}
	
	@Override
	public Iterable<CodeDeletion> explodeLines() {
		return Iterables.transform(getOriginal().explodeLines(), 
			new Function<CodeChunk, CodeDeletion>() {
				@Override
				public CodeDeletion apply(CodeChunk input) {
					return new CodeDeletion(input);
				}
			}
		);
	}

}
