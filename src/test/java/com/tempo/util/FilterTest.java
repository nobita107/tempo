package com.tempo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    @Test
    public void testFilter() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                //         x  x     x  x     x  x     x   x
                new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                new int[] {0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> nodeId % 3 != 0);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(
                new int[] {1, 2, 5, 8, 10, 11},
                new int[] {0, 1, 1, 0, 1, 2}
        );

        assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }
}