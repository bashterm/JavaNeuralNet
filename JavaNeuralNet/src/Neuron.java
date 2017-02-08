import java.lang.Math;
import java.util.Random;

public class Neuron {
	private double[] synapticWeights; // Weights that get applied to each input
	Random rand = new Random(1); // Initialize random number generator with seed
									// of 1

	public Neuron(int inputNum) {
		// Constructor Initializes synaptic weights
		synapticWeights = new double[inputNum]; // Make sure that we have as
												// many weights as we have
												// inputs

		// Generate Random number between 1 and -1 for initial weight

		double initWeight = (2 * Math.random()) - 1;
		// Assign each weight in synaptic weights to the random number
		for (int i = 0; i < synapticWeights.length; i++) {
			synapticWeights[i] = initWeight;
			System.out.printf("%5.4f, ", synapticWeights[i]);
		}
		System.out.print("\n");
	}

	// Actual Heavy Lifting
	public void train(int[][] trainingDataIn, int[] trainingDataOut, int numberOfTrainingIterations) {
		// These arrays hold the data outputted by the training algorithm
		double[] predictions = new double[trainingDataOut.length];
		double[] error = new double[trainingDataOut.length];
		double[] adjustment = new double[trainingDataOut.length];
		// Repeat for the number of training iterations to calculate the best
		// weights possible
		// System.out.printf("%27s|%27s|%27s|%21s|","Predictions","Errors","Adjustments","New
		// Weights");
		for (int i = 0; i < numberOfTrainingIterations; i++) {
			for (int y = 0; y < trainingDataOut.length; y++) {
				// Forward Propagate (Just predict an output based on the
				// current weights and training input)
				predictions[y] = predict(trainingDataIn[y]);
				// System.out.printf("%5.4f ,",predictions[y]);
				// Now calculate how much we missed
				error[y] = trainingDataOut[y] - predictions[y];
			}
			for (int y = 0; y < trainingDataOut.length; y++) {
				// System.out.printf("%5.4f ,",error[y]);
			}
			// Now backpropagate. Basically take derivative of sigmoid at the
			// output and multiply it by the error
			for (int y = 0; y < error.length; y++) {
				adjustment[y] = error[y] * sigmoidDeriv(predictions[y]);
				// System.out.printf("%5.4f ,",adjustment[y]);
			}
			// Last step; update synaptic weights by multiplying the input array
			// by the adjustment array and adding them to the synaptic weights
			for (int y = 0; y < synapticWeights.length; y++) {
				synapticWeights[y] += dot(transpose(trainingDataIn)[y], adjustment);
				System.out.printf("%5.4f, ", synapticWeights[y]);
			}
			System.out.print("\n");
		}
	}

	public double predict(int[] input) {
		double prediction = 0;
		// Predict Output by taking dot product of inputs and matrices
		prediction = sigmoid(dot(input, synapticWeights));
		return prediction;
	}

	// Helper Methods
	public double sigmoid(double x) { // Use sigmoid function for predictions
		return 1 / (1 + Math.exp(-x));
	}

	public double sigmoidDeriv(int x) { // Use derivative of sigmoid function to
										// backpropagate.
		return sigmoid(x) * (1 - sigmoid(x));
	}

	public double sigmoidDeriv(double x) { // Use derivative of sigmoid function
											// to backpropagate.
		return sigmoid(x) * (1 - sigmoid(x));
	}

	// Linear Algebra Methods
	public double dot(int[] input, double[] weight) { // Dot product of two 1xN
														// arrays
		double sum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += input[i] * weight[i];
		}
		return sum;
	}

	public int[][] transpose(int[][] in) { // Returns Transpose of MxN array
		int[][] out = new int[in[0].length][in.length];
		// Set out[x][y] equal to input[y][x]
		for (int y = 0; y < in.length; y++) {
			for (int x = 0; x < in[0].length; x++) {
				out[x][y] = in[y][x];
			}
		}
		return out;
	}
}
