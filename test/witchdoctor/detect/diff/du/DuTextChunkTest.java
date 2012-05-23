package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import witchdoctor.detect.diff.du.mock.ChunkMock;
import difflib.Chunk;

public class DuTextChunkTest {

	@Test
	public void instanciation() {
		List<String> lines = ImmutableList.of("some code");
		Chunk chunk = new ChunkMock(lines);
		DuTextChunk instance = new DuTextChunk(chunk);
		assertEquals(chunk.getPosition(), instance.getPosition().getStart());
		assertEquals(lines.size(), instance.getPosition().getLength());
		assertEquals(lines.get(0), instance.getText());
	}

}
