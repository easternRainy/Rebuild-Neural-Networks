import java.util.ArrayList;
import java.util.Random;

public class Neural {
    /* front propagation
    ->
    ->
    ->   , sum = w1x1+w2x2+...+wnxn+b , F = activation(sum)
    ->
    ->
     */

    /* back propagation
       update wi <- delF_delWi<- delJ_delF
       back output <- delF_delXi <- delJ_delF
     */

    // front propagation
    private ArrayList<Double> x; // input
    private ArrayList<Double> w; // weight
    private double b; //bias
    private String activationFun;
    private double sum; // WX + b

    private Double backPropInput; // size is the num of next layer's neurals, array of delJ_delF
    private ArrayList<Double> backPropOutput; // outer size: num of previous layer's neurals, inner size: next layer's neurals
    private double learningRate = -0.00001;
    private double delF_delSum;

    public Neural(ArrayList<Double> input, String activationFun) {
        this.x = input;
        this.initWeight();
        this.initBias();
        this.activationFun = activationFun;
    }

    public double getOutput() {
        assert(this.x.size() == this.w.size());
        this.getSum();
        return Activation.activation(this.sum, this.activationFun);
    }

    private void getSum() {
        // WX + b
        double sum = 0;
        for(int i=0; i<this.x.size(); i++) {
            sum = sum + this.w.get(i) * this.x.get(i);
        }
        this.sum = sum + this.b;
    }

    private void initWeight() {
        this.randomInitWeight();
    }

    private void randomInitWeight() {
        assert(this.x.size() > 0);
        Random rand = new Random();
        this.w = new ArrayList<>();
        for(int i=0; i<this.x.size(); i++) {
            this.w.add(rand.nextGaussian() / this.x.size());
        }
    }

    private void initBias() {
        this.initZeroBias();
    }

    private void randomInitBias() {
        Random rand = new Random();
        this.b = rand.nextDouble()*0.01;
    }

    private void initZeroBias() {
        this.b = 0.0;
    }

    public void setBackPropInput(Double backPropInput) {
        this.backPropInput = backPropInput;
    }

    public void setBackPropOutput() {
        this.delF_delSum = Activation.dActivation(this.sum, this.activationFun);
        ArrayList<Double> result = new ArrayList<>();
        for(int i=0; i<this.w.size(); i++) {
            double delSum_delXi = this.getDelSum_delXi(i);

            result.add(this.backPropInput * this.delF_delSum * delSum_delXi);

        }
        this.backPropOutput = result;
    }

    public ArrayList<Double> getBackPropOutput() {
        return this.backPropOutput;
    }


    private double getDelSum_delWi(int i) {
        assert(i>=0 && i<this.w.size());
        return this.x.get(i);
    }

    private double getDelSum_delXi(int i) {
        assert(i>=0 && i<this.w.size());
        return this.w.get(i);
    }

    public void updatePara() {
        this.updateW();
        this.updateB();
    }

    private void updateW() {
        assert(this.w.size() == this.x.size());
        ArrayList<Double> newW = new ArrayList<>();
        for(int i=0; i<this.w.size(); i++) {
            // this.x.get(i) is delSum_delWi
            newW.add(this.w.get(i) - this.learningRate * this.delF_delSum * this.getDelSum_delWi(i));
        }
        this.w = newW;
    }

    private void updateB() {
        this.b = this.b - this.learningRate * this.delF_delSum;
    }

    public ArrayList<Double> getW() {
        return this.w;
    }

    public double getB() {
        return this.b;
    }

    public static void main(String[] args) {
        ArrayList<Double> input = new ArrayList<>();
        for(int i=0; i<10; i++) {
            input.add(i*0.1);
        }
        Neural neural = new Neural(input,  "tanh");
        System.out.println("W: " + neural.getW());
        System.out.println("b: " + neural.getB());

        double output = neural.getOutput();
        System.out.println("Output: "+output);
        double target = 1;
//        System.out.println("dMSE: " + dMSE(output, target));
//
//        while(Math.abs(dMSE(output, target)) > 0.01) {
//            System.out.println("MSE: " + MSE(output, target));
//            System.out.println("dMSE: " + dMSE(output, target));
//            neural.updatePara(dMSE(output, target));
//            System.out.println("W: " + neural.getW());
//            System.out.println("b: " + neural.getB());
//            System.out.println("Output: " + output);
//            output = neural.getOutput();
//            //break;
//        }
    }
}
