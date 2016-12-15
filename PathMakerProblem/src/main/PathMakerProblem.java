package main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution to the traveler problem.
 *
 * @author Sebastian Jimenez (sjimenezc)
 */
public class PathMakerProblem {

	/** Character that indicates the traveler to go left. */
	private static final char LEFT = 'L';

	/** Character that indicates the traveler to go right. */
	private static final char RIGHT = 'R';

	/** Character that indicates the traveler to go up. */
	private static final char UP = 'U';

	/** Character that indicates the traveler to go down. */
	private static final char DOWN = 'D';

	/** Map containing the directions as keys and its opposites as values. */
	private static final Map<Character, Character> OPPOSITE_DIRECTION = new HashMap<>();

	static {
		OPPOSITE_DIRECTION.put(LEFT, RIGHT);
		OPPOSITE_DIRECTION.put(RIGHT, LEFT);
		OPPOSITE_DIRECTION.put(UP, DOWN);
		OPPOSITE_DIRECTION.put(DOWN, UP);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(getNumberOfChanges(br.readLine()));
	}

	/**
	 * Obtains the number of changes that the traveler must do in his directions
	 * to go back to the starting point.
	 *
	 * @param path
	 *          string that represents the directions for the traveler.
	 * @return number of changes made to the path.
	 */
	public static int getNumberOfChanges(String path) {
		int pathLength = path.length();
		if (pathLength < 1 || pathLength > 100000 || pathLength % 2 != 0) {
			return -1;
		}

		Map<Character, Integer> charCount = getCharCount(path);
		return getNumberOfChanges(charCount);
	}

	/**
	 * From the map of occurrences, calculates the number of changes that the
	 * traveler needs in his path to go back to the starting point.
	 *
	 * @param charCount
	 *          map with the occurrences for every direction in the path given.
	 * @return number of changes made to the path.
	 */
	private static int getNumberOfChanges(Map<Character, Integer> charCount) {
		if (isCountZero(charCount)) {
			return 0;
		}

		Character maxHorizontal = getMaxHorizontalDirection(charCount);
		Character minHorizontal = OPPOSITE_DIRECTION.get(maxHorizontal);
		Character maxVertical = getMaxVerticalDirection(charCount);
		Character minVertical = OPPOSITE_DIRECTION.get(maxVertical);

		int numberOfChanges = 0;
		if (!isHorizontalCountZero(charCount)) {
			charCount.put(maxHorizontal, charCount.get(maxHorizontal) - 1);
			charCount.put(minHorizontal, charCount.get(minHorizontal) + 1);
			numberOfChanges++;
		}
		if (!isVerticalCountZero(charCount)) {
			charCount.put(maxVertical, charCount.get(maxVertical) - 1);
			charCount.put(minVertical, charCount.get(minVertical) + 1);
			numberOfChanges++;
		}

		return numberOfChanges + getNumberOfChanges(charCount);
	}

	/**
	 * Counts how many occurrences there are for each direction and returns it as
	 * a map.
	 *
	 * @param path
	 *          sequence of directions as a string.
	 * @return map which key is a direction and its value is the number of
	 *         occurrences in the path.
	 */
	private static Map<Character, Integer> getCharCount(String path) {
		Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		charCount.put(LEFT, 0);
		charCount.put(RIGHT, 0);
		charCount.put(UP, 0);
		charCount.put(DOWN, 0);

		for (Character character : path.toCharArray()) {
			Integer count = charCount.get(character);
			charCount.put(character, count + 1);
		}
		return charCount;
	}

	/**
	 * Gets the vertical direction with the most occurrences.
	 *
	 * @param charCount
	 *          map which key is a direction and its value is the number of
	 *          occurrences in the path.
	 * @return character with the vertical direction with the maximum number of
	 *         occurrences.
	 */
	private static Character getMaxVerticalDirection(Map<Character, Integer> charCount) {
		return charCount.get(UP) - charCount.get(DOWN) > 0 ? UP : DOWN;
	}

	/**
	 * Gets the horizontal direction with the most occurrences.
	 *
	 * @param charCount
	 *          map which key is a direction and its value is the number of
	 *          occurrences in the path.
	 * @return character with the horizontal direction with the maximum number of
	 *         occurrences.
	 */
	private static Character getMaxHorizontalDirection(Map<Character, Integer> charCount) {
		return charCount.get(LEFT) - charCount.get(RIGHT) > 0 ? LEFT : RIGHT;
	}

	/**
	 * Returns true if there is the same number of occurrences for each direction
	 * in the same axis.
	 *
	 * @param charCount
	 *          map which key is a direction and its value is the number of
	 *          occurrences in the path.
	 * @return true if both the amount of horizontal and vertical directions is
	 *         zero. False otherwise.
	 */
	private static boolean isCountZero(Map<Character, Integer> charCount) {
		return isHorizontalCountZero(charCount) && isVerticalCountZero(charCount);
	}

	/**
	 * Returns true if there is the same number of occurrences for each horizontal
	 * direction.
	 *
	 * @param charCount
	 *          map which key is a direction and its value is the number of
	 *          occurrences in the path.
	 * @return true if the amount of horizontal directions is zero. False
	 *         otherwise.
	 */
	private static boolean isHorizontalCountZero(Map<Character, Integer> charCount) {
		int horizontalCount = charCount.get(LEFT) - charCount.get(RIGHT);
		return horizontalCount == 0;
	}

	/**
	 * Returns true if there is the same number of occurrences for each vertical
	 * direction.
	 *
	 * @param charCount
	 *          map which key is a direction and its value is the number of
	 *          occurrences in the path.
	 * @return true if the amount of vertical directions is zero. False otherwise.
	 */
	private static boolean isVerticalCountZero(Map<Character, Integer> charCount) {
		int verticalCount = charCount.get(UP) - charCount.get(DOWN);
		return verticalCount == 0;
	}

}
