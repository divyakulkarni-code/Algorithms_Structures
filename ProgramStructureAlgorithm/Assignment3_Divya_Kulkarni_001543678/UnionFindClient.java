package edu.neu.coe.info6205.union_find;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UnionFindClient {


    public static void main(String[] args) {

        int n = 100;
        int noOfTimesUnionPerformed = 0;
        int noOfPairsGenerated = 0;
        int x, y;

        //---------------------
        // Size based
        UF_HWQUPC unionFind = new UF_HWQUPC(n);

        Random random = new Random (); //Generating Random Pairs

        while(true){
            x = random.nextInt (n);
            y = random.nextInt (n);

            noOfPairsGenerated++; //Counter to track no. of pairs generated till we reach 1 component

            if (!unionFind.connected (x, y)){
                unionFind.union (x, y);
                noOfTimesUnionPerformed++; //Counter to track union performed which is n-1 times on number of pairs
            }
            //When all components are connected, for loop will stop here
            if(unionFind.components () == 1){
                break;
            }
        }

        System.out.println ("Final Component: " + unionFind.getCount() +
                ", "+ "No. of times Union Performed: " + noOfTimesUnionPerformed
                + ", "+ "Total Pairs Generated: " + noOfPairsGenerated);

        //---------------------
        // Height based
        WQ unionFind = new UF_HWQUPC(n);

        Random random = new Random (); //Generating Random Pairs

        while(true){
            x = random.nextInt (n);
            y = random.nextInt (n);

            noOfPairsGenerated++; //Counter to track no. of pairs generated till we reach 1 component

            if (!unionFind.connected (x, y)){
                unionFind.union (x, y);
                noOfTimesUnionPerformed++; //Counter to track union performed which is n-1 times on number of pairs
            }
            //When all components are connected, for loop will stop here
            if(unionFind.components () == 1){
                break;
            }
        }

        System.out.println ("Final Component: " + unionFind.getCount() +
                ", "+ "No. of times Union Performed: " + noOfTimesUnionPerformed
                + ", "+ "Total Pairs Generated: " + noOfPairsGenerated);

    }
}
