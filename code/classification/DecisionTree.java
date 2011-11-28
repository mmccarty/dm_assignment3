import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DecisionTree {
    private String trainingFile;
    private String testFile;
    private ArrayList<DataInstance> trainingInstances;
    private ArrayList<DataInstance> testInstances;

    public DecisionTree(String trainingFile, String testFile) {
        System.out.println("Training File: " + trainingFile);
        System.out.println("Test File: " + testFile);
        this.trainingFile = trainingFile;
        this.testFile     = testFile;
        trainingInstances = new ArrayList<DataInstance>();
        testInstances     = new ArrayList<DataInstance>();
        readFile(trainingFile, trainingInstances);
        readFile(testFile, testInstances);

        // Debuging print outs
        //printInstances(trainingInstances);
        //printInstances(testInstances);

        Node root = generateDecisionTree(trainingInstances
                                       , trainingInstances.get(0).getAttributeList());
    }

    private void readFile(String fileName, ArrayList<DataInstance> instances) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in      = new DataInputStream(fstream);
            BufferedReader br       = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String[] splitLine;
            while ((strLine = br.readLine()) != null) {
                splitLine = strLine.split("\t");
                ArrayList<Integer> attributes = new ArrayList<Integer>();
                for (int i = 1; i < splitLine.length; i++) {
                    attributes.add(Integer.parseInt(splitLine[i]));
                }
                instances.add(new DataInstance(splitLine[0], attributes));
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private Node generateDecisionTree(ArrayList<DataInstance> instances
                                    , ArrayList<Integer> attributeList) {
        Node n = new Node();
        n.setInstances(instances);
        if (n.instancesOfSameLabel()) {
            return n;
        }
        if (attributeList.isEmpty()) {
            n.setMajorityLabel();
            return n;
        }

        HashMap<String, Object> splitCriterion = selectAttribute(instances, attributeList);
        n.setLabel(splitCriterion.get("attribute").toString());
        if (splitCriterion.get("discrete").toString() == "true" && splitCriterion.get("multiway").toString() == "true"){
            attributeList = removeSplit(attributeList, splitCriterion.get("attribute"));
        }
        HashMap<String, ArrayList<DataInstance>> partitions = partitionInstances(
               splitCriterion, instances);
        Set<String> labels = partitions.keySet();
        ArrayList<DataInstance> splitInstances;
        Node leaf;
        Node branch;
        for (Object l : labels) {
            splitInstances = partitions.get(l);
            branch = new Node();
            branch.setLabel(l.toString());
            if (splitInstances.isEmpty()) {
                leaf = new Node();
                leaf.setInstances(instances);
                leaf.setMajorityLabel();
                branch.addChild(leaf);
            } else {
                branch.addChild(generateDecisionTree(splitInstances, attributeList));
            }
            n.addChild(branch);
        }
        return n;
    }

    public void printInstances(ArrayList<DataInstance> instances) {
        for (DataInstance di : instances) {
            System.out.println(
                "label: " + di.getLabel() + " attributes: " + di.getAttributes());
        }
    }

    public static void main(String[] args) {
        DecisionTree dt = new DecisionTree(args[0], args[1]);
    }
}
