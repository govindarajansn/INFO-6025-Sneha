package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadraticWithCalipers implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     *
     * @param a a sorted array.
     */
    public ThreeSumQuadraticWithCalipers(int[] a) {
        this.a = a;
        length = a.length;
    }

    /**
     * Get an array or Triple containing all of those triples for which sum is zero.
     *
     * @return a Triple[].
     */
    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        Collections.sort(triples); // ???
        for (int i = 0; i < length - 2; i++) {
            triples.addAll(calipers(a, i, Triple::sum));
        }
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a set of candidate Triples such that the first index is the given value i.
     * Any candidate triple is added to the result if it yields zero when passed into function.
     *
     * @param a        a sorted array of ints.
     * @param i        the index of the first element of resulting triples.
     * @param function a function which takes a triple and returns a value which will be compared with zero.
     * @return a List of Triples.
     */
    public static List<Triple> calipers(int[] a, int i, Function<Triple, Integer> function) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : use function to qualify triples and to navigate otherwise.
        int leftPionter = i + 1;
        int rightPointer = a.length - 1;
        while (leftPionter < rightPointer) {
            if (function.apply(new Triple(a[i], a[leftPionter], a[rightPointer])) == 0) {
                triples.add(new Triple(a[i], a[leftPionter], a[rightPointer]));
            }
            if(function.apply(new Triple(a[i], a[leftPionter], a[rightPointer])) > 0) {
                rightPointer--;
            } else {
                leftPionter++;
            }
        }
        // END 
        return triples;
    }
    public static void main(String[] args) {
        int size = 70;
        int n = 6;
        for(int i=0;i<n;i++){
            Supplier<int[]> intsSupplier = new Source(size, size, 3L).intsSupplier(10);
            int[] ints = intsSupplier.get();
            System.out.println(Arrays.toString(ints));
            ThreeSumQuadraticWithCalipers target = new ThreeSumQuadraticWithCalipers(ints);
            Stopwatch stopwatch = new Stopwatch();
            Triple[] triples = target.getTriples();
            long time = stopwatch.lap();
            System.out.println("Array Size "+ints.length);
            System.out.println("Triplet size "+triples.length);
            System.out.println("Elapsed time: " + time + "ms");
            size=size*2;
        }
    }

    private final int[] a;
    private final int length;
}
