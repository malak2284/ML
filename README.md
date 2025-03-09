# Perceptron for Iris Classification

This project is a Java-based implementation of a simple perceptron applied to the Iris dataset. It trains the perceptron to distinguish between Iris-setosa (label 0) and the other two Iris species (Iris-versicolor and Iris-virginica, label 1). The program loads training and test data from files, performs a training pass, evaluates accuracy, and even lets you test with custom inputs.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [How It Works](#how-it-works)


## Overview
This project demonstrates the basics of machine learning with a single-layer perceptron:
- **Training:** Reads from `iris_training.txt` to adjust weights based on a learning rate.
- **Testing:** Evaluates the perceptron on `iris_test.txt` to compute classification accuracy.
- **Manual Testing:** Allows users to input attribute values and receive a predicted class in real time.

## Features
- **Simple Perceptron Algorithm:**  
  Implements weight updates using a basic perceptron rule with a learning rate of 0.1.
  
- **Iris Dataset Classification:**  
  Differentiates between Iris-setosa and the other Iris species.
  
- **File-Based Data Loading:**  
  Reads training and test examples from tab-separated value files.
  
- **Interactive Prediction:**  
  Supports manual testing via console input for on-the-fly classification.
  
- **Performance Metrics:**  
  Outputs the number of correctly classified examples and overall accuracy.

## How It Works
1. **Data Loading:**  
   The program reads each line from the provided dataset files, parsing attribute values and class labels. Attributes are converted from strings (handling both dots and commas) to double values.
   
2. **Weight Initialization:**  
   All weights are initialized to zero before training begins.
   
3. **Training:**  
   For each training example, the perceptron calculates an output using the dot product of weights and input data. The weights are then updated based on the error (difference between expected and predicted output).
   
4. **Testing:**  
   After training, the perceptron evaluates each test example, comparing its prediction against the actual label to compute overall accuracy.
   
5. **Manual Input:**  
   The user is prompted to enter attribute values. The perceptron then predicts the class (Iris-setosa or not) based on the learned weights.








# Neural Network for Language Classification

NAI-mpp3 is a simple neural network application built in Java that uses a collection of perceptrons to classify text by language based on character frequency analysis. The project features an interactive Swing-based GUI for real-time text classification and displays model accuracy based on a test dataset.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [How It Works](#how-it-works)
- [About](#about)


## Overview
The application trains a set of perceptrons—one for each language category—using text samples stored in a `training` directory. Each subdirectory under `training` represents a language and contains text files that are used to build feature vectors (letter frequency distributions). After training, the model's performance is evaluated on a test dataset from the `test` directory. Users can also interactively input text through a GUI to see which language the network predicts.

## Features
- **Perceptron-Based Neural Network:**  
  Implements a simple single-layer perceptron algorithm with randomized initial weights and a learning rate of 0.1.
  
- **Automated Training & Testing:**  
  Reads training and testing data from designated directories, builds normalized feature vectors, and trains each perceptron accordingly.
  
- **Interactive GUI:**  
  A Swing-based user interface allows users to input text, get real-time predictions, and view model accuracy.
  
- **Real-Time Accuracy Display:**  
  After training, the application calculates and displays the accuracy of the neural network on the test dataset.
  
- **Language Classification via Character Frequency:**  
  The network analyzes the frequency of each letter (a–z) in the input text to form a 26-dimensional feature vector for classification.

## How It Works
1. **Data Loading:**  
   - The `Main` class traverses the `training` directory using `Files.walkFileTree` to create a map where each key is a language (directory name) and the value is a list of feature vectors (one per file).
   - A similar process is used to load test data from the `test` directory.

2. **Feature Extraction:**  
   - The static method `getVectorFromString` in the `NeuralNetwork` class converts an input string into a normalized 26-element vector representing the frequency of each alphabet character (ignoring non-ASCII characters).

3. **Training:**  
   - The `NeuralNetwork` constructor creates a `Perceptron` for each language.
   - The `train` method iterates over the training data for multiple epochs (50 in the provided code), adjusting weights based on the perceptron learning rule.

4. **Prediction:**  
   - When the user inputs text into the GUI, the network computes the weighted sum for each perceptron.
   - The perceptron with the highest sum “wins” and its corresponding language is predicted.
   - Detailed results are displayed, showing the scores for each perceptron.

5. **GUI Interaction:**  
   - The `GUI` class builds an interface with two main text areas: one for input and one for displaying predictions.
   - Two buttons ("Guess" and "Clear") allow the user to classify the text or clear the inputs.
   - A label shows the calculated accuracy of the network on the test dataset.
     
## About
This project is a lightweight demonstration of applying a perceptron-based neural network for text classification. It highlights the fundamentals of feature extraction, weight adjustment using a simple learning rule, and interactive GUI design using Java Swing. The project is ideal for those interested in introductory neural network implementations and language classification techniques.











# k-means Clustering on Iris Dataset

This project implements a k-means clustering algorithm on a modified version of the Iris dataset. It reads data from a tab-separated file (`iris_training.txt`), preprocesses it (including standardization), and then applies k-means clustering to partition the data into _k_ clusters as specified by the user. Finally, it displays cluster compositions, purity measures, and visualizes the clustering results with a scatter plot.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [How It Works](#how-it-works)
- [Dependencies](#dependencies)


## Overview
This Python script demonstrates how to:
- **Load and preprocess data:** Read the Iris dataset from a file, handle different decimal separators, and standardize the features.
- **Implement k-means clustering:** Randomly initialize centroids, iteratively assign points to the nearest centroid, and update centroid positions until convergence.
- **Evaluate clustering quality:** Compute the composition of clusters and measure group purity.
- **Visualize results:** Plot the clusters and centroids using Matplotlib.

## Features
- **Custom Data Loading:**  
  Reads the Iris training data from a file with a custom tab separator and without headers.
  
- **Data Standardization:**  
  Normalizes the features to have a mean of 0 and a standard deviation of 1.
  
- **k-means Clustering Algorithm:**  
  - Random initialization of _k_ centroids.
  - Iterative assignment of data points based on Euclidean distance.
  - Recalculation of centroids until convergence or a maximum number of iterations is reached.
  
- **Cluster Evaluation:**  
  Displays the number of examples in each cluster and calculates the purity of clusters based on the original labels.
  
- **Visualization:**  
  Produces a scatter plot of the first two standardized features with clusters indicated by color and centroids marked in red.

## How It Works
1. **Data Loading & Preprocessing:**  
   - The script reads the `iris_training.txt` file.
   - It converts feature values from string to float, handling both commas and dots as decimal separators.
   - The features are standardized to improve clustering performance.

2. **Clustering with k-means:**  
   - _k_ centroids are chosen randomly from the dataset.
   - For each data point, the nearest centroid is determined using Euclidean distance.
   - Centroids are updated by calculating the mean of all points assigned to each cluster.
   - The process iterates until the centroids no longer change or the maximum iteration count is reached.

3. **Evaluation & Visualization:**  
   - Cluster compositions are printed along with a measure of purity (the fraction of original labels in each cluster).
   - A scatter plot displays the clusters based on the first two features, with centroids highlighted.

## Dependencies
Pandas: For data manipulation and analysis.
NumPy: For numerical computations and handling arrays.
Matplotlib: For data visualization.

