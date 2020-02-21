import java.util.ArrayList;

public abstract class Layer {
    protected int num; // num of neurals
    protected ArrayList<Neural> neurals;
    protected ArrayList<Double> input;
    protected ArrayList<Double> output;

    protected ArrayList<Double> backPropInput;
    protected ArrayList<Double> backPropOutput;


    public void setNeurals(int num, String activationFun) {
        this.setInput();
        this.neurals = new ArrayList<>();
        for(int i=0; i<num; i++) {
            this.neurals.add(new Neural(this.input, activationFun));
        }
    }

    public void setOutput() {
        this.output = new ArrayList<>();
        for(Neural neural: this.neurals) {
            this.output.add(neural.getOutput());
        }
    }

    public ArrayList<Double> getOutput() {
        this.setOutput();
        assert(this.output.size() > 0);
        return this.output;
    }

    public abstract ArrayList<Double> acquireInput();

    public void acquireBackPropInput(ArrayList<Double> backPropInput) {
        assert(backPropOutput.size() == this.neurals.size());
        this.backPropInput = backPropInput;
    }

    public void backPropagation() {
        ArrayList<ArrayList<Double>> neuralsOutput = new ArrayList<>();
//        System.out.println("Unit test: size of neural: " + this.neurals.size());
//        System.out.println("Unite test: size of backPropInput: " + this.backPropInput.size());
        for(int i=0; i<this.neurals.size(); i++) {
            Neural currNeural = this.neurals.get(i);
            currNeural.setBackPropInput(this.backPropInput.get(i));
            currNeural.setBackPropOutput();
            neuralsOutput.add(currNeural.getBackPropOutput());
            currNeural.updatePara();
        }

        this.backPropOutput = new ArrayList<>();
        for(int i=0; i<neuralsOutput.get(0).size(); i++) {
            double sum = 0;
            for(ArrayList<Double> eachNerualOutput: neuralsOutput ) {
                sum = sum + eachNerualOutput.get(i);
            }
            this.backPropOutput.add(sum);
        }

        if(this.getPreviousLayer()!=null) {
            Layer previousLayer = this.getPreviousLayer();
            previousLayer.acquireBackPropInput(this.backPropOutput);
            previousLayer.backPropagation();
        }

    }

    private void setInput() {
        this.input = this.acquireInput();
    }

    public abstract Layer getPreviousLayer();

    public abstract Layer getNextLayer();

}
