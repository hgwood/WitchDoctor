package witchdoctor.detect.lldiff;

import witchdoctor.WitchDoctorException;
import witchdoctor.detect.changes.IChangeFactory;
import witchdoctor.detect.changes.IMacroChange;
import difflib.Chunk;
import difflib.Delta;
import difflib.Delta.TYPE;

/**
 * Creates WitchDoctor's IChange objects from Delta object (from the diffutils
 * library).
 * @author Hugo Wood
 *
 */
public class DifflibChangeFactory {
	
	/**
	 * Adapts Delta object from the diffutils library to WitchDoctor's IChange
	 * objects. Since diffutils' Deltas can contain several changes, a 
	 * IMacroChange object is returned. Individual changes can then be 
	 * retrieved by splitting this object using an IChangeFactory.
	 * Deltas representing an update (type CHANGE in diffutils) cannot be 
	 * processed by this method.
	 * @param delta
	 * @param factory will build IChange object from the Delta object info
	 * @return
	 * @throws WitchDoctorException if delta is of type CHANGE
	 */
	@SuppressWarnings("unchecked")
	public IMacroChange<Object> create(Delta delta, IChangeFactory factory) 
	throws WitchDoctorException {
		if (delta.getType() == TYPE.CHANGE) { throw new WitchDoctorException(); }
		boolean isdeletion = delta.getType() == TYPE.DELETE;
		Chunk content = isdeletion ? delta.getOriginal() : delta.getRevised();
		return factory.createMacro(
			isdeletion, 
			content.getPosition(), 
			(Iterable<Object>)content.getLines());
	}

}
