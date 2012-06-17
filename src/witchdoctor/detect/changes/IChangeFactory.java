package witchdoctor.detect.changes;

public interface IChangeFactory {
	
	IChange create(boolean isdeletion, int position, Object content);
	<T> IMacroChange<T> createMacro(boolean isdeletion, int position, Iterable<T> content);
	<T> Iterable<IChange> split(IMacroChange<T> macro);

}
