import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuVerifierTest {

//implement tests to test Sudokuverifier with boundary values.  Use templates from Task 1 to derive and document test cases.
	
// A correct Sudoku string: 417369825632158947958724316825437169791586432346912758289643571573291684164875293
// An incorrect Sudoku string: 123456789912345678891234567789123456678912345567891234456789123345678912234567891
String c = "417369825632158947958724316825437169791586432346912758289643571573291684164875293";
String i = "123456789912345678891234567789123456678912345567891234456789123345678912234567891";
String invalidCharSudoku = "41736982563215X947958724316825437169791586432346912758289643571573291684164875293"; // Contains 'X'
String shortSudoku = "41736982563215894795872431682543716979158643234691275828964357157329168416487";    // 80 characters
String longSudoku = "4173698256321589479587243168254371697915864323469127582896435715732916841648752935";  //82 characters
String zeroSudoku = "017369825632158947958724316825437169791586432346912758289643571573291684164875293"; // contain 0
String duplicateRowSudoku = "617369825432158947958724316825437169791586432346912758289643571573291684164875293"; // Duplicate in first,second row
String duplicateColSudoku = "147369825632158947958724316825437169791586432346912758289643571573291684164875293"; // Duplicate in first,second column
String duplicateGridSudoku = "417369825612158947958724316825437169791586432346912758289643571573291684164875293"; // Duplicate in first grid

SudokuVerifier v = new SudokuVerifier();

	@Test
	public void testCorrectString() {
		int a = v.verify(c);
		assertEquals("correct string", a, 0);
	}

	@Test
	public void testIncorrectString() {
		int a = v.verify(i);
		assertEquals("incorrect string", a, -2);
		
	}
	
	@Test
    public void testInvalidCharString() {
        int result = v.verify(invalidCharSudoku);
        assertEquals("Expected invalid character error", 1, result);
    }

    @Test
    public void testShortString() {
        int result = v.verify(shortSudoku);
        assertEquals("Expected R1 violation (length check)", -1, result);
    }
    
    @Test
    public void testLongString() {
        int result = v.verify(longSudoku);
        assertEquals("Expected R1 violation (length check)", -1, result);
    }
    
    @Test
    public void testZeroString() {
        int result = v.verify(zeroSudoku);
        assertEquals("Expected R1 violation (zero)", -1, result);
    }

    @Test
    public void testDuplicateRow() {
        int result = v.verify(duplicateRowSudoku);
        assertEquals("Expected R3 violation (row rule)", -3, result);
    }

    @Test
    public void testDuplicateColumn() {
        int result = v.verify(duplicateColSudoku);
        assertEquals("Expected R4 violation (column rule)", -4, result);
    }

    @Test
    public void testDuplicateSubGrid() {
        int result = v.verify(duplicateGridSudoku);
        assertEquals("Expected R2 violation (sub-grid rule)", -2, result);
    }

    @Test
    public void testSpecialUnicodeCharacter() {
        String specialUnicodeSudoku = c.substring(0, 80) + "\u2603"; // Adding a snowman character at the end
        int result = v.verify(specialUnicodeSudoku);
        assertEquals("Expected invalid character error for Unicode", 1, result);
    }
	
}
