package witchdoctor.detect;

public interface IChange {
	
	boolean isDeletion();
	boolean isInsertion();
	int getPosition();

}
