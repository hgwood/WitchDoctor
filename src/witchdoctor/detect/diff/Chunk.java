package witchdoctor.detect.diff;

/**
 * A piece of an original, bigger object.
 * @author Hugo Wood_2
 *
 */
public interface Chunk {
	
	/**
	 * The position of this chunk relative to the larger object
	 * it originates from.
	 * @return
	 */
	int getPosition();
	
	/**
	 * The size of this chunk in a meaningful unit relative to
	 * the larger object it originates from.
	 * @return
	 */
	int getSize();

}
