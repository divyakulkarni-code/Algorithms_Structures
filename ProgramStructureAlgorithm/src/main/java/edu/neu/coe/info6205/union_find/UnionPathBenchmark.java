package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

public class UnionPathBenchmark{

    public static void main(String[] args){
        UnionFindClient client = new UnionFindClient ();


        Benchmark<Boolean> bm1 = new Benchmark_Timer<> (
                "testWaitPeriods",
                b -> {
                    client.wquByDepth ();
                }
        );

        Benchmark<Boolean> bm4 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    client.wquPathCompression ();
                }
        );


        for  ( int i = 10; i<= 320; i = i*2) {
            double xOrdered = bm1.run(true, i);
            System.out.println("Weighted Quick Union by Depth "+ i +" runs: " + xOrdered);

            double xReverse = bm4.run(true, i);
            System.out.println("With Path Compression "+ i +" runs: " + xReverse);
        }

    }
}


