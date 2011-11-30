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

    public static void evaluateModel(HashMap<String, Integer> results){
        Integer tp  = results.get("true positive");
        Integer fn  = results.get("false negative");
        Integer tn  = results.get("true negative");
        Integer fp  = results.get("false positive");
        Integer all = tp + fn + tn + fp;

        double accuracy    = (double) (tp + tn) / all;
        double errorRate   = 1 - accuracy;
        double sensitivity = (double) tp / (tp + fn);
        double specificity = (double) tn / (fp + tn);
        double precision   = (double) tp / (tp + fp);
        double recall      = (double) tp / (tp + fn);
        double fScore      = (double) (2 * precision * recall) / (precision + recall);

        System.out.println("Accuracy: " + accuracy);
        System.out.println("errorRate: " + errorRate);
        System.out.println("sensitivity: " + sensitivity);
        System.out.println("specificity: " + specificity);
        System.out.println("precision: " + precision);
        System.out.println("F-1 Score: " + fScore);
        System.out.println("F-beta Score (0.5): " + fBeta(0.5, precision, recall));
        System.out.println("F-beta Score (2.0): " + fBeta(2.0, precision, recall));
    }

    public static double fBeta(double beta, double precision, double recall) {
        return (double) ((1 + beta * beta) * precision * recall) / (beta * beta * precision + recall);
    }

}

