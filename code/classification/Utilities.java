import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.io.*;

public class Utilities {

    private static HashMap<Integer, ArrayList<DataInstance>> instances_k; 

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

    public static double infoGainAttr(Integer attr, ArrayList<DataInstance> instances) {
        HashMap<Integer, Integer> tally = attributeHistorgram(attr, instances);
        Double info_a  = 0.0;
        //System.out.println("Attribute: " + attr);
        //System.out.println(tally);
        int total = instances.size();
        for (Integer k : tally.keySet()) {
            int count = tally.get(k).intValue(); 
            double p  = (double) count / total;
            info_a = info_a + p * infoGain(instances_k.get(k)); 
        }
        //System.out.println("Info A: " + info_a);
        return info_a;
    }

    public static double infoGain(ArrayList<DataInstance> instances) {
        HashMap<String, Integer> tally = Utilities.tallyLabelCount(instances);
        //System.out.println(tally);
        int total = instances.size();
        ArrayList<Integer> counts = new ArrayList<Integer>(tally.values());
        Double info_d = 0.0;
        for (Integer c : counts) {
           double p = (double) c.intValue() / total;
           info_d = info_d + (-1 * p * (Math.log(p) / Math.log(2)));
        }
        return info_d;
    }

    public static HashMap<Integer, Integer> attributeHistorgram(Integer attr
                                             , ArrayList<DataInstance> instances) {
        HashMap<Integer, Integer> tally = new HashMap<Integer, Integer>();
        instances_k = new HashMap<Integer, ArrayList<DataInstance>>();
        ArrayList<DataInstance> data;
        for (DataInstance di : instances) {
            int value = di.getAttributes().get(attr.intValue()).intValue();
            if (tally.containsKey(value)) {
                data = instances_k.get(value);
                data.add(di);
                instances_k.put(value, data);
                tally.put(value, tally.get(value) + 1);
            } else {
                data = new ArrayList<DataInstance>();
                data.add(di);
                instances_k.put(value, data);
                tally.put(value, 1);
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

    public static HashMap<Integer, ArrayList<DataInstance>> getInstancesK() {
        return instances_k;
    }

}

