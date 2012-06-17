package witchdoctor.detect.changes;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import witchdoctor.WitchDoctorException;
import difflib.Chunk;
import difflib.Delta;
import difflib.InsertDelta;
import difflib.DeleteDelta;
import difflib.Delta.TYPE;

public class Factory {
	
	public Iterable<Delta> explode(Iterable<Delta> deltas) {
		List<Delta> exploded = Lists.newLinkedList();
		for (Delta delta : deltas) {
			switch (delta.getType()) {
			case CHANGE:
				Chunk emptyOriginal = new Chunk(
					delta.getOriginal().getPosition(), 
					Collections.EMPTY_LIST);
				Delta ins = new InsertDelta(emptyOriginal, delta.getRevised());
				exploded.add(ins);
				Chunk emptyRevised = new Chunk(
					delta.getRevised().getPosition(), 
					Collections.EMPTY_LIST);
				Delta del = new DeleteDelta(delta.getOriginal(), emptyRevised);
				exploded.add(del);
				break;
			default:
				exploded.add(delta);
				break;
			}
		}
		return exploded;
	}
	
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
