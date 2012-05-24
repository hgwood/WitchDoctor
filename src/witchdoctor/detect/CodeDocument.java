package witchdoctor.detect;

import java.util.List;

public interface CodeDocument {
	
	/**
	 * Returns the code of the document as an immutable list of lines.
	 * @return
	 */
	List<String> getLines();

}
