import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Node{

    private String label;
    private Node parent;
    private ArrayList<Node> children;
    private ArrayList<DataInstance> instances;

    public Node() {
        children    = new ArrayList<Node>();
    }

    public void setLabel(String label) {
        this.label  = label;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setInstances(ArrayList<DataInstance> instances) {
        this.instances = instances;
    }

    public ArrayList<DataInstance> getInstances() {
        return instances;
    }

    public void addChild(Node child) {
        children.add(child);
        child.setParent(this);
    }

    public boolean instancesOfSameLabel(){
        HashMap<String, Integer> tally = tallyLabelCount();
        ArrayList<String> labels = new ArrayList<String>(tally.keySet());
        if (labels.size() == 1) {
            setLabel(labels.get(0));
            return true;
        }
        return false;
    }

    public void setMajorityLabel() {
        HashMap<String, Integer> tally = tallyLabelCount();
        //System.out.println("Majority Label tally:");
        //System.out.println(tally);
        ArrayList<Integer> counts = new ArrayList<Integer>(tally.values());
        Integer max = Utilities.getMax(counts);
        //System.out.println(max);
        String label = new ArrayList<String>(tally.keySet()).get(counts.indexOf(max));
        //System.out.println(label);
        setLabel(label);
    }

    private HashMap<String, Integer> tallyLabelCount(){
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
}
