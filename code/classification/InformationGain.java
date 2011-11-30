import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InformationGain extends SelectionMethod {

    protected HashMap<String, Object> selectAttribute(ArrayList<DataInstance> instances
                                                  , ArrayList<Integer> attributeList) {
        Double info_d = Utilities.infoGain(instances);
        //System.out.println(info_d);
        ArrayList<Double> info_attrs = new ArrayList<Double>();
        ArrayList<Double> gain       = new ArrayList<Double>();
        double info_a;
        ArrayList<HashMap<Integer, ArrayList<DataInstance>>> instances_k = 
                     new ArrayList<HashMap<Integer, ArrayList<DataInstance>>>();
        for (Integer attr : attributeList) {
            info_a = Utilities.infoGainAttr(attr, instances);
            instances_k.add(Utilities.getInstancesK());
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
        selectedInstances = instances_k.get(attr);
        return choice;
    }

}
