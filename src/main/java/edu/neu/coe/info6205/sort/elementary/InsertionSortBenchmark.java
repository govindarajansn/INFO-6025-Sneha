package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class InsertionSortBenchmark {

    private static Integer [] generatePartiallyOrderedArray(int n){
        int[] partiallyOrdered = IntStream.concat(IntStream.range(0, n / 2), IntStream.range(0, n / 2).map(j ->  new Random().nextInt()).sorted()).toArray();
//        System.out.println(partiallyOrdered.toString());
        return IntStream.of( partiallyOrdered ).boxed().toArray( Integer[]::new );

    }

    private static Integer [] generateRandomArray(int n){
        int[] random = IntStream.range(0, n).map(j -> new Random().nextInt()).toArray();
//        System.out.println(random.toString());
        return IntStream.of( random ).boxed().toArray( Integer[]::new );
    }

    private static Integer [] generateOrderedArray(int n){
        int[] ordered = IntStream.range(0, n).toArray();
//        System.out.println(ordered);
        return IntStream.of( ordered ).boxed().toArray( Integer[]::new );
    }

    private static Integer [] generateReverseOrderedArray(int n){
        int[] reverseOrdered = IntStream.range(0, n).map(j -> n - j - 1).toArray();
//        System.out.println(reverseOrdered.toString());
        return IntStream.of( reverseOrdered ).boxed().toArray( Integer[]::new );
    }

    public static void sortArray(Integer[] arr, int n){
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        Integer[] ys = sorter.sort(arr);
    }

    public static void main(String args[]){
        int n= 400;

            Consumer<Integer[]> randomFunc = randArr -> sortArray(randArr, n);
            Benchmark_Timer<Integer[]> randomTimer = new Benchmark_Timer<>("Random order array", randomFunc);
            Supplier<Integer[]> random = () -> generateRandomArray(n);
            randomFunc.accept(random.get());
            double randTime = randomTimer.run(random.get(), 100);
            System.out.println("Time for random array " + n + " element is " + randTime);
//            randomList.add(randTime);

            Consumer<Integer[]> orderedFunc = orderArr -> sortArray(orderArr, n);
            Benchmark_Timer<Integer[]> orderTimer = new Benchmark_Timer<>("Ordered array", orderedFunc);
            Supplier<Integer[]> order = () -> generateOrderedArray(n);
            randomFunc.accept(order.get());
            double orderTime = orderTimer.run(order.get(), 100);
            System.out.println("Time for Ordered array " + n + " element is " + orderTime);
//            orderedList.add(orderTime);

            Consumer<Integer[]> reverseFunc = reverseArr -> sortArray(reverseArr, n);
            Benchmark_Timer<Integer[]> reverseTimer = new Benchmark_Timer<>("Reverse Order array", reverseFunc);
            Supplier<Integer[]> reverse = () -> generateReverseOrderedArray(n);
            reverseFunc.accept(reverse.get());
            double reverseTime = reverseTimer.run(reverse.get(), 100);
            System.out.println("Time for Reverse Order array " + n + " element is " + reverseTime);
//            reverseList.add(reverseTime);

            Consumer<Integer[]> partialFunc = partialArr -> sortArray(partialArr, n);
            Benchmark_Timer<Integer[]> partialTimer = new Benchmark_Timer<>("Partial Order array", partialFunc);
            Supplier<Integer[]> partial = () -> generatePartiallyOrderedArray(n);
            partialFunc.accept(partial.get());
            double partialTime = partialTimer.run(partial.get(), 100);
            System.out.println("Time for Partial Order array " + n + " element is " + partialTime);

    }

}
