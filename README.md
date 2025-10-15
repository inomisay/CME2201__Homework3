# Constructing an Evolutionary Tree

This is a university assignment for the **CME 2201** course, focusing on the development of a Java program to represent the Tree of Life (ToL) dataset. [cite_start]The project's core is a custom-built General Tree ADT, which is enhanced with a `Hashtable` for efficient data access[cite: 5225].

This repository contains two separate implementations of the project: **EvolutionaryTree** and **TreeOfLife**.

---
## About The Project

[cite_start]The goal of this project is to model the evolutionary relationships between various biological species using data from the Tree of Life Web Project[cite: 5221]. [cite_start]The program parses two CSV files containing 35,960 species to build a hierarchical tree that illustrates the genetic connections between them[cite: 5227].

[cite_start]The data structure uses a `Hashtable` to provide rapid access to any species record by its unique ID[cite: 5247]. [cite_start]These records are then linked together into a custom **General Tree** structure, where each node can have zero or more children and also maintains a reference to its parent node to allow for tracing ancestor paths[cite: 5246, 5248].

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

[cite_start]The program uses two data files to build the tree[cite: 5239].

### Species File (`treeoflife_nodes.csv`)
This file contains information for each node in the tree.

<table>
  <tr>
    <th>Attribute</th>
    <th>Description</th>
  </tr>
  <tr>
    <td><b>node id</b></td>
    [cite_start]<td>Numeric identifier for the species in the tree[cite: 5241].</td>
  </tr>
  <tr>
    <td><b>node_name</b></td>
    [cite_start]<td>Name of the species or none if unknown[cite: 5241].</td>
  </tr>
  <tr>
    <td><b>child nodes</b></td>
    [cite_start]<td>Number of child nodes[cite: 5241].</td>
  </tr>
  <tr>
    <td><b>leaf node</b></td>
    [cite_start]<td>Whether or not the node is a leaf[cite: 5241].</td>
  </tr>
    <tr>
    <td><b>tolorg_link</b></td>
    [cite_start]<td>Indicates if a page exists on the tolweb.org website for this species[cite: 5241].</td>
  </tr>
  <tr>
    <td><b>extinct</b></td>
    [cite_start]<td>Whether the species is living (0) or extinct (1)[cite: 5241].</td>
  </tr>
  <tr>
    <td><b>confidence</b></td>
    [cite_start]<td>Confidence of placement in the tree structure (0 - confident, 1 - problematic, 2 - unspecified)[cite: 5241].</td>
  </tr>
  <tr>
    <td><b>phylesis</b></td>
    [cite_start]<td>Monophyletic status (0 - monophyletic, 1 - uncertain monophyly, 2 - not monophyletic)[cite: 5241].</td>
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
    [cite_start]<td>The identifier for the ancestor (source) node[cite: 5243].</td>
  </tr>
  <tr>
    <td><b>target_node_id</b></td>
    [cite_start]<td>The identifier for the descendant (target) node[cite: 5243].</td>
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

To Run the TreeOfLife Version:
cd TreeOfLife
javac *.java
java Main
