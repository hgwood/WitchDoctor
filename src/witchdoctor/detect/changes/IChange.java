package witchdoctor.detect.changes;

public interface IChange {
	
	boolean isDeletion();
	boolean isInsertion();
	<T> T getContent();
	int getPosition();

}
