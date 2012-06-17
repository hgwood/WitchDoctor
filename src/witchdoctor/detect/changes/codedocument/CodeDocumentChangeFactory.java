package witchdoctor.detect.changes.codedocument;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.changes.ChangeFactory;
import witchdoctor.detect.changes.IChange;

import com.google.common.collect.Iterables;

public class CodeDocumentChangeFactory extends ChangeFactory {
	
	private final CodeDocument original;
	private final CodeDocument revised;
	
	public CodeDocumentChangeFactory(CodeDocument original, CodeDocument revised) {
		super();
		this.original = original;
		this.revised = revised;
	}

	@Override
	public ICodeDocumentChange create(boolean isdeletion, int position, Object content) {
		IChange change = super.create(isdeletion, position, content);
		CodeDocument container = change.isDeletion() ? original : revised;
		CodeDocument other = change.isDeletion() ? revised : original;
		int charPosition = 0;
		for (int i = 0; i < change.getPosition(); i++) {
			charPosition += Iterables.get(container.getLines(), i).length();
		}
		return new CodeDocumentChange(change, container, other, charPosition);
	}
	
	/*public CodeDocumentChange create(IChange change, 
			CodeDocument original, CodeDocument revised) {
		CodeDocument container = change.isDeletion() ? original : revised;
		CodeDocument other = change.isDeletion() ? revised : original;
		int charPosition = 0;
		for (int i = 0; i < change.getPosition(); i++) {
			charPosition += Iterables.get(container.getLines(), i).length();
		}
		return new CodeDocumentChange(change, container, other, charPosition);
	}*/

}
