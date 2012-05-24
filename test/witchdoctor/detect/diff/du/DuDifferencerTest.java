package witchdoctor.detect.diff.du;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.diff.Change;

import com.google.common.io.Files;

public class DuDifferencerTest {
	
	private static class CodeDocumentMock implements CodeDocument {
		
		private final List<String> lines;
		
		public CodeDocumentMock(String path) throws IOException {
			this.lines = Files.readLines(new File(path), Charset.forName("UTF-8"));
		}

		@Override
		public List<String> getLines() {
			return lines;
		}
		
	}

	@Test
	public void test() throws IOException {
		CodeDocument original = new CodeDocumentMock("test/data/diff_original.txt");
		CodeDocument revised = new CodeDocumentMock("test/data/diff_revised.txt");
		Iterable<? extends Change> results = new DuDifferencer().diff(original, revised);
		StringBuilder actual = new StringBuilder();
		for (Change change : results) {
			actual.append(change.isDeletion());
			actual.append(' ');
			actual.append(change.isInsertion());
			actual.append(' ');
			actual.append(change.isUpdate());
			actual.append(' ');
			actual.append(change.getOriginal().getPosition());
			actual.append(' ');
			actual.append(change.getOriginal().getSize());
			actual.append(' ');
			actual.append(change.getRevised().getPosition());
			actual.append(' ');
			actual.append(change.getRevised().getSize());
			actual.append('\n');
		}
		String expected = Files.toString(new File("test/data/diff_results.txt"), Charset.forName("UTF-8"));
		assertEquals(expected, actual.toString());
	}

}
