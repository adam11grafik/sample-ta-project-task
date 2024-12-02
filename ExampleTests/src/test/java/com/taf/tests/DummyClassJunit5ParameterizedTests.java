package com.taf.tests;

import com.agraf.sample.groups.TestTypeTags;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.stream.Stream;

public class DummyClassJunit5ParameterizedTests {

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Tag(TestTypeTags.Regression)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void getTextWithValueSource(int number) {
        Assertions.assertEquals("text", new DummyClass().getText(), "Value not as expected for number: " + number);
    }


    @Tag(TestTypeTags.Regression)
    @ParameterizedTest
    @EnumSource(Month.class)
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        Assertions.assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    @Tag(TestTypeTags.Sanity)
    @Tag(TestTypeTags.Regression)
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
        Assertions.assertEquals(expected, input.isBlank());
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }
}