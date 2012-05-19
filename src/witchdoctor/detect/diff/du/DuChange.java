package witchdoctor.detect.diff.du;

import org.apache.commons.lang3.StringUtils;

import witchdoctor.detect.diff.Change;
import witchdoctor.detect.diff.TextChunk;
import difflib.Delta;

public class DuChange implements Change {
	
	//private final Delta delta;
	private final DuTextChunk original;
	private final DuTextChunk revised;
	
	public DuChange(Delta delta) {
		//this.delta = delta;
		this.original = new DuTextChunk(delta.getOriginal());
		this.revised = new DuTextChunk(delta.getRevised());
	}
	
	public boolean isInsert() {
		return StringUtils.isBlank(original.getText());
	}
	
	public boolean isDelete() {
		return StringUtils.isBlank(revised.getText());
	}
	
	public boolean isUpdate() {
		return !isInsert() && !isDelete();
	}

	@Override
	public TextChunk getOriginal() {
		return original;
	}

	@Override
	public TextChunk getRevised() {
		return revised;
	}

}
