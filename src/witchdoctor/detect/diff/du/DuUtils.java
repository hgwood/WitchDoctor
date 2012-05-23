package witchdoctor.detect.diff.du;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import witchdoctor.utils.LineUtils;
import difflib.Chunk;
import difflib.DeleteDelta;
import difflib.Delta;
import difflib.InsertDelta;

/**
 * Utility class containing several methods to facilitate the manipulation 
 * of diffutils' <code>Delta</code> and <code>Chunk</code> objects.
 * @author Hugo Wood
 *
 */
public final class DuUtils {
	
	private DuUtils() {} // Hiding constructor of utility class.
	
	/**
	 * Returns <code>true</code> if the delta represents a deletion.
	 * A deletion means that the content was replaced by either nothing or 
	 * white spaces. This is different from <code>delta.getType() == 
	 * Delta.TYPE.DELETE</code>, which would return false in the second case.
	 * @param delta
	 * @return
	 */
	public static boolean isDelete(Delta delta) {
		String revisedText = getText(delta.getRevised());
		return LineUtils.isBlank(revisedText);
	}
	
	/**
	 * Returns <code>true</code> if the delta represents a insertion.
	 * A insertion means that the original content was either nothing or 
	 * white spaces. This is different from <code>delta.getType() == 
	 * Delta.TYPE.INSERT</code>, which would return false in the second case.
	 * @param delta
	 * @return
	 */
	public static boolean isInsert(Delta delta) {
		String originalText = getText(delta.getOriginal());
		return LineUtils.isBlank(originalText);
	}
	
	/**
	 * Splits a delta spanning multiple lines to multiple deltas spanning
	 * only one line each.
	 * @param delta
	 * @return
	 */
	public static Iterable<Delta> toOneLiners(Delta delta) {
		List<Delta> oneLiners = new LinkedList<Delta>();
		if (isDelete(delta)) {
			Iterable<Chunk> oneLinerChunks = toOneLiners(delta.getOriginal());
			for (Chunk chunk : oneLinerChunks) {
				Delta oneLiner = new DeleteDelta(chunk, delta.getRevised());
				oneLiners.add(oneLiner);
			}
		} else if (isInsert(delta)) {
			Iterable<Chunk> oneLinerChunks = toOneLiners(delta.getRevised());
			for (Chunk chunk : oneLinerChunks) {
				Delta oneLiner = new InsertDelta(delta.getOriginal(), chunk);
				oneLiners.add(oneLiner);
			}
		} else {
			oneLiners.add(delta);
		}
		return oneLiners;
	}
	
	/**
	 * Returns the content of a chunk of text as a collection of lines.
	 * @param chunk
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Iterable<String> getLines(Chunk chunk) {
		return (Iterable<String>)chunk.getLines();
	}
	
	/**
	 * Retrieves the content of a chunk of text as a single string.
	 * @param chunk
	 * @return
	 */
	public static String getText(Chunk chunk) {
		Iterable<String> lines = getLines(chunk);
		return LineUtils.fromLines(lines);
	}
	
	/**
	 * Splits a chunk of text spanning multiple lines to multiple
	 * chunks of text spanning only one line each.
	 * @param chunk
	 * @return
	 */
	public static Iterable<Chunk> toOneLiners(Chunk chunk) {
		List<Chunk> oneLiners = new LinkedList<Chunk>();
		int startPosition = chunk.getPosition();
		for (String line : getLines(chunk)) {
			List<?> singletonLine = Collections.singletonList(line);
			Chunk oneLiner = new Chunk(startPosition++, singletonLine);
			oneLiners.add(oneLiner);
		}
		return oneLiners;
	}

}
