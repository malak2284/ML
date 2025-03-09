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


