package witchdoctor.detect.changes;

import java.util.List;

import witchdoctor.WitchDoctorException;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import difflib.Chunk;
import difflib.Delta;
import difflib.Delta.TYPE;

public class ChangeFactory {
	
	@SuppressWarnings("unchecked")
	public IMacroChange<Object> fromDiffLib(Delta delta) throws WitchDoctorException {
		if (delta.getType() == TYPE.CHANGE)
			throw new WitchDoctorException();
		boolean isdeletion = delta.getType() == TYPE.DELETE;
		Chunk content = isdeletion ? delta.getOriginal() : delta.getRevised();
		return macroChange(isdeletion, content.getPosition(), (Iterable<Object>)content.getLines());
	}
	
	public <T> IMacroChange<T> macroChange(boolean isdeletion, int position, Iterable<T> content) {
		IChange decorated = new Change<T>(isdeletion, position, null);
		return new MacroChange<T>(decorated, content);
	}
	
	public <T> Iterable<IChange> split(IMacroChange<T> macroChange) {
		List<IChange> changes = Lists.newLinkedList();
		int currentPosition = 0;
		for (T item : macroChange) {
			Change<T> change = new Change<T>(
				macroChange.isDeletion(), 
				currentPosition++, 
				item);
			changes.add(change);
		}
		return changes;
	}
	
	public IChange charPosition(Iterable<String> lines, IChange change) {
		int charPosition = 0;
		for (int i = 0; i < change.getPosition(); i++) {
			charPosition += Iterables.get(lines, i).length();
		}
		return new Reposition(change, charPosition);
	}

}
