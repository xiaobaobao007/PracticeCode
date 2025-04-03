package per.bmy.neuralnet;

import per.bmy.neuralnet.math.IActivationFunction;
import per.bmy.neuralnet.math.Linear;
import per.bmy.neuralnet.math.RandomNumberGenerator;
import per.bmy.neuralnet.math.Sigmoid;

public class NeuralNetConsoleTest {
	public static void main(String[] args) {

		RandomNumberGenerator.seed = 0;

		int numberOfInputs = 2;
		int numberOfOutputs = 1;
		int[] numberOfHiddenNeurons = {3};
		IActivationFunction[] hiddenAcFnc = {new Sigmoid(1.0)};
		Linear outputAcFnc = new Linear(1.0);
		System.out.println("Creating Neural Netword...");
		NeuralNet nn = new NeuralNet(numberOfInputs, numberOfOutputs, numberOfHiddenNeurons, hiddenAcFnc, outputAcFnc);
		System.out.println("Neural Network Network...");

		double[] neuralInput = {1.5, 0.5};
		System.out.println("Feeding the values {1.5;0.5} to the neural network");
		double[] neuralOutput;
		nn.setInputs(neuralInput);
		nn.calc();
		neuralOutput = nn.getOutputs();
		System.out.println("OutPut 1:" + neuralOutput[0]);
		neuralInput[0] = 1.0;
		neuralInput[1] = 2.1;
		System.out.println("Feeding the values {1.0;2.1} to the neural network");
		nn.setInputs(neuralInput);
		nn.calc();
		neuralOutput = nn.getOutputs();
		System.out.println("OutPut 2:" + neuralOutput[0]);

	}
}
