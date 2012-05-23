package witchdoctor.detect.diff.du;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

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
	
	private static class StringIsBlank implements Predicate<Object> {

		@Override
		public boolean apply(Object input) {
			return CharMatcher.WHITESPACE.matchesAllOf(input.toString());
		}
		
	}
	
	private DuUtils() {} // Hiding constructor of utility class.
	
	/**
	 * Returns <code>true</code> if the delta represents a deletion.
	 * A deletion means that the content was replaced by either nothing or 
	 * white spaces. This is different from <code>delta.getType() == 
	 * Delta.TYPE.DELETE</code>, which would return false in the second case.
	 * @param delta
	 * @return
	 * @throws NullPointerException if <code>delta</code> is <code>null</code>
	 */
	public static boolean isDelete(Delta delta) {
		if (delta.getType() == Delta.TYPE.DELETE)
			return true;
		return Iterables.all(delta.getRevised().getLines(), new StringIsBlank());
	}
	
	/**
	 * Returns <code>true</code> if the delta represents a insertion.
	 * A insertion means that the original content was either nothing or 
	 * white spaces. This is different from <code>delta.getType() == 
	 * Delta.TYPE.INSERT</code>, which would return false in the second case.
	 * @param delta
	 * @return
	 * @throws NullPointerException if <code>delta</code> is <code>null</code>
	 */
	public static boolean isInsert(Delta delta) {
		if (delta.getType() == Delta.TYPE.INSERT)
			return true;
		return Iterables.all(delta.getOriginal().getLines(), new StringIsBlank());
	}
	
	/**
	 * Splits a delta spanning multiple lines to multiple deltas spanning
	 * only one line each.
	 * @param delta
	 * @return
	 * @throws NullPointerException if <code>delta</code> is <code>null</code>
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
	 * Splits a chunk of text spanning multiple lines to multiple
	 * chunks of text spanning only one line each.
	 * @param chunk
	 * @return
	 * @throws NullPointerException if <code>chunk</code> is <code>null</code>
	 */
	public static Iterable<Chunk> toOneLiners(Chunk chunk) {
		List<Chunk> oneLiners = new LinkedList<Chunk>();
		int startPosition = chunk.getPosition();
		for (Object line : chunk.getLines()) {
			List<?> singletonLine = Collections.singletonList(line);
			Chunk oneLiner = new Chunk(startPosition++, singletonLine);
			oneLiners.add(oneLiner);
		}
		return oneLiners;
	}

}
