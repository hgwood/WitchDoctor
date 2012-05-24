package witchdoctor.detect.diff.du;

import java.util.List;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.diff.Change;
import witchdoctor.detect.diff.Differencer;
import witchdoctor.detect.diff.code.CodeChange;
import witchdoctor.detect.diff.code.CodeChunk;
import witchdoctor.detect.diff.code.CodeDeletion;
import witchdoctor.detect.diff.code.CodeInsertion;
import witchdoctor.detect.diff.code.CodeUpdate;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import difflib.Chunk;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class DuDifferencer implements Differencer {
	
	@Override
	public Iterable<? extends Change> diff(CodeDocument original, CodeDocument revised) {
		Patch patch = DiffUtils.diff(original.getLines(), revised.getLines());
		List<CodeChange> changes = Lists.transform(patch.getDeltas(), adaptDeltaFunction);
		Iterable<Iterable<? extends CodeChange>> exploded = Lists.transform(changes, explodeChangeFunction);
		return Iterables.concat(exploded);
	}
	
	private final Function<Delta, CodeChange> adaptDeltaFunction = 
		new Function<Delta, CodeChange>() {
			@Override
			public CodeChange apply(Delta input) {
				return adaptDelta(input);
			}
		};
	
	private final Function<CodeChange, Iterable<? extends CodeChange>> explodeChangeFunction = 
		new Function<CodeChange, Iterable<? extends CodeChange>>() {
			@Override
			public Iterable<? extends CodeChange> apply(CodeChange input) {
				return input.explodeLines();
			}
		};
	
	private CodeChange adaptDelta(Delta input) {
		CodeChunk original = adaptChunk(input.getOriginal());
		CodeChunk revised = adaptChunk(input.getRevised());
		if (DuUtils.isDelete(input)) {
			return new CodeDeletion(original);
		} else if (DuUtils.isInsert(input)) {
			return new CodeInsertion(revised);
		} else {
			return new CodeUpdate(original, revised);
		}
	}
	
	@SuppressWarnings("unchecked")
	private CodeChunk adaptChunk(Chunk input) {
		return new CodeChunk(input.getPosition(), (Iterable<String>)input.getLines());
	}

}
