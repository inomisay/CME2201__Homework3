# Constructing an Evolutionary Tree

[cite_start]This is a university assignment for the **CME 2201** course, focusing on the development of a Java program to represent the Tree of Life (ToL) dataset[cite: 5218, 5225]. [cite_start]The core of the project is a custom-built General Tree ADT, which is enhanced with a `Hashtable` for efficient data access[cite: 5220, 5225].

This repository contains two separate implementations of the solution: **EvolutionaryTree** and **TreeOfLife**.

---

## About The Project

[cite_start]The goal of this project is to model the evolutionary relationships between various biological species using data from the Tree of Life Web Project[cite: 5221, 5225]. [cite_start]The program parses two CSV files containing 35,960 species to build a hierarchical tree that illustrates the genetic connections between them[cite: 5227, 5228].

[cite_start]The data structure uses a `Hashtable` to provide rapid access to any species record by its unique ID[cite: 5247]. [cite_start]These records are then linked together into a custom **General Tree** structure, where each node can have zero or more children and also maintains a reference to its parent node to allow for tracing ancestor paths[cite: 5246, 5248].

---

## Project Implementations

This repository contains two distinct versions of the project, located in their respective folders.

### EvolutionaryTree

*(You can add a brief description of the specific approach or data structure choices for your first implementation here.)*

### TreeOfLife

*(You can add a brief description of the specific approach or data structure choices for your second implementation here.)*

---

## Core Functionalities

Both implementations provide a console menu with the following features:

* [cite_start]**Load Dataset**: Reads the node and link data from the CSV files to construct the tree in memory[cite: 5276].
* [cite_start]**Search for Species**: Finds and displays detailed information for a species when given its ID[cite: 5278].
* **Tree Traversal**:
    * [cite_start]Traverses the entire tree in pre-order and saves the output to a file named `pre-order.txt`[cite: 5291].
    * [cite_start]Prints the complete subtree for any given species, also in pre-order[cite: 5294].
* [cite_start]**Ancestor Path**: For a given species, prints its evolutionary path back to the root of the tree[cite: 5305].
* [cite_start]**Most Recent Common Ancestor**: Finds and displays the closest common ancestor for any two given species[cite: 5316].
* [cite_start]**Calculate Tree Metrics**: Computes and displays the tree's overall **height**, **degree**, and **breadth**[cite: 5320]. [cite_start]The degree of a tree is the maximum degree of any node in the tree[cite: 5322]. [cite_start]The breadth is the total number of leaf nodes[cite: 5323].
* [cite_start]**Find Longest Path**: Identifies and prints the longest evolutionary path or paths within the tree[cite: 5325].

---

## Dataset Details

The program uses two data files:

### [cite_start]Species File (`treeoflife_nodes.csv`) [cite: 5240]

This file contains information for each node in the tree. Its attributes are:
* [cite_start]**node id**: A numeric identifier for the species[cite: 5241].
* [cite_start]**node\_name**: The name of the species[cite: 5241].
* [cite_start]**child nodes**: The number of child nodes[cite: 5241].
* [cite_start]**leaf node**: Indicates if the node is a leaf[cite: 5241].
* [cite_start]**tolorg\_link**: Indicates if a page exists on the `tolweb.org` website for the species[cite: 5241].
* [cite_start]**extinct**: Indicates if the species is living (0) or extinct (1)[cite: 5241].
* [cite_start]**confidence**: Confidence in the tree structure's placement (0: confident, 1: problematic, 2: unspecified)[cite: 5241].
* [cite_start]**phylesis**: Indicates if the group is monophyletic (0), of uncertain monophyly (1), or not monophyletic (2)[cite: 5241].

### [cite_start]Tree Links File (`treeoflife_links.csv`) [cite: 5242]

This file defines the parent-child relationships that form the tree structure. Its attributes are:
* [cite_start]**source\_node\_id**: The identifier for the ancestor (source) node[cite: 5243].
* [cite_start]**target\_node\_id**: The identifier for the descendant (target) node[cite: 5243].

---

## How to Run

1.  [cite_start]Ensure you have a Java Development Kit (JDK) installed[cite: 5333].
2.  Place the `treeoflife_nodes.csv` and `treeoflife_links.csv` files in the project's root directory.
3.  Navigate into the directory of the version you wish to run (`EvolutionaryTree` or `TreeOfLife`).
4.  Compile all `.java` files and run the main class to launch the interactive menu.

#### To Run the `EvolutionaryTree` Version:
```bash
# Example commands (adjust paths as needed)
cd EvolutionaryTree/
javac *.java
java Main
#### To Run the `TreeOfLife` Version:
```bash
# Example commands (adjust paths as needed)
cd TreeOfLife/
javac *.java
java Main
