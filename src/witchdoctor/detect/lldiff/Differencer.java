package witchdoctor.detect.lldiff;

import java.util.List;

import witchdoctor.detect.CodeDocument;
import witchdoctor.detect.changes.IChange;
import witchdoctor.detect.changes.codedocument.ICodeDocumentChange;
import witchdoctor.detect.changes.codedocument.ICodeDocumentChangeFactory;

import com.google.common.collect.Lists;

import difflib.DiffUtils;
import difflib.Patch;

public class Differencer {
	
	private final DifflibChangeFactory difflibChangeFactory;
	private final ICodeDocumentChangeFactory codeDocumentChangeFactory;
	
	public Differencer(
			DifflibChangeFactory difflibChangeFactory,
			ICodeDocumentChangeFactory codeDocumentChangeFactory) {
		super();
		this.difflibChangeFactory = difflibChangeFactory;
		this.codeDocumentChangeFactory = codeDocumentChangeFactory;
	}
	
	public Iterable<ICodeDocumentChange> diff(
			CodeDocument original, CodeDocument revised) {
		Patch patch = DiffUtils.diff(
			original.getLines(), revised.getLines());
		Iterable<IChange> allChanges =
			difflibChangeFactory.create(patch.getDeltas());
		List<ICodeDocumentChange> allCodeDocumentChanges = 
			Lists.newLinkedList();
		for (IChange change : allChanges) {
			allCodeDocumentChanges.add(
				codeDocumentChangeFactory.create(change, original, revised));
		}
		return allCodeDocumentChanges;
	}

}
