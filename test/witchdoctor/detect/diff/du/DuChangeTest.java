package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import witchdoctor.detect.diff.du.mock.DeltaMock;
import difflib.Delta;

public class DuChangeTest {
	
	@Test
	public void testInsert() {
		String revised = "some code";
		Delta delta = DeltaMock.newInsertion(revised);
		DuChange instance = new DuChange(delta);
		assertFalse(instance.isDelete());
		assertTrue(instance.isInsert());
		assertFalse(instance.isUpdate());
		assertEquals("", instance.getOriginal().getText());
		assertEquals(revised, instance.getRevised().getText());
	}
	
	@Test
	public void testDelete() {
		String original = "some code";
		Delta delta = DeltaMock.newDeletion(original);
		DuChange instance = new DuChange(delta);
		assertTrue(instance.isDelete());
		assertFalse(instance.isInsert());
		assertFalse(instance.isUpdate());
		assertEquals(original, instance.getOriginal().getText());
		assertEquals("", instance.getRevised().getText());
	}

	@Test
	public void testUpdate() {
		String original = "some code";
		String revised = "some different code";
		Delta delta = DeltaMock.newUpdate(original, revised);
		DuChange instance = new DuChange(delta);
		assertFalse(instance.isDelete());
		assertFalse(instance.isInsert());
		assertTrue(instance.isUpdate());
		assertEquals(original, instance.getOriginal().getText());
		assertEquals(revised, instance.getRevised().getText());
	}

}
