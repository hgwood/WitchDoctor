package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import difflib.ChangeDelta;
import difflib.Chunk;
import difflib.Delta;

public class DuChangeTest {
	
	private static Delta createDelta(String original, String revised) {
		List<?> originalCodeLines = Arrays.asList(original.split("\\n"));
		Chunk originalChunk = new Chunk(0, originalCodeLines);
		List<?> revisedCodeLines = Arrays.asList(revised.split("\\n"));
		Chunk revisedChunk = new Chunk(0, revisedCodeLines);
		return new ChangeDelta(originalChunk, revisedChunk);
	}
	
	@Test
	public void testInsert() {
		String original = "";
		String revised = "some different code";
		Delta delta = createDelta(original, revised);
		DuChange instance = new DuChange(delta);
		assertFalse(instance.isDelete());
		assertTrue(instance.isInsert());
		assertFalse(instance.isUpdate());
		assertEquals(original, instance.getOriginal().getText());
		assertEquals(revised, instance.getRevised().getText());
	}
	
	@Test
	public void testDelete() {
		String original = "some code";
		String revised = "";
		Delta delta = createDelta(original, revised);
		DuChange instance = new DuChange(delta);
		assertTrue(instance.isDelete());
		assertFalse(instance.isInsert());
		assertFalse(instance.isUpdate());
		assertEquals(original, instance.getOriginal().getText());
		assertEquals(revised, instance.getRevised().getText());
	}

	@Test
	public void testUpdate() {
		String original = "some code";
		String revised = "some different code";
		Delta delta = createDelta(original, revised);
		DuChange instance = new DuChange(delta);
		assertFalse(instance.isDelete());
		assertFalse(instance.isInsert());
		assertTrue(instance.isUpdate());
		assertEquals(original, instance.getOriginal().getText());
		assertEquals(revised, instance.getRevised().getText());
	}

}
