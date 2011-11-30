import java.util.ArrayList;

public class RandomForest {

    private ArrayList<DecisionTree> forest;

    public RandomForest(String trainingFile, String testFile){
        forest = new ArrayList<DecisionTree>();
        forest.add(new DecisionTree(trainingFile, testFile, new RandomSelection()));
    }

    public static void main(String[] args) {
        RandomForest rf = new RandomForest(args[0], args[1]);
    }
}
