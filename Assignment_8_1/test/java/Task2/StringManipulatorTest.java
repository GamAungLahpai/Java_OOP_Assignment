package Task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Assignment 8.1 - Task 2
 * Test class for StringManipulator
 * Following TDD: Write tests first, then implement the class
 */
class StringManipulatorTest {

    private StringManipulator manipulator;

    @BeforeEach
    void setUp() {
        manipulator = new StringManipulator();
    }

    // Tests for concatenate method
    @Test
    void testConcatenateBasic() {
        String result = manipulator.concatenate("Hello", "World");
        assertEquals("HelloWorld", result, "Should concatenate two strings");
    }

    @Test
    void testConcatenateWithSpace() {
        String result = manipulator.concatenate("Hello ", "World");
        assertEquals("Hello World", result, "Should preserve spaces in concatenation");
    }

    @Test
    void testConcatenateEmptyStrings() {
        String result = manipulator.concatenate("", "");
        assertEquals("", result, "Concatenating empty strings should return empty");
    }

    @Test
    void testConcatenateOneEmpty() {
        String result = manipulator.concatenate("Hello", "");
        assertEquals("Hello", result, "Should handle one empty string");
    }

    // Tests for findLength method
    @Test
    void testFindLengthBasic() {
        int length = manipulator.findLength("Hello");
        assertEquals(5, length, "Should return correct length");
    }

    @Test
    void testFindLengthEmpty() {
        int length = manipulator.findLength("");
        assertEquals(0, length, "Empty string should have length 0");
    }

    @Test
    void testFindLengthWithSpaces() {
        int length = manipulator.findLength("Hello World");
        assertEquals(11, length, "Should count spaces in length");
    }

    // Tests for convertToUpperCase method
    @Test
    void testConvertToUpperCaseBasic() {
        String result = manipulator.convertToUpperCase("hello");
        assertEquals("HELLO", result, "Should convert to uppercase");
    }

    @Test
    void testConvertToUpperCaseMixed() {
        String result = manipulator.convertToUpperCase("HeLLo WoRLd");
        assertEquals("HELLO WORLD", result, "Should convert mixed case to uppercase");
    }

    @Test
    void testConvertToUpperCaseAlreadyUpper() {
        String result = manipulator.convertToUpperCase("HELLO");
        assertEquals("HELLO", result, "Should handle already uppercase strings");
    }

    @Test
    void testConvertToUpperCaseEmpty() {
        String result = manipulator.convertToUpperCase("");
        assertEquals("", result, "Empty string should remain empty");
    }

    // Tests for convertToLowerCase method
    @Test
    void testConvertToLowerCaseBasic() {
        String result = manipulator.convertToLowerCase("HELLO");
        assertEquals("hello", result, "Should convert to lowercase");
    }

    @Test
    void testConvertToLowerCaseMixed() {
        String result = manipulator.convertToLowerCase("HeLLo WoRLd");
        assertEquals("hello world", result, "Should convert mixed case to lowercase");
    }

    @Test
    void testConvertToLowerCaseAlreadyLower() {
        String result = manipulator.convertToLowerCase("hello");
        assertEquals("hello", result, "Should handle already lowercase strings");
    }

    @Test
    void testConvertToLowerCaseEmpty() {
        String result = manipulator.convertToLowerCase("");
        assertEquals("", result, "Empty string should remain empty");
    }

    // Tests for containsSubstring method
    @Test
    void testContainsSubstringTrue() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result, "Should find substring 'World'");
    }

    @Test
    void testContainsSubstringFalse() {
        boolean result = manipulator.containsSubstring("Hello World", "Java");
        assertFalse(result, "Should not find substring 'Java'");
    }

    @Test
    void testContainsSubstringCaseSensitive() {
        boolean result = manipulator.containsSubstring("Hello World", "world");
        assertFalse(result, "Should be case sensitive - 'world' != 'World'");
    }

    @Test
    void testContainsSubstringEmptySubstring() {
        boolean result = manipulator.containsSubstring("Hello", "");
        assertTrue(result, "Empty substring should always be found");
    }

    @Test
    void testContainsSubstringAtStart() {
        boolean result = manipulator.containsSubstring("Hello World", "Hello");
        assertTrue(result, "Should find substring at start");
    }

    @Test
    void testContainsSubstringAtEnd() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result, "Should find substring at end");
    }

    @Test
    void testContainsSubstringInMiddle() {
        boolean result = manipulator.containsSubstring("Hello World", "lo Wo");
        assertTrue(result, "Should find substring in middle");
    }

    // Integration tests - combining multiple methods
    @Test
    void testCombinedOperations() {
        String concat = manipulator.concatenate("Hello", "World");
        String upper = manipulator.convertToUpperCase(concat);
        assertEquals("HELLOWORLD", upper);

        int length = manipulator.findLength(upper);
        assertEquals(10, length);

        boolean contains = manipulator.containsSubstring(upper, "WORLD");
        assertTrue(contains);
    }
}