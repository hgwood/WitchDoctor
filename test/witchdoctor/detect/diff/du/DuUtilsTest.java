package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import difflib.ChangeDelta;
import difflib.Chunk;
import difflib.Delta;

public class DuUtilsTest {
	
	private static Delta createDelta(String original, String revised) {
		List<?> originalCodeLines = Arrays.asList(original.split("\\n"));
		Chunk originalChunk = new Chunk(0, originalCodeLines);
		List<?> revisedCodeLines = Arrays.asList(revised.split("\\n"));
		Chunk revisedChunk = new Chunk(0, revisedCodeLines);
		return new ChangeDelta(originalChunk, revisedChunk);
	}
	
	@Test
	public void test_delete() {
		Delta delta = createDelta("some code", "\t ");
		assertTrue(DuUtils.isDelete(delta));
		assertFalse(DuUtils.isInsert(delta));
	}
	
	@Test
	public void test_insert() {
		Delta delta = createDelta("\t ", "some code");
		assertFalse(DuUtils.isDelete(delta));
		assertTrue(DuUtils.isInsert(delta));
	}

	@Test
	public void test_update() {
		Delta delta = createDelta("some code", "some other code");
		assertFalse(DuUtils.isDelete(delta));
		assertFalse(DuUtils.isInsert(delta));
	}
	
	@Test
	public void test_toOneLinerChunks() {
		String code = "some code\nand some code";
		List<String> lines = Arrays.asList(code.split("\\n"));
		Chunk chunk = new Chunk(0, lines);
		Iterable<Chunk> chunks = DuUtils.toOneLiners(chunk);
		Iterator<Chunk> iterator = chunks.iterator();
		Chunk chunk1 = iterator.next();
		Chunk chunk2 = iterator.next();
		assertEquals(1, chunk1.getLines().size());
		assertEquals(1, chunk2.getLines().size());
		assertEquals(lines.get(0), chunk1.getLines().get(0));
		assertEquals(lines.get(1), chunk2.getLines().get(0));
	}
	
	@Test
	public void test_toOneLinerDeltas_delete() {
		String code = "some code\nand some code";
		List<String> lines = Arrays.asList(code.split("\\n"));
		Delta delta = createDelta(code, "");
		Iterable<Delta> deltas = DuUtils.toOneLiners(delta);
		Iterator<Delta> iterator = deltas.iterator();
		Delta delta1 = iterator.next();
		Delta delta2 = iterator.next();
		assertEquals(Delta.TYPE.DELETE, delta1.getType());
		assertEquals(Delta.TYPE.DELETE, delta2.getType());		
		assertEquals(1, delta1.getOriginal().getLines().size());
		assertEquals(1, delta2.getOriginal().getLines().size());
		assertEquals(1, delta1.getRevised().getLines().size());
		assertEquals(1, delta2.getRevised().getLines().size());
		assertEquals(lines.get(0), delta1.getOriginal().getLines().get(0));
		assertEquals(lines.get(1), delta2.getOriginal().getLines().get(0));
		assertEquals("", delta1.getRevised().getLines().get(0));
		assertEquals("", delta2.getRevised().getLines().get(0));
	}
	
	@Test
	public void test_toOneLinerDeltas_insert() {
		String code = "some code\nand some code";
		List<String> lines = Arrays.asList(code.split("\\n"));
		Delta delta = createDelta("", code);
		Iterable<Delta> deltas = DuUtils.toOneLiners(delta);
		Iterator<Delta> iterator = deltas.iterator();
		Delta delta1 = iterator.next();
		Delta delta2 = iterator.next();
		assertEquals(Delta.TYPE.INSERT, delta1.getType());
		assertEquals(Delta.TYPE.INSERT, delta2.getType());		
		assertEquals(1, delta1.getOriginal().getLines().size());
		assertEquals(1, delta2.getOriginal().getLines().size());
		assertEquals(1, delta1.getRevised().getLines().size());
		assertEquals(1, delta2.getRevised().getLines().size());
		assertEquals(lines.get(0), delta1.getRevised().getLines().get(0));
		assertEquals(lines.get(1), delta2.getRevised().getLines().get(0));
		assertEquals("", delta1.getOriginal().getLines().get(0));
		assertEquals("", delta2.getOriginal().getLines().get(0));
	}

}
