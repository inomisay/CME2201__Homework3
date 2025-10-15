# Constructing an Evolutionary Tree

This is a university assignment for the **CME 2201** course, focusing on the development of a Java program to represent the Tree of Life (ToL) dataset. The project's core is a custom-built General Tree ADT, which is enhanced with a `Hashtable` for efficient data access.

This repository contains two separate implementations of the project: **EvolutionaryTree** and **TreeOfLife**.

---
## About The Project

The goal of this project is to model the evolutionary relationships between various biological species using data from the Tree of Life Web Project. The program parses two CSV files containing 35,960 species to build a hierarchical tree that illustrates the genetic connections between them.

The data structure uses a `Hashtable` to provide rapid access to any species record by its unique ID. These records are then linked together into a custom **General Tree** structure, where each node can have zero or more children and also maintains a reference to its parent node to allow for tracing ancestor paths.



---
## Project Implementations

This repository contains two distinct versions of the project, located in their respective folders.

### 📁 EvolutionaryTree
*(You can add a brief description of this version's specific approach here. For example: "This version uses a recursive approach for tree traversal...")*

### 📁 TreeOfLife
*(You can add a brief description of this version's specific approach here. For example: "This version uses an iterative approach with stacks and queues for tree traversal...")*

---
## Core Functionalities

Both implementations provide a console menu with the following features:

* **Load Dataset**: Reads the node and link data from the CSV files to construct the tree in memory.
* **Search for Species**: Finds and displays detailed information for a species when given its ID.
* **Tree Traversal**:
    * Traverses the entire tree in pre-order and saves the output to a file named `pre-order.txt`.
    * Prints the complete subtree for any given species, also in pre-order.
* **Ancestor Path**: For a given species, prints its evolutionary path back to the root of the tree.
* **Most Recent Common Ancestor**: Finds and displays the closest common ancestor for any two given species.
* **Calculate Tree Metrics**: Computes and displays the tree's overall **height**, **degree**, and **breadth**. The degree of a tree is the maximum degree of any node in the tree. The breadth is the total number of leaf nodes.
* **Find Longest Path**: Identifies and prints the longest evolutionary path or paths within the tree.

---
## Dataset Details

The program uses two data files to build the tree.

### Species File (`treeoflife_nodes.csv`)
This file contains information for each node in the tree.

<table>
  <tr>
    <th>Attribute</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><b>node id</b></td>
    <td>Numeric identifier for the species in the tree.</td>
  </tr>
  <tr>
    <td><b>node_name</b></td>
    <td>Name of the species or none if unknown.</td>
  </tr>
  <tr>
    <td><b>child nodes</b></td>
    <td>Number of child nodes.</td>
  </tr>
  <tr>
    <td><b>leaf node</b></td>
    <td>Whether or not the node is a leaf.</td>
  </tr>
    <tr>
    <td><b>tolorg_link</b></td>
    <td>Indicates if a page exists on the tolweb.org website for this species.</td>
  </tr>
  <tr>
    <td><b>extinct</b></td>
    <td>Whether the species is living (0) or extinct (1).</td>
  </tr>
  <tr>
    <td><b>confidence</b></td>
    <td>Confidence of placement in the tree structure (0 - confident, 1 - problematic, 2 - unspecified).</td>
  </tr>
  <tr>
    <td><b>phylesis</b></td>
    <td>Monophyletic status (0 - monophyletic, 1 - uncertain monophyly, 2 - not monophyletic).</td>
  </tr>
</table>

### Tree Links File (`treeoflife_links.csv`)
This file defines the parent-child relationships that form the tree structure.

<table>
  <tr>
    <th>Attribute</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><b>source_node_id</b></td>
    <td>The identifier for the ancestor (source) node.</td>
  </tr>
  <tr>
    <td><b>target_node_id</b></td>
    <td>The identifier for the descendant (target) node.</td>
  </tr>
</table>

---
## How to Run

1.  Ensure you have a Java Development Kit (JDK) installed.
2.  Place the `treeoflife_nodes.csv` and `treeoflife_links.csv` files in the same root directory as the `EvolutionaryTree` and `TreeOfLife` folders.
3.  Navigate into the directory of the version you wish to run.
4.  Compile the Java files and run the main class to launch the interactive menu.

#### To Run the `EvolutionaryTree` Version:
```bash
cd EvolutionaryTree
javac *.java
java Main
bash```

#### To Run the `TreeOfLife` Version:
```bash
cd TreeOfLife
javac *.java
java Main
bash```
