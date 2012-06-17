package witchdoctor.detect.lldiff;

import witchdoctor.WitchDoctorException;
import witchdoctor.detect.changes.IChangeFactory;
import witchdoctor.detect.changes.IMacroChange;
import difflib.Chunk;
import difflib.Delta;
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

}
