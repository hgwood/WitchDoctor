package witchdoctor.detect.diff.code;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import witchdoctor.detect.diff.Chunk;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

public class CodeChunk implements Chunk, Iterable<String> {
	
	private final int position;
	private final FluentIterable<String> lines;
	
	public CodeChunk(int position, Iterable<String> lines) {
		this.position = position;
		this.lines = FluentIterable.from(lines);
	}
	
	public String getLine(int index) {
		return lines.get(index);
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public int getSize() {
		return lines.size();
	}

	@Override
	public Iterator<String> iterator() {
		return lines.iterator();
	}
	
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
