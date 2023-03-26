import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP {
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity           the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity) {
        // First sort all weights in decreasing order

        Collections.sort(bombWeights, Collections.reverseOrder());
        int m = 0;
        int[] AUAVSnumber = new int[maxNumberOfAvailableAUAVs];
        Arrays.fill(AUAVSnumber,10);
        for (int i = 0; i < bombWeights.size(); i++) {
            if (bombWeights.get(i) > maxAUAVCapacity){
                return -1;
            }
        }

        for (int i = 0; i < bombWeights.size(); i++) {

            if (i < maxNumberOfAvailableAUAVs &&bombWeights.get(i) == AUAVSnumber[i] && bombWeights.get(i) <= maxAUAVCapacity ) {
                AUAVSnumber[i] -= maxAUAVCapacity;

                bombWeights.set(i,-1);
            } else  {
                for (int j = 0; j < maxNumberOfAvailableAUAVs; j++) {
                    if (AUAVSnumber[j] >= bombWeights.get(i) && bombWeights.get(j) <= maxAUAVCapacity ) {
                        AUAVSnumber[j] -= bombWeights.get(i);
                        m = j;
                        bombWeights.set(i,-1);
                            break;
                    }
                }
            }
        }
        for (int i = 0; i < bombWeights.size(); i++) {
            if (bombWeights.get(i) != -1){
                return -1;
            }
        }
        return m+1;
    }

        // Initialize result (Count of AUAVs)
        // Create an array to store remaining space in AUAVs, there can be at most maxNumberOfAvailableAUAVs AUAVs
        // Place items one by one


    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
