import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class NeuralNetwork {
    Set<Map.Entry<String, List<double[]>>> trainingMapEntrySet;
    List<Perceptron> perceptrons = new ArrayList<>();

    public NeuralNetwork(Map<String, List<double[]>> trainingMap) {
        this.trainingMapEntrySet = trainingMap.entrySet();
        for (Map.Entry<String, List<double[]>> entry : trainingMapEntrySet) {
            perceptrons.add(new Perceptron(26, entry.getKey()));
        }
    }

    public void train() {
        for (int i = 0; i < 50; i++) {
            for (Map.Entry<String, List<double[]>> entry : trainingMapEntrySet) {
                for (Perceptron perceptron : perceptrons) {
                    perceptron.train(entry.getValue(), entry.getKey());
                }
            }
        }
    }

    public String guess(String text) {
        StringBuilder sb = new StringBuilder();
        Map<Perceptron, Double> sums = new HashMap<>();
        for (Perceptron perceptron : perceptrons) {
            sums.put(perceptron, perceptron.getSum(getVectorFromString(text)));
        }
        Perceptron winner = Collections.max(sums.entrySet(), Map.Entry.comparingByValue()).getKey();
        for (int i = 0; i < perceptrons.size(); i++) {
            Perceptron perceptron = perceptrons.get(i);
            if (perceptron.getLanguage().equals(winner.getLanguage())) {
                sb.append(i + 1).append(". ").append(perceptron.getLanguage().toUpperCase()).append("\n");
            } else {
                sb.append(i + 1).append(". nie ").append(perceptron.getLanguage()).append("\n");
            }
        }
        return sb.toString();
    }

    public static double[] getVectorFromString(String text) {
        char[] charTab = text.replaceAll("[^\\x00-\\x7F]", "").toCharArray();
        int[] count = new int[26];
        int sum = 0;
        int[] tmpTab = new int[127];
        double[] resultTab = new double[26];
        for (char c : charTab) {
            tmpTab[c]++;
        }
        for (int i = 97; i <= 122; i++) {
            count[i - 97] = tmpTab[i];
            sum += tmpTab[i];
        }
        for (int i = 0; i < count.length; i++) {
            resultTab[i] = (double)count[i] / sum;
        }
        return resultTab;
    }

    public double[] getVectorFromFile(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine().toLowerCase());
        }
        return NeuralNetwork.getVectorFromString(sb.toString());
    }

    public double calculateAccuracy() throws IOException {
        Map<String, List<double[]>> testMap = new HashMap<>();
        Path path = Paths.get("test");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            String key = null;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                key = dir.getFileName().toString();
                if (!key.equals("test")) {
                    testMap.put(key, new ArrayList<>());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                testMap.get(key).add(getVectorFromFile(file.toString()));
                return FileVisitResult.CONTINUE;
            }
        });
        int correctGuesses = 0;
        int totalGuesses = 0;

        printTestMap(testMap);

        for (Map.Entry<String, List<double[]>> entry : testMap.entrySet()) {
            String actualLabel = entry.getKey();
            for (double[] vector : entry.getValue()) {
                String guessedLabel = classify(vector); // nowa metoda do klasyfikacji bez użycia guess(String text)
                if (guessedLabel.equals(actualLabel)) {
                    correctGuesses++;
                }
                totalGuesses++;
            }
        }


        return (double) correctGuesses / totalGuesses;

    }

    public void printTestMap(Map<String, List<double[]>> testMap) {
        for (Map.Entry<String, List<double[]>> entry : testMap.entrySet()) {
            System.out.println("Klasa: " + entry.getKey());
            System.out.println("Wektory cech:");
            List<double[]> vectors = entry.getValue();
            for (int i = 0; i < vectors.size(); i++) {
                System.out.println("Wektor " + (i + 1) + ": " + Arrays.toString(vectors.get(i)));
            }
            System.out.println();
        }
    }

    public String classify(double[] vector) {
        Map<Perceptron, Double> sums = new HashMap<>();
        for (Perceptron perceptron : perceptrons) {
            sums.put(perceptron, perceptron.getSum(vector));
        }
        Perceptron winner = Collections.max(sums.entrySet(), Map.Entry.comparingByValue()).getKey();
        return winner.getLanguage();
    }








}
