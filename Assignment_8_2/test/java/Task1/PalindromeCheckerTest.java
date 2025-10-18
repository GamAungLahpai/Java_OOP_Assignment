package Task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PalindromeChecker using JUnit 5
 * Following TDD approach
 */
class PalindromeCheckerTest {

    @Test
    void testSimplePalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("radar"),
                "'radar' should be a palindrome");
    }

    @Test
    void testPalindromeWithSpacesAndPunctuation() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"),
                "'A man, a plan, a canal, Panama' should be a palindrome");
    }

    @Test
    void testNonPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("hello"),
                "'hello' should not be a palindrome");
    }

    @Test
    void testAnotherNonPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("openai"),
                "'openai' should not be a palindrome");
    }

    @Test
    void testSingleCharacter() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("a"),
                "Single character should be a palindrome");
    }

    @Test
    void testEmptyString() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(""),
                "Empty string should be considered a palindrome");
    }

    @Test
    void testPalindromeWithMixedCase() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("RaceCar"),
                "'RaceCar' should be a palindrome (case insensitive)");
    }

    @Test
    void testPalindromeWithNumbers() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("12321"),
                "'12321' should be a palindrome");
    }

    @Test
    void testPalindromeWithSpaces() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("race car"),
                "'race car' should be a palindrome");
    }

    @Test
    void testComplexPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("Was it a car or a cat I saw?"),
                "Should handle complex palindrome");
    }

    @Test
    void testNonPalindromeWithSpaces() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("not a palindrome"),
                "'not a palindrome' should not be a palindrome");
    }

    @Test
    void testNullString() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome(null),
                "Null string should return false");
    }
}