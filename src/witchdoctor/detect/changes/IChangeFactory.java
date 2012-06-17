package witchdoctor.detect.changes;

public interface IChangeFactory {
	
	/**
	 * 
	 * @param isdeletion
	 * @param position
	 * @param content
	 * @return
	 */
	IChange create(boolean isdeletion, int position, Object content);
	
	/**
	 * 
	 * @param isdeletion
	 * @param position
	 * @param content
	 * @return
	 */
	<T> IMacroChange<T> createMacro(boolean isdeletion, int position, Iterable<T> content);
	
	/**
	 * Splits a macro change into a collection of individual changes.
	 * @param macro
	 * @return
	 */
	<T> Iterable<IChange> split(IMacroChange<T> macro);

}
