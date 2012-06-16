package witchdoctor.detect;

public class ChangeDecorator implements IChange {
	
	private final IChange decorated;
	
	public ChangeDecorator(IChange decorated) {
		this.decorated = decorated;
	}

	@Override
	public boolean isDeletion() {
		return decorated.isDeletion();
	}

	@Override
	public boolean isInsertion() {
		return decorated.isInsertion();
	}

	@Override
	public int getPosition() {
		return decorated.getPosition();
	}

}
