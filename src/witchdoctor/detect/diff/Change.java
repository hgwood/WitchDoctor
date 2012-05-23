package witchdoctor.detect.diff;

import witchdoctor.detect.diff.Chunk;

public interface Change {
	
	boolean isDeletion();
	boolean isInsertion();
	boolean isUpdate();
	
	Chunk getOriginal();
	Chunk getRevised();

}
