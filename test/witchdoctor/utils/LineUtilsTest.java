package witchdoctor.utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LineUtilsTest {

	@Test
	public void test_toLines_empty() {
		String text = "";
		List<String> result = LineUtils.toLines(text);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void test_toLines_oneLine() {
		String text = "some text";
		List<String> result = LineUtils.toLines(text);
		assertEquals(text, result.get(0));
	}
	
	@Test
	public void test_toLines_multipleLines() {
		String line1 = "some text";
		String line2 = "and another line";
		String text = line1 + "\n" + line2;
		List<String> result = LineUtils.toLines(text);
		assertEquals(line1, result.get(0));
		assertEquals(line2, result.get(1));
	}
	
	@Test
	public void test_fromLines_empty() {
		List<String> lines = Arrays.asList(new String[] {});
		String result = LineUtils.fromLines(lines);
		assertEquals("", result);
	}
	
	@Test
	public void test_fromLines_oneLine() {
		List<String> lines = Arrays.asList(new String[] {"some text"});
		String result = LineUtils.fromLines(lines);
		assertEquals(lines.get(0), result);
	}
	
	@Test
	public void test_fromLines_multipleLines() {
		List<String> lines = Arrays.asList(new String[] {"some text", "and another line"});
		String result = LineUtils.fromLines(lines);
		assertEquals(lines.get(0) + "\n" + lines.get(1), result);
	}

}
