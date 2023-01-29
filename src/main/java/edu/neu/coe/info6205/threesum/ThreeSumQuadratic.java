package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.*;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++){
            triples.addAll(getTriples(i));
        }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.
        int leftPointer = j-1;
        int rightPointer = j+1;
        while (leftPointer >= 0 && rightPointer <= length-1) {
            int total = a[j] + a[leftPointer] + a[rightPointer];
            if (total == 0) {
                triples.add(new Triple(a[j], a[leftPointer], a[rightPointer]));
                leftPointer--;
                rightPointer++;
            } else if (total < 0) {
                rightPointer++;
            } else {
                leftPointer--;
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
