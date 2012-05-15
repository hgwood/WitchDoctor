package witchdoctor.detect.diff;

import witchdoctor.detect.CodeDocument;

public interface Differencer {
	
	Iterable<Change> diff(CodeDocument left, CodeDocument right);

}
