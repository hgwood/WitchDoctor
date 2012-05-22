package witchdoctor.utils;

import java.util.Arrays;
import java.util.List;

public final class LineUtils {
	
	private LineUtils() {} // Hiding constructor of utility class.
	
	public static List<String> toLines(String str) {
		return Arrays.asList(org.apache.commons.lang3.StringUtils.split(str, "\n"));
	}
	
	public static String fromLines(Iterable<String> lines) {
		return org.apache.commons.lang3.StringUtils.join(lines, "\n");
	}

}
