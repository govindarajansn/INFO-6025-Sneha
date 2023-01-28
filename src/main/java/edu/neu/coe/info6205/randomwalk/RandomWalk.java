/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;


import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.*;



public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();


    RandomWalk walk ;

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */

    /*
      if x=1 => person moves in east direction
      if x=-1 => person moves in west direction
      if y=1 => person moves in north direction
      if y=-1 => person moves in south direction
     */
    private void move(int dx, int dy) {
        // FIXME do move by replacing the following code
        //System.out.println(" move ");
         x = x + dx;
         y = y + dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // FIXME
        for(int i = 0; i < m; i++){
            randomMove();
        }
        // END 
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
      //  System.out.println(ns+"  "+step);
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */

    //provides the distance of the person from the lamp-post
    public double distance() {
        //System.out.println(x + " " +y);
        //FIXME by replacing the following code
         Double dist = Math.sqrt(y*y + x*x);
         //System.out.println(dist);
         return dist;
        // END 
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n){
        double totalDistance = 0;

        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
            System.out.println("Experiment Number: "+n+" Steps Taken: "+m+" Distance Covered: "+walk.distance());
        }
        //System.out.println(dist_arr);
//        System.out.println(m+ "Experiment and mean is "+totalDistance / n);
        return totalDistance / n;
    }

    public static void main(String[] args) {
        //System.out.println(args[0]+" vcd "+args[1]);
//        if (args.length == 0)
//            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int[] m = new int[]{ 8, 10, 12, 20, 23, 30, 37, 45, 50, 60 };
        int n = 20;
        List<Double> dist_arr = new ArrayList<Double>();
        List<Double> sq_arr = new ArrayList<Double>();
       // Arrays.sort(m);
        if (args.length > 1) n = Integer.parseInt(args[1]);
        for(int i=0;i<m.length;i++) {
            double meanDistance = randomWalkMulti(m[i], n);
            dist_arr.add(meanDistance);
            System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
        }
        for(int i=0;i<m.length;i++) {

            sq_arr.add(Math.sqrt(m[i]));
            //System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
        }


        System.out.println(Arrays.toString(m));
        System.out.println(dist_arr);
        System.out.println(sq_arr);
       // System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
    }

}
