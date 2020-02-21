import java.util.ArrayList;

public class FullyConnectedLayer extends Layer{
    private Layer previousLayer;
    private Layer nextLayer;
    public FullyConnectedLayer(int num, String activationFun, Layer previousLayer) {
        this.previousLayer = previousLayer;
        this.setNeurals(num, activationFun);
    }

    @Override
    public ArrayList<Double> acquireInput() {
        return this.previousLayer.getOutput();
    }

    @Override
    public Layer getPreviousLayer() {
        return this.previousLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    public Layer getNextLayer() {
        return this.nextLayer;
    }
}
