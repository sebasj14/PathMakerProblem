package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.PathMakerProblem;

public class PathMakerProblemTest {

	@Test
	public void testEmptyPath() {
		int expectedResult = 0;
		int actualResult = PathMakerProblem.makePath("");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSimplePath() {
		int expectedResult = 0;
		int actualResult = PathMakerProblem.makePath("LURD");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSimpleInvalidPath() {
		int expectedResult = -1;
		int actualResult = PathMakerProblem.makePath("LLURD");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testMediumBackToStartPath() {
		int expectedResult = 0;
		int actualResult = PathMakerProblem.makePath("LRURDLDLURDU");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testMediumJustLeftPath() {
		int expectedResult = 9;
		int actualResult = PathMakerProblem.makePath("LLLLLLLLLLLL");
		assertEquals(expectedResult, actualResult);
	}

}
