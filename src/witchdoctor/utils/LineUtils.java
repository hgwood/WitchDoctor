package witchdoctor.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;

public final class LineUtils {
	
	private LineUtils() {} // Hiding constructor of utility class.
	
	/**
	 * Returns true if the specified string is null, empty, or contains only 
	 * white spaces.
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return 
			Strings.isNullOrEmpty(str) || 
			CharMatcher.WHITESPACE.matchesAllOf(str);
	}
	
	public static FluentIterable<String> toLines(String str) {
		return FluentIterable.from(Splitter.on('\n').split(str));
	}
	
	public static String fromLines(Iterable<String> lines) {
		return Joiner.on('\n').join(lines);
	}

}
