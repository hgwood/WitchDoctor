package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import witchdoctor.detect.diff.du.mock.ChunkMock;
import witchdoctor.detect.diff.du.mock.DeltaMock;
import difflib.Chunk;
import difflib.Delta;

public class DuUtilsTest {
	
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
		String[] lines = new String[] { "some code", "more code" };
		Chunk chunk = new ChunkMock(lines);
		Iterable<Chunk> chunks = DuUtils.toOneLiners(chunk);
		int i = 0;
		for (Chunk oneLiner : chunks) {
			assertEquals(1, oneLiner.getLines().size());
			assertEquals(lines[i], oneLiner.getLines().get(0));
			i++;
		}
	}
	
	@Test
	public void test_toOneLinerDeltas_delete() {
		String[] lines = { "some code", "and some code" };
		Delta delta = DeltaMock.newDeletion(lines);
		Iterable<Delta> deltas = DuUtils.toOneLiners(delta);
		int i = 0;
		for (Delta oneLiner : deltas) {
			assertEquals(Delta.TYPE.DELETE, oneLiner.getType());
			assertEquals(1, oneLiner.getOriginal().size());
			assertEquals(0, oneLiner.getRevised().size());
			assertEquals(lines[i], oneLiner.getOriginal().getLines().get(0));
			i++;
		}
	}
	
	@Test
	public void test_toOneLinerDeltas_insert() {
		String[] lines = { "some code", "and some code" };
		Delta delta = DeltaMock.newInsertion(lines);
		Iterable<Delta> deltas = DuUtils.toOneLiners(delta);
		int i = 0;
		for (Delta oneLiner : deltas) {
			assertEquals(Delta.TYPE.INSERT, oneLiner.getType());
			assertEquals(0, oneLiner.getOriginal().size());
			assertEquals(1, oneLiner.getRevised().size());
			assertEquals(lines[i], oneLiner.getRevised().getLines().get(0));
			i++;
		}
	}

}
