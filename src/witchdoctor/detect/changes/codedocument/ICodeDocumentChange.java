package witchdoctor.detect.changes.codedocument;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.changes.IChange;

public interface ICodeDocumentChange extends IChange {
	
	CodeDocument getContainerDocument();
	CodeDocument getOtherDocument();

}
