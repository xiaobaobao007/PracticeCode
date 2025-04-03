package per.bmy.neuralnet;

import java.util.ArrayList;

import per.bmy.neuralnet.math.IActivationFunction;
import per.bmy.neuralnet.math.RandomNumberGenerator;

public class Neuron {
	//神经元相关的权重
	protected ArrayList<Double> weight;
	//神经元的输入
	private ArrayList<Double> input;
	//这个神经元的输出，由激活函数产生
	private Double output;
	//传递给激活函数的值
	private Double outputBeforeActivation;
	//输入的数量。如果为0，则表示神经元尚未初始化。
	private int numberOfInputs = 0;
	//神经元的偏差。除了第一层，其他都应该是1.0。
	protected Double bias = 1.0;
	//神经元的激活函数
	private IActivationFunction activationFunction;

	public Neuron() {

	}

	public Neuron(int numberofinputs) {
		numberOfInputs = numberofinputs;
		weight = new ArrayList<>(numberofinputs + 1);
		input = new ArrayList<>(numberofinputs);
	}

	public Neuron(int numberofinputs, IActivationFunction iaf) {
		numberOfInputs = numberofinputs;
		weight = new ArrayList<>(numberofinputs + 1);
		input = new ArrayList<>(numberofinputs);
		activationFunction = iaf;
	}

	public void init() {
		if (numberOfInputs > 0) {
			for (int i = 0; i <= numberOfInputs; i++) {
				double newWeight = RandomNumberGenerator.GenerateNext();
				try {
					this.weight.set(i, newWeight);
				} catch (IndexOutOfBoundsException iobe) {
					this.weight.add(newWeight);
				}
			}
		}
	}

	public void setInputs(double[] values) {
		if (values.length == numberOfInputs) {
			for (int i = 0; i < numberOfInputs; i++) {
				try {
					input.set(i, values[i]);
				} catch (IndexOutOfBoundsException iobe) {
					input.add(values[i]);
				}
			}
		}
	}

	public void setInputs(ArrayList<Double> values) {
		if (values.size() == numberOfInputs) {
			input = values;
		}
	}

	public ArrayList<Double> getArrayInputs() {
		return input;
	}

	public double[] getInputs() {
		double[] inputs = new double[numberOfInputs];
		for (int i = 0; i < numberOfInputs; i++) {
			inputs[i] = this.input.get(i);
		}
		return inputs;
	}

	public void setInput(int i, double value) {
		if (i >= 0 && i < numberOfInputs) {
			try {
				input.set(i, value);
			} catch (IndexOutOfBoundsException iobe) {
				input.add(value);
			}
		}
	}

	public double getInput(int i) {
		return input.get(i);
	}

	public double[] getWeights() {
		double[] weights = new double[numberOfInputs + 1];
		for (int i = 0; i <= numberOfInputs; i++) {
			weights[i] = weight.get(i);
		}
		return weights;
	}

	public ArrayList<Double> getArrayWeights() {
		return weight;
	}

	public void updateWeight(int i, double value) {
		if (i >= 0 && i <= numberOfInputs) {
			weight.set(i, value);
		}
	}

	public int getNumberOfInputs() {
		return this.numberOfInputs;
	}

	public void setWeight(int i, double value) throws NeuralException {
		if (i >= 0 && i < numberOfInputs) {
			this.weight.set(i, value);
		} else {
			throw new NeuralException("Invalid weight index");
		}
	}

	public double getOutput() {
		return output;
	}

	public void calc() {
		outputBeforeActivation = 0.0;
		if (numberOfInputs > 0) {
			if (input != null && weight != null) {
				for (int i = 0; i <= numberOfInputs; i++) {
					outputBeforeActivation += (i == numberOfInputs ? bias : input.get(i)) * weight.get(i);
				}
			}
		}
		output = activationFunction.calc(outputBeforeActivation);
	}

	public void setActivationFunction(IActivationFunction iaf) {
		this.activationFunction = iaf;
	}

	public double getOutputBeforeActivation() {
		return outputBeforeActivation;
	}
}
