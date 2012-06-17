package witchdoctor.detect.lldiff;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import witchdoctor.WitchDoctorException;
import witchdoctor.detect.changes.IChange;
import witchdoctor.detect.changes.IChangeFactory;
import witchdoctor.detect.changes.IMacroChange;

import com.google.common.collect.Iterables;

import difflib.ChangeDelta;
import difflib.Chunk;
import difflib.DeleteDelta;
import difflib.Delta;
import difflib.Delta.TYPE;
import difflib.InsertDelta;

public class DifflibChangeFactoryTest {
	
	private static IChangeFactory getMockFactory() {
		return new IChangeFactory() {

			@Override
			public IChange create(boolean isdeletion, int position,
					Object content) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> IMacroChange<T> createMacro(final boolean isdeletion,
					final int position, final Iterable<T> content) {
				return new IMacroChange<T>() {

					@Override
					public boolean isDeletion() {
						return isdeletion;
					}

					@Override
					public boolean isInsertion() {
						return !isdeletion;
					}

					@SuppressWarnings("hiding")
					@Override
					public <T> T getContent() {
						return null;
					}

					@Override
					public int getPosition() {
						return position;
					}

					@Override
					public Iterator<T> iterator() {
						return content.iterator();
					}
					
				};
			}

			@Override
			public <T> Iterable<IChange> split(IMacroChange<T> macro) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	@Test
	public void cutUpdates_delete() {
		Delta delta = new DeleteDelta(null, null);
		Iterable<Delta> deltas = Collections.singletonList(delta);
		DifflibChangeFactory instance = new DifflibChangeFactory(null);
		Iterable<Delta> cuts = instance.cutUpdates(deltas);
		Delta first = Iterables.getFirst(cuts, null);
		assertEquals(delta, first);
	}
	
	@Test
	public void cutUpdates_insert() {
		Delta delta = new InsertDelta(null, null);
		Iterable<Delta> deltas = Collections.singletonList(delta);
		DifflibChangeFactory instance = new DifflibChangeFactory(null);
		Iterable<Delta> cuts = instance.cutUpdates(deltas);
		Delta first = Iterables.getFirst(cuts, null);
		assertEquals(delta, first);
	}
	
	@Test
	public void cutUpdates_change() {
		Chunk original = new Chunk(0, (List<?>)null);
		Chunk revised = new Chunk(0, (List<?>)null);
		Delta delta = new ChangeDelta(original, revised);
		Iterable<Delta> deltas = Collections.singletonList(delta);
		DifflibChangeFactory instance = new DifflibChangeFactory(null);
		Iterable<Delta> cuts = instance.cutUpdates(deltas);
		Delta first = Iterables.get(cuts, 0);
		assertEquals(original, first.getOriginal());
		assertEquals(TYPE.DELETE, first.getType());
		Delta second = Iterables.get(cuts, 1);
		assertEquals(revised, second.getRevised());
		assertEquals(TYPE.INSERT, second.getType());
	}
	
	@Test
	public void create_insert() throws WitchDoctorException {
		IChangeFactory factory = getMockFactory();
		Delta delta = new InsertDelta(null, new Chunk(0, (List<?>)null));
		DifflibChangeFactory instance = new DifflibChangeFactory(factory);
		IMacroChange<Object> result = instance.create(delta);
		assertTrue(result.isInsertion());
		assertEquals(0, result.getPosition());
	}
	
	@Test
	public void create_delete() throws WitchDoctorException {
		IChangeFactory factory = getMockFactory();
		Delta delta = new DeleteDelta(new Chunk(0, (List<?>)null), null);
		DifflibChangeFactory instance = new DifflibChangeFactory(factory);
		IMacroChange<Object> result = instance.create(delta);
		assertTrue(result.isDeletion());
		assertEquals(0, result.getPosition());
	}

}
