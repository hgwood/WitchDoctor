package witchdoctor.detect.changes;

/**
 * Point of extension for the {@link IChange} interface. See decorator 
 * pattern.
 * All calls are delegated to the object provided to the constructor. 
 * @author Hugo Wood
 *
 */
public class ChangeDecorator implements IChange {
	
	private final IChange decorated;
	
	/**
	 * 
	 * @return the underlying object being decorated.
	 */
	protected IChange decorated() {
		return decorated;
	}
	
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
