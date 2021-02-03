package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Benchmark_InsertionSort {

    public static void main(String[] args){
        final List<Integer> listOrdered = new ArrayList<>();
        for(int i=0; i< 5000; i++) {
            listOrdered.add(i);
        }
        Integer[] xsOrdered = listOrdered.toArray(new Integer[0]);

        final List<Integer> listReverse = new ArrayList<>();

        for(int i=5000; i >0 ; i--) {
            listReverse.add(i);
        }
        Integer[] xsReverse = listReverse.toArray(new Integer[0]);

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(xsOrdered,0, xsOrdered.length );

        Benchmark<Boolean> bm1 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    insertionSort.sort(xsOrdered,0, xsOrdered.length );
                }
                );

        Benchmark<Boolean> bm4 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    insertionSort.sort(xsReverse,0, xsReverse.length  );
                }
        );


        for  ( int i = 10; i<= 320; i = i*2) {
             double xOrdered = bm1.run(true, i);
             System.out.println("xOrdered for "+ i +" runs: " + xOrdered);

             double xReverse = bm4.run(true, i);
             System.out.println("xReverse for "+ i +" runs: " + xReverse);
        }

    }
}


