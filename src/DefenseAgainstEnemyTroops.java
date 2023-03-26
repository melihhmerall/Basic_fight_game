import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        // TODO: YOUR CODE HERE
        int N =numberOfEnemiesArrivingPerHour.size();
        int [] Sol = new int[N+1];
        int adder =0;
        Sol[0] = 0;
        int melih = 0;
        ArrayList<Integer> Hour = new ArrayList<Integer>();
        for (int j = 1; j < N+1; j++) {
            int max =-1;
            int returni =0;

            for (int i = 0; i < j; i++) {
                melih = 0;
                if(numberOfEnemiesArrivingPerHour.get(j-1) >(i - j)*(i - j)){
                    adder =Sol[i] + (j - i)*(j - i);
                    melih--;
                }
                else {
                    adder =Sol[i] + numberOfEnemiesArrivingPerHour.get(j-1);

                }
                if(Sol[j]  < adder){
                    Sol[j]  = adder;
                    returni = i;



                }

            }
            Hour.add(returni);
            Hour.add(j);

        }

//                for (int l = Hour.size()-3; l >-1; l--) {
//                    Hour.set(l,0);
//                }
//
//        for (int i = Hour.size()-1; i >-1; i--) {
//            if (Hour.get(i)==0){
//                Hour.remove(i);
//            }
//        }


        OptimalEnemyDefenseSolution optimalEnemyDefenseSolution = new OptimalEnemyDefenseSolution(Sol[N],Hour);


        return optimalEnemyDefenseSolution;
    }
}
