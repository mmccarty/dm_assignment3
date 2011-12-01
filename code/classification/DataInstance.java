package classification;

import java.util.ArrayList;

public class DataInstance{

    private ArrayList<Integer> attributes;
    private String label;
    private String classifierResult;

    public DataInstance(String label, ArrayList<Integer> attributes){
        this.attributes = attributes;
        this.label      = label;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Integer> getAttributes() {
        return attributes;
    }

    public ArrayList<Integer> getAttributeList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer i = 0; i < attributes.size(); i++) {
            list.add(i);
        }
        return list;
    }

    public void setClassifierResult(String result){
        classifierResult = result;
    }

    public String getClassificerResult() {
        return classifierResult;
    }
}
