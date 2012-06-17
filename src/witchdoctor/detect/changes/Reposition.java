package witchdoctor.detect.changes;

public class Reposition extends ChangeDecorator {
	
	private final int newPosition;
	
	public Reposition(IChange decorated, int newPosition) {
		super(decorated);
		this.newPosition = newPosition;
	}
	
	@Override
	public int getPosition() {
		return newPosition;
	}

}
