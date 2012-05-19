package witchdoctor.detect.diff;

import witchdoctor.detect.diff.TextChunk;

public interface Change {
	
	TextChunk getOriginal();
	TextChunk getRevised();

}
