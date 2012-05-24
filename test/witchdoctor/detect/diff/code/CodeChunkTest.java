package witchdoctor.detect.diff.code;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.FluentIterable;

@RunWith(Parameterized.class)
public class CodeChunkTest {
	
	@Parameters
	public static Collection<Object[]> generateData() {
		return Arrays.asList(
			new Object[] { Arrays.asList("") },
			new Object[] { Arrays.asList("a") },
			new Object[] { Arrays.asList("a", "b") }
		);
	}
	
	private final List<String> lines;

	public CodeChunkTest(List<String> lines) {
		this.lines = lines;
	}

	@Test
	public void explodeLines() {
		CodeChunk instance = new CodeChunk(0, lines);
		FluentIterable<CodeChunk> result = FluentIterable.from(instance.explodeLines());
		assertEquals(lines.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(1, result.get(i).getSize());
			assertEquals(0 + i, result.get(i).getPosition());
			assertEquals(lines.get(i), result.get(i).getLine(0));
		}
	}

}
