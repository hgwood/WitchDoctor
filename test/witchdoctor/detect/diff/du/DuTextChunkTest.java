package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import witchdoctor.detect.diff.du.mock.ChunkMock;
import difflib.Chunk;

public class DuTextChunkTest {

	@Test
	public void test() {
		String code = "some code";
		String[] codeLines = code.split("\\n");
		Chunk chunk = new ChunkMock(codeLines);
		DuTextChunk instance = new DuTextChunk(chunk);
		assertEquals(0, instance.getPosition().getStart());
		assertEquals(codeLines.length, instance.getPosition().getLength());
		assertEquals(code, instance.getText());
	}

}
