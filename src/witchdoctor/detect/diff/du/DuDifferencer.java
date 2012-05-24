package witchdoctor.detect.diff.du;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.diff.Change;
import witchdoctor.detect.diff.CodeChange;
import witchdoctor.detect.diff.CodeChunk;
import witchdoctor.detect.diff.CodeDeletion;
import witchdoctor.detect.diff.CodeInsertion;
import witchdoctor.detect.diff.CodeUpdate;
import witchdoctor.detect.diff.Differencer;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class DuDifferencer implements Differencer {
	
	@Override
	public Iterable<? extends Change> diff(CodeDocument original, CodeDocument revised) {
		Patch patch = DiffUtils.diff(original.getLines(), revised.getLines());
		List<CodeChange> changes = Lists.transform(patch.getDeltas(), adaptDeltaFunction);
		Iterable<Iterable<CodeChange>> exploded = Lists.transform(changes, explodeChangeFunction);
		return Iterables.concat(exploded);
	}
	
	private final Function<Delta, CodeChange> adaptDeltaFunction = 
		new Function<Delta, CodeChange>() {
			@Override
			public CodeChange apply(Delta input) {
				return adaptDelta(input);
			}
		};
	
	private final Function<CodeChange, Iterable<CodeChange>> explodeChangeFunction = 
		new Function<CodeChange, Iterable<CodeChange>>() {
			@Override
			public Iterable<CodeChange> apply(CodeChange input) {
				return explodeChange(input);
			}
		};
	
	private CodeChange adaptDelta(Delta input) {
		CodeChunk original = new DuCodeChunk(input.getOriginal());
		CodeChunk revised = new DuCodeChunk(input.getRevised());
		if (DuUtils.isDelete(input)) {
			return new CodeDeletion(original);
		} else if (DuUtils.isInsert(input)) {
			return new CodeInsertion(revised);
		} else {
			return new CodeUpdate(original, revised);
		}
	}
	
	private Iterable<CodeChange> explodeChange(CodeChange input) {
		
		if (input.isDeletion()) {
			
			Iterable<CodeChunk> exploded = explodeChunk(input.getOriginal());
			return Iterables.transform(exploded, new Function<CodeChunk, CodeChange>() {
				@Override
				public CodeChange apply(CodeChunk input) {
					return new CodeDeletion(input);
				}
			});
			
		} else if (input.isInsertion()) {
			
			Iterable<CodeChunk> exploded = explodeChunk(input.getRevised());
			return Iterables.transform(exploded, new Function<CodeChunk, CodeChange>() {
				@Override
				public CodeChange apply(CodeChunk input) {
					return new CodeInsertion(input);
				}
			});
			
		} else {			
			return Collections.singletonList(input);
		}
		
	}
	
	private Iterable<CodeChunk> explodeChunk(CodeChunk input) {
		Collection<CodeChunk> smallerChunks = Lists.newLinkedList();
		int currentPosition = input.getPosition();
		for (String line : input) {
			List<String> singleLine = Collections.singletonList(line);
			smallerChunks.add(new CodeChunk(currentPosition++, singleLine));
		}
		return smallerChunks;
	}

}
