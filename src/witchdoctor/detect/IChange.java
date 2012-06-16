package witchdoctor.detect;

public interface IChange {
	
	boolean isDeletion();
	boolean isInsertion();
	<T> T getContent();
	int getPosition();

}
