package witchdoctor.detect.diff;

import witchdoctor.detect.CodeDocument;

public interface Differencer {
	
	Iterable<? extends Change> diff(CodeDocument left, CodeDocument right);

}
