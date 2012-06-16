package witchdoctor.detect;

class Change<T> implements IChange {
	
	private final boolean isdeletion;
	private final int position;
	private final Object content;
	
	public Change(boolean isdeletion, int position, Object content) {
		this.isdeletion = isdeletion;
		this.position = position;
		this.content = content;
	}
	
	public boolean isDeletion() {
		return isdeletion;
	}
	
	public final boolean isInsertion() {
		return !isDeletion();
	}
	
	public int getPosition() {
		return position;
	}
	
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
