package witchdoctor.detect.changes.codedocument;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.changes.ChangeDecorator;
import witchdoctor.detect.changes.IChange;

class CodeDocumentChange extends ChangeDecorator implements ICodeDocumentChange {
	
	private final CodeDocument container;
	private final CodeDocument other;
	private final int charPosition;

	protected CodeDocumentChange(IChange decorated, 
			CodeDocument container, CodeDocument other, int charPosition) {
		super(decorated);
		this.container = container;
		this.other = other;
		this.charPosition = charPosition;
	}
	
	public CodeDocument getContainerDocument() {
		return container;
	}
	
	public CodeDocument getOtherDocument() {
		return other;
	}
	
	@Override
	public int getPosition() {
		return charPosition;
	}

}
