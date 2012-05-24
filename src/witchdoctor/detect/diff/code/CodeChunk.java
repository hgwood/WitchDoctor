package witchdoctor.detect.diff.code;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import witchdoctor.detect.diff.Chunk;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

/**
 * A code fragment of zero or more lines 
 * that belongs to a larger piece of code.
 * Its lines can be iterated.
 * @author Hugo Wood
 *
 */
public class CodeChunk implements Chunk, Iterable<String> {
	
	private final int position;
	private final FluentIterable<String> lines;
	
	public CodeChunk(int position, Iterable<String> lines) {
		this.position = position;
		this.lines = FluentIterable.from(lines);
	}
	
	/**
	 * Returns a specific line of the chunk.
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException if index is below zero or if index
	 * is greater than the size of the chunk minus one.
	 */
	public String getLine(int index) {
		return lines.get(index);
	}

	/**
	 * Returns the position of this chunk relative to the code it originates
	 * from (i.e. the line number minus one).
	 */
	@Override
	public int getPosition() {
		return position;
	}

	/**
	 * Returns the size of this chunk in number of lines.
	 */
	@Override
	public int getSize() {
		return lines.size();
	}

	@Override
	public Iterator<String> iterator() {
		return lines.iterator();
	}
	
	/**
	 * Splits this chunks into multiple chunks spanning only one line.
	 * @return
	 */
	public Iterable<CodeChunk> explodeLines() {
		Collection<CodeChunk> subChunks = Lists.newLinkedList();
		int currentPosition = position;
		for (String line : lines) {
			List<String> singleLine = Collections.singletonList(line);
			subChunks.add(new CodeChunk(currentPosition++, singleLine));
		}
		return subChunks;
	}

}
