package org.graphstream.algorithm;

import java.util.stream.Stream;
import junit.framework.TestCase;
import org.graphstream.graph.Edge;
import org.junit.Test;

public class AbstractSpanningTreeTest extends TestCase {

    @Test
    public void testGetters() {
        AbstractSpanningTree ast = new AbstractSpanningTree() {
            @Override
            protected void makeTree() {}

            @Override
            public Stream<Edge> getTreeEdgesStream() {
                return null;
            }
        };

        assertNull(ast.getFlagAttribute());
        assertNull(ast.getFlagOff());
        assertNull(ast.getFlagOn());
    }

}