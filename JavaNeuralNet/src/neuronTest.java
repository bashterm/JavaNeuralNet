
public class neuronTest {
	public static void main(String[] args) {
		// Training Data
		// Arrays are addressed like such [row][column]
		int[][] inputs = { { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 1 } };
		int[] outputs = { 0, 1, 1, 0 };
		// Testing Data
		int[] in = { 1, 0, 1 };
		System.out.println("DEBUG: Creating Neuron");
		Neuron neuron1 = new Neuron(3);

		System.out.println("DEBUG: Training");
		neuron1.train(inputs, outputs, 100);

		System.out.println("DEBUG: Predicting Value");
		double guess = neuron1.predict(in);

		System.out.println(guess);
	}
}
/*
 * I thought this network was bad. But i just messed up some PEMDAS in the neuron code. It works as expected now
 */