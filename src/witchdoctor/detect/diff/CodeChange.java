package witchdoctor.detect.diff;

public abstract class CodeChange implements Change {

	@Override
	public abstract boolean isDeletion();

	@Override
	public abstract boolean isInsertion();

	@Override
	public abstract boolean isUpdate();

	@Override
	public abstract  CodeChunk getOriginal();

	@Override
	public abstract CodeChunk getRevised();
	
	public abstract Iterable<? extends CodeChange> explodeLines();
}
