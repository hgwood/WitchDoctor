package witchdoctor.detect.diff;

import witchdoctor.detect.diff.Chunk;

/**
 * A difference between two objects: a deletion, an insertion or an update.
 * @author Hugo Wood
 *
 */
public interface Change {
	
	boolean isDeletion();
	boolean isInsertion();
	boolean isUpdate();
	
	/**
	 * Returns the part of the original object that was changed 
	 * (either deleted or updated).
	 * @return
	 */
	Chunk getOriginal();
	
	/**
	 * Returns the part of the revised object that followed the change
	 * (either what was inserted or the result of an update).
	 * @return
	 */
	Chunk getRevised();

}
