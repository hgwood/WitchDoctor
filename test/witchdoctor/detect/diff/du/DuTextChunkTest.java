package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import witchdoctor.utils.LineUtils;
import difflib.Chunk;

public class DuTextChunkTest {

	@Test
	public void test() {
		String code = "some code";
		List<?> codeLines = LineUtils.toLines(code);
		Chunk chunk = new Chunk(0, codeLines);
		DuTextChunk instance = new DuTextChunk(chunk);
		assertEquals(0, instance.getPosition().getStart());
		assertEquals(codeLines.size(), instance.getPosition().getLength());
		assertEquals(code, instance.getText());
	}

}
