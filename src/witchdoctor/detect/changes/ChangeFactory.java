package witchdoctor.detect.changes;

import java.util.List;

import com.google.common.collect.Lists;

public class ChangeFactory implements IChangeFactory {
	
	@Override
	public IChange create(boolean isdeletion, int position, Object content) {
		return new Change(isdeletion, position, content);
	}
	
	@Override
	public <T> IMacroChange<T> createMacro(boolean isdeletion, int position, Iterable<T> content) {
		IChange decorated = create(isdeletion, position, null);
		return new MacroChange<T>(decorated, content);
	}
	
	@Override
	public <T> Iterable<IChange> split(IMacroChange<T> macroChange) {
		List<IChange> changes = Lists.newLinkedList();
		int currentPosition = 0;
		for (T item : macroChange) {
			IChange change = create(
				macroChange.isDeletion(), 
				currentPosition++, 
				item);
			changes.add(change);
		}
		return changes;
	}
	
}
