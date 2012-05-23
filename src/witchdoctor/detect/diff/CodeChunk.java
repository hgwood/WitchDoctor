package witchdoctor.detect.diff;

import java.util.Iterator;

import com.google.common.collect.FluentIterable;

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

}
