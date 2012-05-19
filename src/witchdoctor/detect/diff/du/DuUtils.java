package witchdoctor.detect.diff.du;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import witchdoctor.utils.LineUtils;
import difflib.Chunk;
import difflib.DeleteDelta;
import difflib.Delta;
import difflib.InsertDelta;

public class DuUtils {
	
	public static boolean isDelete(Delta delta) {
		String revisedText = getText(delta.getRevised());
		return StringUtils.isBlank(revisedText);
	}
	
	public static boolean isInsert(Delta delta) {
		String originalText = getText(delta.getOriginal());
		return StringUtils.isBlank(originalText);
	}
	
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
	
	@SuppressWarnings("unchecked")
	public static Iterable<String> getLines(Chunk chunk) {
		return (Iterable<String>)chunk.getLines();
	}
	
	public static String getText(Chunk chunk) {
		Iterable<String> lines = getLines(chunk);
		return LineUtils.fromLines(lines);
	}
	
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
