package classification;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectionMethod {

    protected HashMap<Integer, ArrayList<DataInstance>> selectedInstances;

    protected HashMap<String, Object> selectAttribute(ArrayList<DataInstance> instances
                                                  , ArrayList<Integer> attributeList) {
        return new HashMap<String, Object>();
    }

    public HashMap<Integer, ArrayList<DataInstance>> getSelectedInstances(){
        return selectedInstances;
    }
}
