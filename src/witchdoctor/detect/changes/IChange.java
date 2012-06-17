package witchdoctor.detect.changes;

/**
 * Represents a change between two entities.
 * A change is either an insertion/addition or a deletion/removal.
 * @author Hugo Wood
 *
 */
public interface IChange {
	
	/**
	 * This should be equivalent to <code>!isInsertion()</code>.
	 * @return true if this change represents a deletion; false otherwise.
	 */
	boolean isDeletion();
	
	/**
	 * This should be equivalent to <code>!isDeletion()</code>.
	 * @return true if this change represents an insertion; false otherwise.
	 */
	boolean isInsertion();
	
	/**
	 * 
	 * @return the part that was inserted or deleted.
	 */
	<T> T getContent();
	
	/**
	 * 
	 * @return the position of the change relative to the source entity.
	 */
	int getPosition();

}
