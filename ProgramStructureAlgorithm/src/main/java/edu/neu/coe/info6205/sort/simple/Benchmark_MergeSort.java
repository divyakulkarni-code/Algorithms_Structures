package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.Timer;
import org.ini4j.Ini;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Benchmark_MergeSort {

    public static void main(String[] args){

        //Partially Sorted Array
        Integer [] xsPartiallyOrdered = new Integer[5000];    // Almost sorted Array of 100

        xsPartiallyOrdered[0] = (int)(Math.random () * 10) + 1;

        for (int a = 1; a < xsPartiallyOrdered.length; a++) {
            xsPartiallyOrdered[a] = xsPartiallyOrdered[a - 1] + (int) (Math.random () * 12) - 2;
        }

        Integer[] xsPartiallyOrderedOffOn = xsPartiallyOrdered.clone ();
        Integer[] xsPartiallyOrderedOnOn = xsPartiallyOrdered.clone ();
        Integer[] xsPartiallyOrderedOffOff = xsPartiallyOrdered.clone ();


        int k = 7;
        int N = (int) Math.pow(2, k);
        Benchmark_MergeSort BMSort = new Benchmark_MergeSort ();
        final Config config = BMSort.setupConfig("true", "0", "1", "", "", false, false);
        final Helper<Integer> helper = HelperFactory.create("merge sort", N, config);
        //MergeSortBasic mergeSort = new MergeSortBasic (helper, false, false);



        // Ordered array with
        // Insurance: true
        // NoCopy: false
        Benchmark<Boolean> bm1 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    MergeSortBasic mergeSort = new MergeSortBasic (helper, true, false);
                    mergeSort.sortWithNoCopyAndInsurance (xsPartiallyOrdered);
                }
        );

        // Ordered array with
        // Insurance: false
        // NoCopy: true
        Benchmark<Boolean> bm2 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    MergeSortBasic mergeSort = new MergeSortBasic (helper, false, true);
                    mergeSort.sortWithNoCopyAndInsurance(xsPartiallyOrderedOffOn);
                }
        );

        // Ordered array with
        // Insurance: false
        // NoCopy: false
        Benchmark<Boolean> bm3 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    MergeSortBasic mergeSort = new MergeSortBasic (helper, false, false);
                    mergeSort.sortWithNoCopyAndInsurance(xsPartiallyOrderedOffOff);
                }
        );

        // Ordered array with
        // Insurance: true
        // NoCopy: true
        Benchmark<Boolean> bm4 = new Benchmark_Timer<>(
                "testWaitPeriods",
                b -> {
                    MergeSortBasic mergeSort = new MergeSortBasic (helper, true, true);
                    mergeSort.sortWithNoCopyAndInsurance(xsPartiallyOrderedOnOn);
                }
        );


        for  ( int i = 1; i<= 1000; i++) {
            double a = bm1.run(true, i);
            System.out.println(" "+ i +" : " +" T/F " + a);

            double b = bm2.run(true, i);
            System.out.println(" "+ i +" : " + " F/T " + b);

            double c = bm3.run (true,i);
            System.out.println (" "+ i +" : " + " F/F " + c);

            double d = bm4.run (true,i);
            System.out.println (" "+ i +" : "+ " T/T " + d);
        }

    }
    public static Config setupConfig(final String instrumenting, final String seed, final String inversions, String cutoff, String interimInversions, boolean insuranceOnOff, boolean noCopyOnOff) {
        final Ini ini = new Ini();
        final String sInstrumenting = INSTRUMENTING;
        ini.put(Config.HELPER, Config.INSTRUMENT, instrumenting);
        ini.put(Config.HELPER, SEED, seed);
        ini.put(Config.HELPER, CUTOFF, cutoff);
        ini.put(Config.HELPER, INSURANCE, insuranceOnOff);
        ini.put(Config.HELPER, NOCOPY, noCopyOnOff);
        ini.put(sInstrumenting, INVERSIONS, inversions);
        ini.put(sInstrumenting, INVERSIONS, inversions);
        ini.put(sInstrumenting, SWAPS, instrumenting);
        ini.put(sInstrumenting, COMPARES, instrumenting);
        ini.put(sInstrumenting, COPIES, instrumenting);
        ini.put(sInstrumenting, FIXES, instrumenting);
        ini.put("huskyhelper", "countinteriminversions", interimInversions);
        return new Config(ini);
    }

    public static final String TRUE = "true";
    public static final String FALSE = "";
    public static final String INSTRUMENTING = InstrumentedHelper.INSTRUMENTING;
    public static final String INVERSIONS = InstrumentedHelper.INVERSIONS;
    public static final String SEED = "seed";
    public static final String CUTOFF = "cutoff";
    public static final String INSURANCE = "insuranceOnOff";
    public static final String NOCOPY = "noCopyOnOff";
    public static final String SWAPS = InstrumentedHelper.SWAPS;
    public static final String COMPARES = InstrumentedHelper.COMPARES;
    public static final String COPIES = InstrumentedHelper.COPIES;
    public static final String FIXES = InstrumentedHelper.FIXES;
}


