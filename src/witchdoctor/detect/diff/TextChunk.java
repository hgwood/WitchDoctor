package witchdoctor.detect.diff;

import witchdoctor.utils.Range;

public interface TextChunk {
	
	Range getPosition();
	String getText();

}
