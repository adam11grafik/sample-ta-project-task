package com.taf.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyClassJunit4Test {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getText() {
        Assert.assertEquals(new DummyClass().getText(), "text");
    }
}