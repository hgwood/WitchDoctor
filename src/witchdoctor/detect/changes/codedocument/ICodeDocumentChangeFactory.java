package witchdoctor.detect.changes.codedocument;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.changes.IChange;

public interface ICodeDocumentChangeFactory {
	
	CodeDocumentChange create(IChange change, 
			CodeDocument original, CodeDocument revised);

}
