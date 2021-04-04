package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;

public class MergeSortBasic<X extends Comparable<X>> extends SortWithHelper<X> {

    public static final String DESCRIPTION = "MergeSort";
    private String INSURANCE;
    private boolean insurance = false ;
    private boolean noCopy = false;

    /**
     * Constructor for MergeSort
     * <p>
     * NOTE this is used only by unit tests, using its own instrumented helper.
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public MergeSortBasic(Helper<X> helper, boolean insurance, boolean noCopy) {
        super(helper);
        insertionSort = new InsertionSort<>(helper);
        this.insurance = insurance;
        this.noCopy = noCopy;
    }

    /**
     * Constructor for MergeSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public MergeSortBasic(int N, Config config) {
        super(DESCRIPTION, N, config);

        this.insurance = config.getBoolean ("helper","insuranceOnOff");
        System.out.println ("Insurance: " + this.insurance);
        this.noCopy = config.getBoolean ("helper","noCopyOnOff");
        System.out.println ("No Copy: " + this.noCopy);
        insertionSort = new InsertionSort<>(getHelper());
    }

    @Override
    public X[] sort(X[] xs, boolean makeCopy) {
        // called sortWithNoCopyAndInsurance just for the purpose of Benchmarking and comment out others
        return sortWithNoCopyAndInsurance(xs);

//        getHelper().init(xs.length);
//        X[] result = makeCopy ? Arrays.copyOf(xs, xs.length) : xs;
//        // TODO don't copy but just allocate according to the xs/aux interchange optimization
//        aux = Arrays.copyOf(xs, xs.length);
//        sort(result, 0, result.length);
//        return result;
    }

    @Override
    public void sort(X[] a, int from, int to) {
        // called just for the purpose of Benchmarking and comment out others
        sortWithNoCopyAndInsurance(a);

//        @SuppressWarnings("UnnecessaryLocalVariable") int lo = from;
//        if (to <= lo + getHelper().cutoff()) {
//            insertionSort.sort(a, from, to);
//            return;
//        }
//        int mid = from + (to - from) / 2;
//        sort(a, lo, mid);
//        sort(a, mid, to);
//        System.arraycopy(a, from, aux, from, to - from);
//        getHelper().incrementCopies(to - from);
//        merge(aux, a, lo, mid, to);
    }

    // "noCopy": Third Improvement to avoid copy of original array into aux array.
    //           aux array is declared but original elements are NOT copied over
    //           roles of aux and original array are interchanged.
    // "Insurance": Before calling merge on two arrays check if the largest element of the Left
    //              array is smaller than the smallest element of the Right array, then simply return
    //              without performing any merge operation.

     /** allocate space for aux array but do NOT copy anything in it
     *If noCopy is set to 'true' then aux->a (just reference allocation)
     *If noCopy is set to 'false' then aux = a (aux is the copy of original array)
     * @param a
     * @return array
     */
    public X[] sortWithNoCopyAndInsurance(X[] a){
        // Source array aux
        // Destination array a.
        X[] aux = noCopy ? a : Arrays.copyOf(a, a.length);
        //X[] aux = a.clone ();

        sortWithNoCopyAndInsurance(aux,a,0,a.length-1);
        assert isSorted (a, 0,a.length-1);
        return a;
    }

    // Source array aux
    // Destination array a.
    private void sortWithNoCopyAndInsurance(X[]src, X[] dst, int from, int to) {
        final Helper<X> helper = getHelper();

        if (to <= from + getHelper().cutoff()) {
            //insertionSort.sort(dst, from, to);
            for (int i = from; i <= to; i++)
                for (int j = i; j > from && helper.less(dst[j], dst[j-1]); j--) {
                    helper.swap (dst, j, j-1);
                }

            assert isSorted (dst,from,to);
            return;
        }
        int middle = from + (to - from) / 2;

        //switched the roles of Source and Destination Array
        sortWithNoCopyAndInsurance(dst, src, from, middle); // sort left array
        sortWithNoCopyAndInsurance(dst, src, middle + 1, to); // sort right array

        /**If insurance variable is true then we compare the
         * last element of the left sub-array with the first element of the right sub-array.
         */
        if(insurance) {
            if(!helper.less (src[middle+1],src[middle])) {
                //helper.copy (src, from, dst, from);
                System.arraycopy (src, from, dst, from, to - from + 1);
                return;
            }
        }
        // Finally merge the two sorted arrays
        mergeForNoCopy(src, dst, from, middle, to);
    }

    /**
     * Merge Sort with No Copy Implementation
     * @param src
     * @param dst
     * @param lo
     * @param mid
     * @param hi
     */
    private void mergeForNoCopy(X[]src, X[] dst, int lo, int mid, int hi) {

        assert isSorted (src,lo, mid);
        assert isSorted (src,mid+1, hi);
        final Helper<X> helper = getHelper();
        int i = lo;
        int j = mid + 1;

        for(int k = lo; k<= hi; k++){
            if(i> mid) helper.copy (src,j++,dst,k);
            else if(j > hi) helper.copy (src, i++, dst, k);
            else if(helper.less (src[j],src[i])) {
                helper.incrementFixes(mid - i);
                helper.copy (src,j++,dst,k);
            }
            else helper.copy (src,i++,dst,k);
        }
        assert isSorted (dst,lo, hi);
    }

    private boolean isSorted(X[] a, int lo, int hi) {
        final Helper<X> helper = getHelper();
        for (int i = lo + 1; i <= hi; i++)
            if (helper.less(a[i], a[i-1])) return false;
        return true;
    }
    private void merge(X[] aux, X[] a, int lo, int mid, int hi) {
        final Helper<X> helper = getHelper();
        int i = lo;
        int j = mid;
        int k = lo;
        for (; k < hi; k++)
            if (i >= mid) helper.copy(aux, j++, a, k);
            else if (j >= hi) helper.copy(aux, i++, a, k);
            else if (helper.less(aux[j], aux[i])) {
                helper.incrementFixes(mid - i);
                helper.copy(aux, j++, a, k);
            } else helper.copy(aux, i++, a, k);
    }


    private X[] aux = null;
    private final InsertionSort<X> insertionSort;
}

