package edu.neu.coe.info6205.union_find;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UnionFindClient {
    int n = 30000;
    int noOfTimesUnionPerformed = 0;
    int noOfPairsGenerated = 0;
    int x, y;
    Random random = new Random (); //Generating Random Pairs


    public void wquByDepth(){
        WQUByDepth unionFindByHeight = new WQUByDepth (n);

        while(true){
            x = random.nextInt (n);
            y = random.nextInt (n);

            noOfPairsGenerated++; //Counter to track no. of pairs generated till we reach 1 component

            if (!unionFindByHeight.connected (x, y)){
                unionFindByHeight.union (x, y);
                noOfTimesUnionPerformed++; //Counter to track union performed which is n-1 times on number of pairs
            }
            //When all components are connected, for loop will stop here
            if(unionFindByHeight.countSets () == 1){
                break;
            }
        }

    }

    public void wquPathCompression(){
        WQUPC unionFind = new WQUPC(n);
        while(true){

            x = random.nextInt (n);
            y = random.nextInt (n);

            noOfPairsGenerated++; //Counter to track no. of pairs generated till we reach 1 component

            if (!unionFind.connected (x, y)){
                unionFind.union (x, y);
                noOfTimesUnionPerformed++; //Counter to track union performed which is n-1 times on number of pairs
            }
            //When all components are connected, for loop will stop here
            if(unionFind.count () == 1){
                break;
            }
        }


    }
}
