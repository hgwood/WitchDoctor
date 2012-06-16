package witchdoctor.detect;

import java.util.Iterator;

class MacroChange<T> extends ChangeDecorator implements Iterable<T> {

	private final Iterable<T> content;
	
	public MacroChange(IChange decorated, Iterable<T> content) {
		super(decorated);
		this.content = content;
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}

}
