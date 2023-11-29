package com.tempo.util;

import org.junit.jupiter.api.Test;

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>A <tt>Hierarchy</tt> stores an arbitrary <em>forest</em> (an ordered collection of ordered trees)
 * as an array indexed by DFS-order traversal.
 * A node is represented by a unique ID.
 * Parent-child relationships are identified by the position in the array and the associated depth.
 * Tree root has depth 0, immediate children have depth 1, their children have depth 2, etc.
 * </p>
 *
 * <p>Depth of the first element is 0. If the depth of a node is D, the depth of the next node in the array can be:</p>
 * <ul>
 *   <li>D + 1 if the next node is a child of this node;</li>
 *   <li>D if the next node is a sibling of this node;</li>
 *   <li>d < D - in this case the next node is not related to this node.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <code>
 * nodeIds: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * depths: 0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2
 * the forest can be visualized as follows:
 * 1
 * - 2
 * - - 3
 * - - - 4
 * - 5
 * 6
 * - 7
 * 8
 * - 9
 * - 10
 * - - 11
 * </code>
 * Note that the depth is equal to the number of hyphens for each node.
 * */
interface Hierarchy {
    int size();

    int nodeId(int index);

    int depth(int index);

    default String formatString() {
        return IntStream.range(0, size()).mapToObj(i -> "" + nodeId(i) + ":" + depth(i) ).collect(Collectors.joining(", ", "[", "]"));
    }
}

class Filter {
    /**
     * A node is present in the filtered hierarchy iff its node ID passes the predicate and all of its ancestors pass it as well.
     * */
    static Hierarchy filter(Hierarchy hierarchy, IntPredicate nodeIdPredicate) {
        // todo implement
        return new ArrayBasedHierarchy(new int[0], new int[0]);
    }
}

class ArrayBasedHierarchy implements Hierarchy {
    private final int[] myNodeIds;
    private final int[] myDepths;

    public ArrayBasedHierarchy(int[] nodeIds, int[] depths) {
        myNodeIds = nodeIds;
        myDepths = depths;
    }

    @Override
    public int size() {
        return myDepths.length;
    }

    @Override
    public int nodeId(int index) {
        return myNodeIds[index];
    }

    @Override
    public int depth(int index) {
        return myDepths[index];
    }
}
