package edu.neu.coe.info6205.union_find;

public class WQUByDepth {
    //Declaring instance variables
    private int[] parent;
    private int[] height;
    private int countComponents;

    /**
     * Initializes an empty data structure with n elements with each element in its own set
     * @param n of elements
     */
    public WQUByDepth(int n){
        countComponents = n;
        parent = new int[n];
        height = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
            height[i] = 0;
        }
    }

    /**
     * Returns the number of sets
     * @return number of sets
     */
    public int countSets(){
        return countComponents;

    }

    /**
     * Returns the element of the set containing element p
     * @param p an element
     * @return element of the set containing the element p
     */
   public int find(int p){
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
   }

   //Validating index of the element p
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n){
            throw new IllegalArgumentException ("Index is not valid or between 0 to n-1");
        }
    }

    /**
     *
     * @param p one element
     * @param q second element
     * @return true if p and q are in the same set; false if they are not
     * @throws IllegalArgumentException is both p and q are not in the valid indexes
     */
    @Deprecated
    public boolean connected(int p, int q){
       return find(p) == find(q);
    }

    /**Merge the sets containing element p with the q
     *
     * @param p one element
     * @param q other element
     * @throws IllegalArgumentException if both elements are not within the valid indexes
     */
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);

        if (i == j){
            return;
        }

        //make the smaller tree point to the larger tree
        if (height[i] < height[j]) parent[i] = j;
        else if (height[i] > height[j]) parent[j] = i;

        else{
            parent[j] = i;
            height[i] ++;
        }
        countComponents --;
    }

}
