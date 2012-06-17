package witchdoctor.detect.changes;

class Change implements IChange {
	
	private final boolean isdeletion;
	private final int position;
	private final Object content;
	
	public Change(boolean isdeletion, int position, Object content) {
		this.isdeletion = isdeletion;
		this.position = position;
		this.content = content;
	}
	
	@Override
	public boolean isDeletion() {
		return isdeletion;
	}
	
	@Override
	public final boolean isInsertion() {
		return !isDeletion();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getContent() {
		return (T)content;
	}
	
	@Override
	public int getPosition() {
		return position;
	}
	
	@Override
	public String toString() {
		String prefix = "";
		if (isDeletion()) {
			prefix = "-";
		} else if (isInsertion()) {
			prefix = "+";
		} else {
			prefix = "?";
		}
		return prefix + content.toString();
	}

}
