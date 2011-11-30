import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.io.*;

public class Utilities {

    public static HashMap<String, Integer> tallyLabelCount(ArrayList<DataInstance> instances){
        HashMap<String, Integer> tally = new HashMap<String, Integer>();
        String label;
        for (DataInstance di : instances){
            label = di.getLabel();
            if (tally.containsKey(label)){
                tally.put(label, tally.get(label) + 1);
            } else {
                tally.put(label, 1);
            }
        }
        return tally;
    }


    public static Integer getMax(ArrayList<Integer>counts) {
        Integer maxValue = counts.get(0);
        for (Integer value : counts){
            if(value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static Double getMax(ArrayList<Double>counts) {
        Double maxValue = counts.get(0);
        for (Double value : counts){
            if(value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

}

