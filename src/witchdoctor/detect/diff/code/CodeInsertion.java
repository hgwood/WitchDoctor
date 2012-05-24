package witchdoctor.detect.diff.code;

import java.util.Collections;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class CodeInsertion extends CodeChange {

	public CodeInsertion(CodeChunk inserted) {
		super(
			new CodeChunk(
					inserted.getPosition(), 
				Collections.<String>emptyList()), 
			inserted);
	}
	
	@Override
	public Iterable<CodeInsertion> explodeLines() {
		return Iterables.transform(getRevised().explodeLines(), 
			new Function<CodeChunk, CodeInsertion>() {
				@Override
				public CodeInsertion apply(CodeChunk input) {
					return new CodeInsertion(input);
				}
			}
		);
	}

}
