package witchdoctor.detect.diff.du;

import java.util.LinkedList;
import java.util.List;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.diff.Change;
import witchdoctor.detect.diff.Differencer;
import witchdoctor.utils.LineUtils;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class DuDifferencer implements Differencer {

	@Override
	public Iterable<Change> diff(CodeDocument left, CodeDocument right) {
		List<Change> changes = new LinkedList<Change>();
		Patch patch = DiffUtils.diff(
				LineUtils.toLines(left.getCode()).toImmutableList(), 
				LineUtils.toLines(right.getCode()).toImmutableList());
		for (Delta delta : patch.getDeltas()) {
			Iterable<Delta> oneLiners = DuUtils.toOneLiners(delta);
			for (Delta oneLiner : oneLiners) {
				Change change = new DuChange(oneLiner);
				changes.add(change);
			}
		}
		return changes;
	}

}
