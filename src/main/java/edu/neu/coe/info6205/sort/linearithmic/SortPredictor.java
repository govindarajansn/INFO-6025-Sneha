package edu.neu.coe.info6205.sort.linearithmic;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.StatPack;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class SortPredictor {

    private static Integer [] generateRandomArray(int n){
        int[] random = IntStream.range(0, n).map(j -> new Random().nextInt(n)).toArray();
        return IntStream.of( random ).boxed().toArray( Integer[]::new );
    }

    public static double runBenchMarkMerge(String description, int n,  Function<Integer, Integer[]> generateArray) {
        Supplier<Integer[]> arraySupplier = () ->  generateArray.apply(n);

        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        BaseHelper<Integer> helper = new InstrumentedHelper<>("Quicksort Helper", config);

        PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);

        double benchMarkTime = new Benchmark_Timer<Integer[]>(
                description,
                null,
                (xs) -> {
                    helper.postProcess(new MergeSortBasic<>(helper).sort(xs));
                },
                null
        ).runFromSupplier(arraySupplier, 1);

        StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        System.out.println("Number of Compares " + (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        System.out.println("Number of Swaps "  + (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean());
        System.out.println("Number of Hits "  + (int) statPack.getStatistics(InstrumentedHelper.HITS).mean());
        System.out.println("Bench Mark time "  + benchMarkTime);

        return benchMarkTime;
    }

    public static double runBenchMarkQuick(String description, int n,  Function<Integer, Integer[]> generateArray) {
        Supplier<Integer[]> arraySupplier = () ->  generateArray.apply(n);

        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        BaseHelper<Integer> helper = new InstrumentedHelper<>("Quicksort Helper", config);

        PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);

        double benchMarkTime = new Benchmark_Timer<Integer[]>(
                description,
                null,
                (xs) -> {
                    helper.postProcess(new QuickSort_DualPivot<>(helper).sort(xs));
                },
                null
        ).runFromSupplier(arraySupplier, 1);

        StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        System.out.println("Number of Compares " + (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        System.out.println("Number of Swaps "  + (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean());
        System.out.println("Number of Hits "  + (int) statPack.getStatistics(InstrumentedHelper.HITS).mean());
        System.out.println("Bench Mark time "  + benchMarkTime);

        return benchMarkTime;
    }

    public static double runBenchMarkHeap(String description, int n,  Function<Integer, Integer[]> generateArray) {
        Supplier<Integer[]> arraySupplier = () ->  generateArray.apply(n);

        final Config config = Config.setupConfig("true", "0", "1", "1", "");
        BaseHelper<Integer> helper = new InstrumentedHelper<>("Quicksort Helper", config);

        PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);

        double benchMarkTime = new Benchmark_Timer<Integer[]>(
                description,
                null,
                (xs) -> {
                    helper.postProcess(new HeapSort<>(helper).sort(xs));
                },
                null
        ).runFromSupplier(arraySupplier, 1);

        StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        System.out.println("Number of Compares " + (int) statPack.getStatistics(InstrumentedHelper.COMPARES).mean());
        System.out.println("Number of Swaps "  + (int) statPack.getStatistics(InstrumentedHelper.SWAPS).mean());
        System.out.println("Number of Hits "  + (int) statPack.getStatistics(InstrumentedHelper.HITS).mean());
        System.out.println("Bench Mark time "  + benchMarkTime);

        return benchMarkTime;
    }

    public static void main(String args[]) throws  IOException {
        int [] arr = {10000, 20000, 40000, 80000 , 160000 };
        for(int i=0;i< arr.length;i++){
            runBenchMarkMerge("Merge sort with Random Array with "+ arr[i] + " elements ", arr[i], (size) -> generateRandomArray(size));
            runBenchMarkQuick("Quick sort with Random Array with "+ arr[i] + " elements ", arr[i], (size) -> generateRandomArray(size));
            runBenchMarkHeap("Heap sort with Random Array with "+ arr[i] + " elements ", arr[i], (size) -> generateRandomArray(size));
        }
    }

}

