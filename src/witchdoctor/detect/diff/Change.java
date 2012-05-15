package witchdoctor.detect.diff;

import witchdoctor.utils.TextChunk;

public interface Change {
	
	TextChunk getOriginal();
	TextChunk getRevised();

}
