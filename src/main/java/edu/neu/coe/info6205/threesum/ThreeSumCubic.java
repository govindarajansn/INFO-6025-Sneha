package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the brute-force approach of
 * testing every candidate in the solution-space.
 * The array provided in the constructor may be randomly ordered.
 * <p>
 * This algorithm runs in O(N^3) time.
 */
class ThreeSumCubic implements ThreeSum {
    /**
     * Construct a ThreeSumCubic on a.
     * @param a an array.
     */
    public ThreeSumCubic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                        triples.add(new Triple(a[i], a[j], a[k]));
                }
            }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    public static void main(String[] args) {
        int size = 100;
        int n = 5;
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
            System.out.println("Elapsed time: " + time + "msecs");
            i++;
            size=size*2;
        }
    }

    private final int[] a;
    private final int length;
}
