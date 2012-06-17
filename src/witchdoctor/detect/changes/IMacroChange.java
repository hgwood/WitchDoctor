package witchdoctor.detect.changes;

/**
 * Represents a change spanning multiple elements (lines for example).
 * Roughly equivalent to a collection of subsequent changes of the same type
 * (insertion or deletion).
 * @author Hugo Wood
 *
 * @param <T> the type of the elements contained in this macro change.
 */
public interface IMacroChange<T> extends IChange, Iterable<T> {

}
