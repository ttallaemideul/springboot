package tlmd;

import java.util.List;
import java.util.Map;

public class Util {
	
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ((o instanceof String) && (((String) o).trim().length() == 0)) {
			return true;
		}
		if (o instanceof Map) {
			return ((Map<?, ?>) o).isEmpty();
		}
		if (o instanceof List) {
			return ((List<?>) o).isEmpty();
		}
		if (o instanceof Object[]) {
			return (((Object[]) o).length == 0);
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object o) {
		return ! isEmpty(o);
	}

}
