package witchdoctor.detect.diff;

public class CodeDeletion extends CodeChange {
	
	private final CodeChunk code;
	
	public CodeDeletion(CodeChunk deleted) {
		this.code = deleted;
	}
	
	@Override
	public boolean isDeletion() {
		return true;
	}

	@Override
	public boolean isInsertion() {
		return false;
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public CodeChunk getOriginal() {
		return code;
	}

	@Override
	public CodeChunk getRevised() {
		throw new UnsupportedOperationException();
	}

}
