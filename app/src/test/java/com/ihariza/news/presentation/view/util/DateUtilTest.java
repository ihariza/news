package com.ihariza.news.presentation.view.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

public class DateUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = InvocationTargetException.class)
    public void instantiateClassShouldThrowIllegalStateException()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<DateUtil> constructor = DateUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void givenyyyyMMddTHHmmss0000FormatShouldReturnsddMMyyyyHHmmhFormat() {
        String result = DateUtil.formatTimestampIntoDate("2018-07-11 22:49:24 +0000");
        assert(result.equals("11-07-2018 22:49h"));
    }

    @Test
    public void givenBadFormatShouldReturnsNull() {
        String result = DateUtil.formatTimestampIntoDate("2018-07-11 22:49:24");
        assert(result == null);
    }
}
