package main;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Sebastián Jiménez (sjimenezc)
 */
public class PathMakerProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(makePath(args[0]));
	}

	public static int makePath(String path) {
		int pathLength = path.length();
		if (pathLength % 4 != 0) {
			return -1;
		}

		Map<Character, Integer> charCount = getCharCount(path);

		return getNumberOfChanges(charCount);
	}

	private static int getNumberOfChanges(Map<Character, Integer> charCount) {
		if (isCountEqual(charCount)) {
			return 0;
		}

		Entry<Character, Integer> minEntry = getMinEntry(charCount);
		Entry<Character, Integer> maxEntry = getMaxEntry(charCount);

		charCount.put(maxEntry.getKey(), maxEntry.getValue() - 1);
		charCount.put(minEntry.getKey(), minEntry.getValue() + 1);

		return 1 + getNumberOfChanges(charCount);
	}

	private static Map<Character, Integer> getCharCount(String s) {
		Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		charCount.put('L', 0);
		charCount.put('R', 0);
		charCount.put('U', 0);
		charCount.put('D', 0);

		for (Character character : s.toCharArray()) {
			Integer count = charCount.get(character);
			charCount.put(character, count + 1);
		}

		return charCount;
	}

	private static Entry<Character, Integer> getMaxEntry(Map<Character, Integer> charCount) {
		Entry<Character, Integer> maxEntry = null;
		for (Entry<Character, Integer> entry : charCount.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

		return maxEntry;
	}

	private static Entry<Character, Integer> getMinEntry(Map<Character, Integer> charCount) {
		Entry<Character, Integer> minEntry = null;
		for (Entry<Character, Integer> entry : charCount.entrySet()) {
			if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
				minEntry = entry;
			}
		}

		return minEntry;
	}

	private static boolean isCountEqual(Map<Character, Integer> charCount) {
		Collection<Integer> values = charCount.values();
		return Collections.max(values).compareTo(Collections.min(values)) == 0;
	}
}
