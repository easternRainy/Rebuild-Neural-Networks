import java.util.ArrayList;

public class InputLayer extends Layer {
    private ArrayList<Double> input;
    private Layer nextLayer;

    public InputLayer(ArrayList<Double> input) {
        this.input = input;
        this.setNeurals(this.input.size(), "same");
    }

    @Override
    public ArrayList<Double> acquireInput() {
        return this.input;
    }

    @Override
    public Layer getPreviousLayer() {
        return null;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    @Override
    public Layer getNextLayer() {
        return this.nextLayer;
    }
}
