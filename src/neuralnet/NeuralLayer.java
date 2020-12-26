package neuralnet;

import java.util.ArrayList;

import neuralnet.math.IActivationFunction;


public abstract class NeuralLayer {
	//这一层的神经元数量
	protected int numberOfNeuronsInLayer;
	//这一层的神经元
	private final ArrayList<Neuron> neuron;
	//激励函数
	protected IActivationFunction activationFnc;
	//将值提供给此层的前一层
	protected NeuralLayer previousLayer;
	protected NeuralLayer nextLayer;
	protected ArrayList<Double> input;
	protected ArrayList<Double> output;
	protected int numberOfInputs;

	public NeuralLayer(int numberofneurons) {
		this.numberOfNeuronsInLayer = numberofneurons;
		neuron = new ArrayList<>(numberofneurons);
		output = new ArrayList<>(numberofneurons);
	}

	public NeuralLayer(int numberofneurons, IActivationFunction iaf) {
		this.numberOfNeuronsInLayer = numberofneurons;
		this.activationFnc = iaf;
		neuron = new ArrayList<>(numberofneurons);
		output = new ArrayList<>(numberofneurons);
	}

	public int getNumberOfNeuronsInLayer() {
		return numberOfNeuronsInLayer;
	}

	public ArrayList<Neuron> getListOfNeurons() {
		return neuron;
	}

	protected NeuralLayer getPreviousLayer() {
		return previousLayer;
	}

	protected NeuralLayer getNextLayer() {
		return nextLayer;
	}

	protected void setPreviousLayer(NeuralLayer layer) {
		previousLayer = layer;
	}

	protected void setNextLayer(NeuralLayer layer) {
		nextLayer = layer;
	}

	protected void init() {
		if (numberOfNeuronsInLayer >= 0) {
			for (int i = 0; i < numberOfNeuronsInLayer; i++) {
				try {
					neuron.get(i).setActivationFunction(activationFnc);
					neuron.get(i).init();
				} catch (IndexOutOfBoundsException iobe) {
					neuron.add(new Neuron(numberOfInputs, activationFnc));
					neuron.get(i).init();
				}
			}
		}
	}

	protected void setInputs(ArrayList<Double> inputs) {
		this.numberOfInputs = inputs.size();
		this.input = inputs;
	}

	protected void calc() {
		if (input != null && !neuron.isEmpty()) {
			for (int i = 0; i < numberOfNeuronsInLayer; i++) {
				neuron.get(i).setInputs(this.input);
				neuron.get(i).calc();
				try {
					output.set(i, neuron.get(i).getOutput());
				} catch (IndexOutOfBoundsException iobe) {
					output.add(neuron.get(i).getOutput());
				}
			}
		}
	}

	protected ArrayList<Double> getOutputs() {
		return output;
	}

	protected Neuron getNeuron(int i) {
		return neuron.get(i);
	}

	protected void setNeuron(int i, Neuron _neuron) {
		try {
			this.neuron.set(i, _neuron);
		} catch (IndexOutOfBoundsException iobe) {
			this.neuron.add(_neuron);
		}
	}

}
