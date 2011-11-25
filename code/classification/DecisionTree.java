import java.io.*;
import java.util.ArrayList;

public class DecisionTree {
    private String trainingFile;
    private String testFile;
    private ArrayList<DataInstance> instances;

    public DecisionTree(String trainingFile, String testFile) {
        System.out.println("Training File: " + trainingFile);
        System.out.println("Test File: " + testFile);
        this.trainingFile = trainingFile;
        this.testFile     = testFile;
        instances         = new ArrayList<DataInstance>();
        readTrainingFile();
        printInstances();
    }

    private void readTrainingFile() {
        try {
            FileInputStream fstream = new FileInputStream(trainingFile);
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

    public void printInstances() {
        for (DataInstance di : instances) {
            System.out.println(
                "label: " + di.getLabel() + " attributes: " + di.getAttributes());
        }
    }

    public static void main(String[] args) {
        DecisionTree dt = new DecisionTree(args[0], args[1]);
    }
}
