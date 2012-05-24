package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import witchdoctor.detect.diff.du.mock.DeltaMock;
import difflib.Delta;

@RunWith(Parameterized.class)
public class DuUtilsTest {
	
	@Parameters
	public static Collection<Object[]> generateData() {
		return Arrays.asList(
			new Object[] {"a", "a", false, false},
			new Object[] {"a", "b", false, false},
			new Object[] {"a", "", true, false},
			new Object[] {"a", "\t\n\r ", true, false},
			new Object[] {"", "a", false, true},
			new Object[] {"\t\n\r ", "a", false, true}
		);
	}
	
	private final String original;
	private final String revised;
	private final boolean isDeletion;
	private final boolean isInsertion;
	
	public DuUtilsTest(String original, String revised, boolean isDeletion, boolean isInsertion) {
		this.original = original;
		this.revised = revised;
		this.isDeletion = isDeletion;
		this.isInsertion = isInsertion;
	}
	
	@Test
	public void test() {
		Delta delta = DeltaMock.newUpdate(original, revised);
		assertEquals(DuUtils.isDelete(delta), isDeletion);
		assertEquals(DuUtils.isInsert(delta), isInsertion);
	}
	/*
	@Test
	public void test_delete() {
		Delta delta = DeltaMock.newUpdate("some code", "\t ");
		assertTrue(DuUtils.isDelete(delta));
		assertFalse(DuUtils.isInsert(delta));
	}
	
	@Test
	public void test_insert() {
		Delta delta = DeltaMock.newUpdate("\t ", "some code");
		assertFalse(DuUtils.isDelete(delta));
		assertTrue(DuUtils.isInsert(delta));
	}

	@Test
	public void test_update() {
		Delta delta = DeltaMock.newUpdate("some code", "some other code");
		assertFalse(DuUtils.isDelete(delta));
		assertFalse(DuUtils.isInsert(delta));
	}
	
	@Test
	public void test_toOneLinerChunks() {
		List<String> lines = ImmutableList.of("some code", "more code");
		Chunk chunk = new ChunkMock(lines);
		Iterable<Chunk> chunks = DuUtils.toOneLiners(chunk);
		int i = 0;
		for (Chunk oneLiner : chunks) {
			assertEquals(1, oneLiner.getLines().size());
			assertEquals(lines.get(i), oneLiner.getLines().get(0));
			i++;
		}
	}
	
	@Test
	public void test_toOneLinerDeltas_delete() {
		List<String> lines = ImmutableList.of("some code", "more code");
		Delta delta = DeltaMock.newDeletion(lines);
		Iterable<Delta> deltas = DuUtils.toOneLiners(delta);
		int i = 0;
		for (Delta oneLiner : deltas) {
			assertEquals(Delta.TYPE.DELETE, oneLiner.getType());
			assertEquals(1, oneLiner.getOriginal().size());
			assertEquals(0, oneLiner.getRevised().size());
			assertEquals(lines.get(i), oneLiner.getOriginal().getLines().get(0));
			i++;
		}
	}
	
	@Test
	public void test_toOneLinerDeltas_insert() {
		List<String> lines = ImmutableList.of("some code", "more code");
		Delta delta = DeltaMock.newInsertion(lines);
		Iterable<Delta> deltas = DuUtils.toOneLiners(delta);
		int i = 0;
		for (Delta oneLiner : deltas) {
			assertEquals(Delta.TYPE.INSERT, oneLiner.getType());
			assertEquals(0, oneLiner.getOriginal().size());
			assertEquals(1, oneLiner.getRevised().size());
			assertEquals(lines.get(i), oneLiner.getRevised().getLines().get(0));
			i++;
		}
	}
*/
}
