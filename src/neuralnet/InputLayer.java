package neuralnet;

import java.util.ArrayList;

import neuralnet.math.Linear;

public class InputLayer extends NeuralLayer {

	public InputLayer(int numberofinputs) {
		super(numberofinputs, new Linear(1));
		previousLayer = null;
		numberOfInputs = numberofinputs;
		init();
	}

	@Override
	public void setNextLayer(NeuralLayer layer) {
		nextLayer = layer;
		if (layer.previousLayer != this)
			layer.setPreviousLayer(this);
	}

	@Override
	public void setPreviousLayer(NeuralLayer layer) {
		previousLayer = null;
	}


	@Override
	public void init() {
		for (int i = 0; i < numberOfInputs; i++) {
			this.setNeuron(i, new InputNeuron());
			this.getNeuron(i).init();
		}
	}

	/**
	 * setInputs
	 * This method feeds an array of real values into this layer's inputs
	 *
	 * @param inputs array of values to be fed into the layer's inputs
	 * @see InputLayer
	 */
	@Override
	public void setInputs(ArrayList<Double> inputs) {
		if (inputs.size() == numberOfInputs) {
			input = inputs;
		}
	}

	/**
	 * calc
	 * This method overrides the superclass calc because it just passes the
	 * input values to the outputs, provided this is the input layer
	 *
	 * @see InputLayer
	 */
	@Override
	public void calc() {
		if (input != null && getListOfNeurons() != null) {
			for (int i = 0; i < numberOfNeuronsInLayer; i++) {
				double[] firstInput = {this.input.get(i)};
				getNeuron(i).setInputs(firstInput);
				getNeuron(i).calc();
				try {
					output.set(i, getNeuron(i).getOutput());
				} catch (IndexOutOfBoundsException iobe) {
					output.add(getNeuron(i).getOutput());
				}
			}
		}
	}

}
