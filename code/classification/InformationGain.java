import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InformationGain extends SelectionMethod {

    private static HashMap<Integer, ArrayList<DataInstance>> instances_k; 

    protected HashMap<String, Object> selectAttribute(ArrayList<DataInstance> instances
                                                  , ArrayList<Integer> attributeList) {
        Double info_d = infoGain(instances);
        //System.out.println(info_d);
        ArrayList<Double> info_attrs = new ArrayList<Double>();
        ArrayList<Double> gain       = new ArrayList<Double>();
        double info_a;
        ArrayList<HashMap<Integer, ArrayList<DataInstance>>> instances_k_all= 
                     new ArrayList<HashMap<Integer, ArrayList<DataInstance>>>();
        for (Integer attr : attributeList) {
            info_a = infoGainAttr(attr, instances);
            instances_k_all.add(instances_k);
            info_attrs.add(info_a);
            gain.add(info_d - info_a);
        }
        double max_gain = Utilities.getMax(gain);
        //System.out.println(max_gain);
        //System.out.println(gain);
        Integer attr = gain.indexOf(max_gain);
        HashMap<String, Object> choice = new HashMap<String, Object>();
        choice.put("attribute", attr);
        choice.put("discrete", "true");
        choice.put("multiway", "true");
        selectedInstances = instances_k_all.get(attr);
        return choice;
    }

    public double infoGainAttr(Integer attr, ArrayList<DataInstance> instances) {
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

    public double infoGain(ArrayList<DataInstance> instances) {
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

    public HashMap<Integer, Integer> attributeHistorgram(Integer attr
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

}
