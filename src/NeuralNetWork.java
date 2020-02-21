import java.util.ArrayList;
import java.util.Random;

public class NeuralNetWork {
    // for testing
    public static double MSE(ArrayList<Double> x, ArrayList<Double> x0) {
        assert(x.size() == x0.size());
        double sum = 0;
        for(int i=0; i<x.size(); i++) {
            sum = sum + Math.pow(x.get(i) - x0.get(i), 2);
        }
        return Math.sqrt(sum);
    }

    public static ArrayList<Double> dMSE(ArrayList<Double> x, ArrayList<Double> x0) {
        assert(x.size() == x0.size());
        ArrayList<Double> derivative = new ArrayList<>();
        for(int i=0; i<x.size(); i++) {
            derivative.add((x.get(i) - x0.get(i)) / MSE(x, x0));
        }
        return derivative;
    }


    public static void main(String[] args) {
        ArrayList<Double> data = new ArrayList<>();
        ArrayList<Double> target = new ArrayList<>();
        Random rand = new Random();
        for(int i=0; i<5; i++) {
            data.add(i * 0.1);
        }
        target.add(0.8);
        target.add(0.991);
        target.add(0.77);
        target.add(0.74);
        target.add(0.67);



        InputLayer inputLayer = new InputLayer(data);
        FullyConnectedLayer f1 = new FullyConnectedLayer(10, "pointOne", inputLayer);
        FullyConnectedLayer output = new FullyConnectedLayer(5, "pointOne", f1);


        ArrayList<Double> outputData = output.getOutput();

        System.out.println(outputData.toString());

        System.out.println("MSE: " + MSE(outputData, target));
        while(Math.abs(MSE(outputData, target)) > 0.3) {
            System.out.println("Unit test: MSE: " + Math.abs(MSE(outputData, target)));
            System.out.println("Unit test: outputData: " + outputData.toString());
            ArrayList<Double> backInput = new ArrayList<>();
            backInput = dMSE(outputData, target);
            output.acquireBackPropInput(backInput);
            output.backPropagation();
            outputData = output.getOutput();
        }

        System.out.println("Unit test: MSE: " + Math.abs(MSE(outputData, target)));
        System.out.println("Unit test: outputData: " + outputData.toString());
    }

}
