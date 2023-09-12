package org.graphstream.algorithm.generator;

import junit.framework.TestCase;
import org.junit.Test;

public class URLGeneratorTest extends TestCase {

    @Test
    public void test() {
        URLGenerator urlGen = new URLGenerator("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");
        urlGen.setMode(URLGenerator.Mode.HOST);
        urlGen.setDepthLimit(100);
        urlGen.addHostFilter("google", "schmoogle", "poogle", "abc", "xyz");
        assertTrue(urlGen.isValid("abc"));
        assertTrue(urlGen.isValid("https://google.de"));
        assertTrue(urlGen.isValid("https://poogle.de"));

    }

}