package classification;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomSelection extends SelectionMethod {

    protected HashMap<String, Object> selectAttribute(ArrayList<DataInstance> instances
                                                  , ArrayList<Integer> attributeList) {
        Random r = new Random();
        Integer attr = attributeList.get(r.nextInt(attributeList.size())); 

        selectedInstances = new HashMap<Integer, ArrayList<DataInstance>>();
        ArrayList<DataInstance> data;
        for (DataInstance di : instances) {
            int value = di.getAttributes().get(attr.intValue()).intValue();
            if (selectedInstances.containsKey(value)){
                data = selectedInstances.get(value);
                data.add(di);
                selectedInstances.put(value, data);
            } else {
                data = new ArrayList<DataInstance>();
                data.add(di);
                selectedInstances.put(value, data);
            }
        }

        HashMap<String, Object> choice = new HashMap<String, Object>();
        choice.put("attribute", attr);
        choice.put("discrete", "true");
        choice.put("multiway", "true");
        return choice;
    }
}
