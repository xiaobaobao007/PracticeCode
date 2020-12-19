package neuralnet.math;

public interface IActivationFunction {
	double calc(double x);

	enum ActivationFunctionENUM {
		STEP, LINEAR, SIGMOID, HYPERTAN
	}
}
