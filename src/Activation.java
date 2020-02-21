public class Activation {
    public static double activation(double x, String funName) {
        if(funName=="relu") {
            return relu(x);
        }

        if(funName=="sigmoid") {
            return sigmoid(x);
        }

        if(funName=="same") {
            return same(x);
        }

        if(funName=="tanh") {
            return tanh(x);
        }

        if(funName=="pointOne") {
            return pointOne(x);
        }

        System.out.println("Activation function does not exist");
        return 0;
    }

    public static double dActivation(double x, String funName) {
        if(funName=="relu") {
            return dRelu(x);
        }

        if(funName=="sigmoid") {
            return dSigmoid(x);
        }

        if(funName=="same") {
            return dSame(x);
        }

        if(funName=="tanh") {
            return dTanh(x);
        }

        if(funName=="pointOne") {
            return dPointOne(x);
        }

        System.out.println("Activation function does not exist");
        return 0;
    }

    public static double relu(double x) {
        if (x <= 0) {
            return 0;
        } else {
            return x;
        }
    }

    public static double dRelu(double x) {
        if (x <=0 ) {
            return 0;
        } else {
            return 1;
        }
    }

    public static double sigmoid(double x) {
        return 1/(1+Math.exp(-x));
    }

    public static double dSigmoid(double x) {
        return 1/(2 + Math.exp(x) + Math.exp(-x));
    }

    public static double same(double x) {
        return x;
    }

    public static double dSame(double x) {
        return 1;
    }

    public static double tanh(double x) {
        return Math.tanh(x);
    }

    public static double dTanh(double x) {
        return 1 - Math.pow(Math.tanh(x), 2);
    }

    public static double pointOne(double x) {
        return 0.1 * x;
    }

    public static double dPointOne(double x) {
        return 0.1;
    }
}
