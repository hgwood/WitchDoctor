package witchdoctor.detect;

import witchdoctor.utils.Range;

class CompilerMessage {
	
	private final String message;
	private final Range position;
	
	public CompilerMessage(String message, Range position) {
		this.message = message;
		this.position = position;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Range getPosition() {
		return position;
	}

}
