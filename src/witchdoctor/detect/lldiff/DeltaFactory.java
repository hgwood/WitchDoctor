package witchdoctor.detect.lldiff;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import difflib.Chunk;
import difflib.DeleteDelta;
import difflib.Delta;
import difflib.InsertDelta;

/**
 * Creates objects of type Delta (from the diffutils library).
 * @author Hugo Wood
 *
 */
public class DeltaFactory {
	
	/**
	 * Transforms deltas of type CHANGE into two deltas: one INSERT and one
	 * DELETE. Other deltas are left unchanged.
	 * @param deltas a collection of deltas of any type
	 * @return a collection of deltas of type INSERT or DELETE
	 */
	public Iterable<Delta> explode(Iterable<Delta> deltas) {
		List<Delta> exploded = Lists.newLinkedList();
		for (Delta delta : deltas) {
			switch (delta.getType()) {
			case CHANGE:
				Chunk emptyOriginal = new Chunk(
					delta.getOriginal().getPosition(), 
					Collections.EMPTY_LIST);
				Delta ins = new InsertDelta(emptyOriginal, delta.getRevised());
				exploded.add(ins);
				Chunk emptyRevised = new Chunk(
					delta.getRevised().getPosition(), 
					Collections.EMPTY_LIST);
				Delta del = new DeleteDelta(delta.getOriginal(), emptyRevised);
				exploded.add(del);
				break;
			default:
				exploded.add(delta);
				break;
			}
		}
		return exploded;
	}

}
