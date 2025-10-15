import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tree {
    private Hashtable<Integer, Node<SpeciesData>> nodeTable;  // Store species nodes
    private Node<SpeciesData> root;  // Root of the tree, if needed

    public Tree() {
        nodeTable = new Hashtable<>();
    }

    public void loadDataset(String nodesFilePath, String linksFilePath) throws IOException {
        // Load nodes from the treeoflife_nodes.csv
        loadNodesFromCSV(nodesFilePath);

        // Load links from treeoflife_links.csv (assuming simple parent-child relationships)
        loadLinksFromCSV(linksFilePath);
    }

    // Method to load nodes from the CSV file and parse them
    public void loadNodesFromCSV(String nodesFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nodesFilePath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                parseNodeData(line);
            }
        }
    }

    // Parse node data from a single CSV line
    private void parseNodeData(String line) {
        try {
            String[] data = parseCSVLine(line); // Parse line into individual elements
            int nodeId = Integer.parseInt(data[0].trim());
            String nodeName = data[1].trim();
            int childNodes = Integer.parseInt(data[2].trim());
            boolean leafNode = Integer.parseInt(data[3].trim()) == 1;
            String tolorgLink = data[4].trim();
            boolean extinct = Integer.parseInt(data[5].trim()) == 1;
            int confidence = Integer.parseInt(data[6].trim());
            int phylesis = Integer.parseInt(data[7].trim());

            // Create a SpeciesData object
            SpeciesData speciesData = new SpeciesData(nodeId, nodeName, childNodes, leafNode, tolorgLink, extinct, confidence, phylesis);
            
            // Create a Node with the SpeciesData object and add it to the nodeTable
            Node<SpeciesData> node = new Node<>(nodeId, speciesData);
            nodeTable.put(nodeId, node);
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            e.printStackTrace();
        }
    }

    // Method to parse a CSV line into individual elements
    private String[] parseCSVLine(String line) {
        Pattern pattern = Pattern.compile("((?:\"[^\"]*\")|[^,]+)");
        Matcher matcher = pattern.matcher(line);
        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group().replace("\"", "").trim());
        }
        return tokens.toArray(new String[0]);
    }

    // Method to load links from the CSV file and establish relationships between nodes
    public void loadLinksFromCSV(String linksFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(linksFilePath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                parseLinkData(line);
            }
        }
    }

    // Parse link data from a single CSV line and link nodes
    private void parseLinkData(String line) {
        try {
            String[] data = line.split(",");
            int sourceNodeId = Integer.parseInt(data[0].trim());
            int targetNodeId = Integer.parseInt(data[1].trim());

            // Get the nodes and link them
            Node<SpeciesData> sourceNode = nodeTable.get(sourceNodeId);
            Node<SpeciesData> targetNode = nodeTable.get(targetNodeId);

            if (sourceNode != null && targetNode != null) {
                sourceNode.addChild(targetNode); // Link the source node to the target node
            }
        } catch (Exception e) {
            System.out.println("Error parsing link data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Search for species by node_id
    public Node<SpeciesData> searchSpecies(int nodeId) {
        return nodeTable.get(nodeId);
    }

    // Print the details of a species
    public void printSpeciesDetails(Node<SpeciesData> node) {
        if (node != null) {
            node.displayInfo();  // Call displayInfo() on the node, which delegates to SpeciesData's displayInfo()
        }
    }

    public Node<SpeciesData> getRoot() {
        return root;
    }
    
    // Save the traversal order to a file
    public static void saveTraversalOrderToFile(List<Integer> order) {
        File outputFile = new File("TraversalPreOrderTree.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            for (Integer number : order) {
                writer.print(number + "-");
            }
            System.out.println("Pre-order traversal has been saved to TraversalPreOrderTree.txt");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    
    // Traverse the tree in pre-order and save to file
    public void traverseTreePreOrder() {
        System.out.println("Traversing the tree in pre-order...");
        root = nodeTable.get(1); // Assuming root node has ID 1
        if (root != null) {
            List<Integer> traversalOrder = new ArrayList<>();
            traversePreOrder(root, traversalOrder);
            saveTraversalOrderToFile(traversalOrder);
        } else {
            System.out.println("Root node not found.");
        }
    }

    // Recursive pre-order traversal method
    private void traversePreOrder(Node<SpeciesData> node, List<Integer> traversalOrder) {
        if (node != null) {
            traversalOrder.add(node.getData().getNodeId()); // Visit the node (nodeId)
            for (Node<SpeciesData> child : node.getChildNodes()) {
                traversePreOrder(child, traversalOrder); // Recurse for each child
            }
        }
    }
    
    // Method to print the subtree in pre-order
    public void printSubtreePreOrder(Node<SpeciesData> node, String indent) {
        if (node != null) {
            System.out.println(indent + node.getData().getNodeId() + "-" + node.getData().getSpeciesName() + 
                               (node.getData().isExtinct() ? " (-)" : " (+)"));
            for (Node<SpeciesData> child : node.getChildNodes()) {
                printSubtreePreOrder(child, indent + "-");
            }
        }
    }
    
    // Method to get the node by ID (to search for the root of the subtree)
    public Node<SpeciesData> getNodeById(int nodeId) {
        return nodeTable.get(nodeId);
    }
    
    // Method to print the ancestor path of a given species
 // Method to print the ancestor path of a given species in reverse
    public void printAncestorPath(Node<SpeciesData> species) {
        List<Node<SpeciesData>> ancestorPath = new ArrayList<>();
        Node<SpeciesData> currentNode = species;

        // Traverse upwards to find all ancestors
        while (currentNode != null) {
            ancestorPath.add(currentNode);  // Add the current node to the path
            currentNode = currentNode.getParentNode();  // Move to the parent node
        }

        // Print the path in reverse order with decreasing indentation
        for (int i = ancestorPath.size() - 1; i >= 0; i--) {
            Node<SpeciesData> node = ancestorPath.get(i);
            String indent = "-".repeat(ancestorPath.size() - 1 - i);  // Decrease indentation for each level
            System.out.println(indent + node.getData().getNodeId() + "-" + node.getData().getSpeciesName() + 
                               (node.getData().isExtinct() ? " (-)" : " (+)"));
        }
    }


    // Method to find the most recent common ancestor of two species
    public Node<SpeciesData> findMostRecentCommonAncestor(int nodeId1, int nodeId2) {
        Node<SpeciesData> species1 = nodeTable.get(nodeId1);
        Node<SpeciesData> species2 = nodeTable.get(nodeId2);

        // Check if either of the species was not found
        if (species1 == null || species2 == null) {
            return null; // Return null if species is not found
        }

        // Get the ancestor paths for both species
        List<Node<SpeciesData>> ancestors1 = getAncestorPath(species1);
        List<Node<SpeciesData>> ancestors2 = getAncestorPath(species2);

        // Loop through the ancestor paths to find the common ancestor
        for (Node<SpeciesData> ancestor1 : ancestors1) {
            for (Node<SpeciesData> ancestor2 : ancestors2) {
                if (ancestor1.equals(ancestor2)) {
                    // Return the common ancestor node
                    return ancestor1;
                }
            }
        }

        // If no common ancestor is found, return null
        return null;
    }

    // Method to get the ancestor path for a given species node
    private List<Node<SpeciesData>> getAncestorPath(Node<SpeciesData> species) {
        List<Node<SpeciesData>> ancestorPath = new ArrayList<>();
        Node<SpeciesData> currentNode = species;

        // Traverse upwards from the given species node to find all ancestors
        while (currentNode != null) {
            ancestorPath.add(currentNode);
            currentNode = currentNode.getParentNode();  // Move to the parent node
        }

        return ancestorPath;
    }

    // Method to calculate the height of the tree starting from a specific node
    public int calculateHeight(Node<SpeciesData> node) {
        if (node == null) {
            return 0;
        }

        int maxHeight = 0;
        for (Node<SpeciesData> child : node.getChildNodes()) {
            int childHeight = calculateHeight(child);
            maxHeight = Math.max(maxHeight, childHeight);
        }

        return maxHeight + 1;
    }

    // Method to calculate the degree of a node (number of children)
    public int calculateDegree(Node<SpeciesData> node) {
        if (node == null) {
            return 0;
        }
        return node.getChildNodes().size(); 
    }

    // Method to calculate the maximum degree of the tree starting from the root
    public int calculateTreeDegree(Node<SpeciesData> root) {
        if (root == null) {
            return 0;
        }

        int maxDegree = calculateDegree(root); // Get the degree of the root node
        for (Node<SpeciesData> child : root.getChildNodes()) {
            maxDegree = Math.max(maxDegree, calculateTreeDegree(child)); // Recursively calculate tree degree
        }

        return maxDegree;
    }

    // Method to calculate the breadth (number of leaf nodes) of the tree
    public int calculateBreadth(Node<SpeciesData> node) {
        if (node == null) {
            return 0;
        }

        if (node.getChildNodes().isEmpty()) { 
            return 1;
        }

        int leafCount = 0;
        for (Node<SpeciesData> child : node.getChildNodes()) {  
            leafCount += calculateBreadth(child);  // Recursively calculate breadth for child nodes
        }

        return leafCount;
    }

    // Method to calculate and print tree metrics like height, degree, and breadth
    public void calculateTreeMetrics() {
        root = nodeTable.get(1); // Assuming root node has ID 1
        if (root != null) {
            int height = calculateHeight(root);
            int degree = calculateTreeDegree(root);
            int breadth = calculateBreadth(root);

            System.out.println("Tree Height: " + height);
            System.out.println("Maximum Degree: " + degree);
            System.out.println("Number of Leaf Nodes (Breadth): " + breadth);
        } else {
            System.out.println("Root node not found.");
        }
    }

    // Methods for finding and printing the longest evolutionary path
    public List<List<Node<SpeciesData>>> findLongestEvolutionaryPaths() {
        List<List<Node<SpeciesData>>> longestPaths = new ArrayList<>();
        List<Node<SpeciesData>> currentPath = new ArrayList<>();

        Node<SpeciesData> rootEntity = nodeTable.get(1); // Assuming root node has ID 1
        if (rootEntity != null) {
            findLongestPathsDFS(rootEntity, currentPath, longestPaths);
        }
        return longestPaths;
    }

    private void findLongestPathsDFS(Node<SpeciesData> currentEntity, List<Node<SpeciesData>> currentPath, List<List<Node<SpeciesData>>> longestPaths) {
        if (currentEntity == null) return;

        currentPath.add(currentEntity);

        if (currentEntity.getChildNodes().isEmpty()) {  // Check for leaf node
            if (longestPaths.isEmpty() || currentPath.size() > longestPaths.get(0).size()) {
                longestPaths.clear();
                longestPaths.add(new ArrayList<>(currentPath));
            } else if (currentPath.size() == longestPaths.get(0).size()) {
                longestPaths.add(new ArrayList<>(currentPath));
            }
        } else {
            for (Node<SpeciesData> child : currentEntity.getChildNodes()) { 
                findLongestPathsDFS(child, currentPath, longestPaths);
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public void printLongestEvolutionaryPath() {
        System.out.println("The longest evolutionary path(s):");
        List<List<Node<SpeciesData>>> longestPaths = findLongestEvolutionaryPaths();
        longestPaths.forEach(path -> {
            path.forEach(n -> System.out.print(n.getData().getNodeId() + "-" + n.getData().getSpeciesName() + " "));
            System.out.println();
        });
    }
}
