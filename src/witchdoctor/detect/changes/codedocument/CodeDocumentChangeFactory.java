package witchdoctor.detect.changes.codedocument;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.changes.IChange;

import com.google.common.collect.Iterables;

public class CodeDocumentChangeFactory implements ICodeDocumentChangeFactory {
	
	public CodeDocumentChange create(IChange change, 
			CodeDocument original, CodeDocument revised) {
		CodeDocument container = change.isDeletion() ? original : revised;
		CodeDocument other = change.isDeletion() ? revised : original;
		int charPosition = 0;
		for (int i = 0; i < change.getPosition(); i++) {
			charPosition += Iterables.get(container.getLines(), i).length();
		}
		return new CodeDocumentChange(change, container, other, charPosition);
	}

}
