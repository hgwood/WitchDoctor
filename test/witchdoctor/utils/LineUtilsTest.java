package witchdoctor.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

public class LineUtilsTest {
	
	// TODO there are some improvement possible here
	// maybe tests could take a parameter (ala pex)

	@Test
	public void test_toLines_empty() {
		String text = "";
		FluentIterable<String> result = LineUtils.toLines(text);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void test_toLines_oneLine() {
		String text = "some text";
		FluentIterable<String> result = LineUtils.toLines(text);
		assertEquals(1, result.size());
		assertEquals(text, result.get(0));
	}
	
	@Test
	public void test_toLines_multipleLines() {
		List<String> lines = ImmutableList.of("some text", "and another line");
		String text = lines.get(0) + "\n" + lines.get(1);
		FluentIterable<String> result = LineUtils.toLines(text);
		assertEquals(2, result.size());
		assertEquals(lines.get(0), result.get(0));
		assertEquals(lines.get(1), result.get(1));
	}
	
	@Test
	public void test_fromLines_empty() {
		Iterable<String> lines = new LinkedList<String>();
		String result = LineUtils.fromLines(lines);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void test_fromLines_oneLine() {
		List<String> lines = ImmutableList.of("some text");
		String result = LineUtils.fromLines(lines);
		assertEquals(lines.get(0), result);
	}
	
	@Test
	public void test_fromLines_multipleLines() {
		List<String> lines = ImmutableList.of("some text", "and another line");
		String result = LineUtils.fromLines(lines);
		assertEquals(lines.get(0) + "\n" + lines.get(1), result);
	}

}
