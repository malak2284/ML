import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Perceptron {
    static double alpha = 0.1; // Współczynnik uczenia

    static class Label {
        double[] data;
        int decision; // 0 dla Iris-setosa, 1 dla Iris-versicolor i Iris-virginica

        public Label(double[] data, int decision) {
            this.data = data;
            this.decision = decision;
        }
    }

    private static List<Label> loadInput(String fname) throws FileNotFoundException {
        List<Label> list = new ArrayList<>();
        Scanner input = new Scanner(new File(fname));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] row = line.split("\t");
            double[] x = new double[row.length - 1];
            for (int i = 0; i < row.length - 1; i++) {
                String number = row[i].replace(',', '.');
                x[i] = Double.parseDouble(number);
            }
            int decision;
            if (row[row.length - 1].equals("Iris-setosa"))
                decision = 0;
            else
                decision = 1;
            list.add(new Label(x, decision));
        }
        input.close();
        return list;
    }

    private static double[] initializeWeights(int size) {
        double[] weights = new double[size];
        for (int i = 0; i < size; i++) {
            weights[i] = 0; // Inicjalizacja wag jako 0
        }
        return weights;
    }

    private static int calculateOutput(double[] weights, double[] data) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += data[i] * weights[i];
        }
        return (sum >= 0) ? 1 : 0; // Funkcja aktywacji: 1 dla sumy dodatniej, 0 w przeciwnym wypadku
    }

    private static double[] trainPerceptron(List<Label> list, double[] weights) {
        for (Label label : list) {
            int output = calculateOutput(weights, label.data);
            int localError = label.decision - output;
            for (int i = 0; i < weights.length; i++) {
                weights[i] += alpha * localError * label.data[i]; // Aktualizacja wag
            }
        }
        return weights;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Label> trainingData = loadInput("iris_training.txt");
        List<Label> testData = loadInput("iris_test.txt");

        int numAttributes = trainingData.get(0).data.length;
        double[] weights = initializeWeights(numAttributes);

        weights = trainPerceptron(trainingData, weights);

        int correctPredictions = 0;
        for (Label label : testData) {
            int output = calculateOutput(weights, label.data);
            if (output == label.decision) {
                correctPredictions++;
            }
        }

        double accuracy = (double) correctPredictions / testData.size() * 100;
        System.out.println("Number of correctly classified examples: " + correctPredictions);
        System.out.println("Accuracy: " + accuracy + "%");

        // Testowanie perceptronu na wejściu podanym ręcznie
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the attributes for testing (separated by spaces):");
        double[] testInput = new double[numAttributes];
        for (int i = 0; i < numAttributes; i++) {
            testInput[i] = scanner.nextDouble();
        }
        int testOutput = calculateOutput(weights, testInput);
        System.out.println("Predicted class: " + (testOutput == 0 ? "Iris-setosa" : "Iris-versicolor or Iris-virginica"));
        scanner.close();
    }
}
