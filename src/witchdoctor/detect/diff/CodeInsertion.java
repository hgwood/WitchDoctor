package witchdoctor.detect.diff;

public class CodeInsertion implements Change {
	
	private final CodeChunk code;
	
	public CodeInsertion(CodeChunk inserted) {
		this.code = inserted;
	}
	
	@Override
	public boolean isDeletion() {
		return false;
	}

	@Override
	public boolean isInsertion() {
		return true;
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public CodeChunk getOriginal() {
		throw new UnsupportedOperationException();
	}

	@Override
	public CodeChunk getRevised() {
		return code;
	}

}
