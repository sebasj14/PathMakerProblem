package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.PathMakerProblem;

public class PathMakerProblemTest {

	@Test
	public void testEmptyPath() {
		int expectedResult = 0;
		int actualResult = PathMakerProblem.getNumberOfChanges("");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSimplePath() {
		int expectedResult = 0;
		int actualResult = PathMakerProblem.getNumberOfChanges("LURD");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSimpleInvalidPath() {
		int expectedResult = -1;
		int actualResult = PathMakerProblem.getNumberOfChanges("LLURD");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testMediumBackToStartPath() {
		int expectedResult = 0;
		int actualResult = PathMakerProblem.getNumberOfChanges("LRURDLDLURDU");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testSimpleJustLeftPath() {
		int expectedResult = 1;
		int actualResult = PathMakerProblem.getNumberOfChanges("LL");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testMediumInvalidPath() {
		int expectedResult = -1;
		int actualResult = PathMakerProblem.getNumberOfChanges("LLRURURUDLL");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testMediumPath() {
		int expectedResult = 2;
		int actualResult = PathMakerProblem.getNumberOfChanges("LRDDURDLDLURDLLU");
		assertEquals(expectedResult, actualResult);
	}

}
