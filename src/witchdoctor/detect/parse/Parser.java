package witchdoctor.detect.parse;

import witchdoctor.detect.CodeDocument;

public interface Parser {
	
	AstNode parse(CodeDocument source);

}
