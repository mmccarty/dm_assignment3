import java.util.ArrayList;

public class DataInstance{

    private ArrayList<Integer> attributes;
    private String label;

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
}