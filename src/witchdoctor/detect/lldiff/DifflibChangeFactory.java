package witchdoctor.detect.lldiff;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import witchdoctor.WitchDoctorException;
import witchdoctor.detect.changes.IChangeFactory;
import witchdoctor.detect.changes.IMacroChange;
import difflib.Chunk;
import difflib.DeleteDelta;
import difflib.Delta;
import difflib.InsertDelta;
import difflib.Delta.TYPE;

/**
 * Creates WitchDoctor's IChange objects from the diffutils library's objects.
 * @author Hugo Wood
 *
 */
public class DifflibChangeFactory {
	
	private final IChangeFactory changeFactory; 
	
	/**
	 * 
	 * @param changeFactory will build IChange object from diffutils' objects info
	 */
	public DifflibChangeFactory(IChangeFactory changeFactory) {
		super();
		this.changeFactory = changeFactory;
	}
	
	/**
	 * Transforms deltas of type CHANGE into two deltas: one INSERT and one
	 * DELETE. Other deltas are left unchanged.
	 * @param deltas a collection of deltas of any type
	 * @return a collection of deltas of type INSERT or DELETE
	 */
	public Iterable<Delta> cutUpdates(Iterable<Delta> deltas) {
		List<Delta> cuts = Lists.newLinkedList();
		for (Delta delta : deltas) {
			switch (delta.getType()) {
			case CHANGE:
				Chunk emptyOriginal = new Chunk(
					delta.getOriginal().getPosition(), 
					Collections.EMPTY_LIST);
				Delta ins = new InsertDelta(emptyOriginal, delta.getRevised());
				cuts.add(ins);
				Chunk emptyRevised = new Chunk(
					delta.getRevised().getPosition(), 
					Collections.EMPTY_LIST);
				Delta del = new DeleteDelta(delta.getOriginal(), emptyRevised);
				cuts.add(del);
				break;
			default:
				cuts.add(delta);
				break;
			}
		}
		return cuts;
	}

	/**
	 * Adapts Delta object from the diffutils library to WitchDoctor's IChange
	 * objects. Since diffutils' Deltas can contain several changes, a 
	 * IMacroChange object is returned. Individual changes can then be 
	 * retrieved by splitting this object using an IChangeFactory.
	 * Deltas representing an update (type CHANGE in diffutils) cannot be 
	 * processed by this method.
	 * @param delta
	 * @return
	 * @throws WitchDoctorException if delta is of type CHANGE
	 */
	@SuppressWarnings("unchecked")
	public IMacroChange<Object> create(Delta delta) 
	throws WitchDoctorException {
		if (delta.getType() == TYPE.CHANGE) { throw new WitchDoctorException(); }
		boolean isdeletion = delta.getType() == TYPE.DELETE;
		Chunk content = isdeletion ? delta.getOriginal() : delta.getRevised();
		return changeFactory.createMacro(
			isdeletion, 
			content.getPosition(), 
			(Iterable<Object>)content.getLines());
	}
	
	/**
	 * Same as the other version of create, but for a collection of deltas.
	 * The collection can contain deltas of type CHANGE. Those will 
	 * be cut using {@link cutUpdates}.
	 * @param deltas
	 * @return
	 */
	public Iterable<IMacroChange<Object>> create(Iterable<Delta> deltas) {
		List<IMacroChange<Object>> changes = Lists.newLinkedList();
		Iterable<Delta> cuts = cutUpdates(deltas);
		for (Delta delta : cuts) {
			try {
				changes.add(create(delta));
			} catch (WitchDoctorException e) {
				assert false;
			}
		}
		return changes;
	}

}
