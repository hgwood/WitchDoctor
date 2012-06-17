package witchdoctor.detect.changes;

class ChangeDecorator implements IChange {
	
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
	public <T> T getContent() {
		return decorated.<T>getContent();
	}

	@Override
	public int getPosition() {
		return decorated.getPosition();
	}

}
