package com.tempo.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    @Test
    @DisplayName("Always FALSE predicate, filtered must be empty")
    public void testFilterAll() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                //         x  x     x  x     x  x     x   x
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                new int[]{0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> false);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(new int[]{}, new int[]{});

        assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }

    @Test
    @DisplayName("Always true predicate, filtered must match original")
    public void testFilterNothing() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                //         x  x     x  x     x  x     x   x
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                new int[]{0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> true);

        assertEquals(unfiltered.formatString(), filteredActual.formatString());
    }

    @Test
    public void testFilter() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                //         x  x     x  x     x  x     x   x
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                new int[]{0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> nodeId % 3 != 0);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(
                new int[]{1, 2, 5, 8, 10, 11},
                new int[]{0, 1, 1, 0, 1, 2}
        );

        assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }

    @Test
    @DisplayName("Empty input - Should return empty output")
    public void testFilterEmptyInput() {
        Hierarchy emptyHierarchy = new ArrayBasedHierarchy(
                //         x  x     x  x     x  x     x   x
                new int[]{},
                new int[]{}
        );
        Hierarchy filteredActual = Filter.filter(emptyHierarchy, nodeId -> nodeId % 3 != 0);
        assertEquals(emptyHierarchy.formatString(), filteredActual.formatString());
    }
}