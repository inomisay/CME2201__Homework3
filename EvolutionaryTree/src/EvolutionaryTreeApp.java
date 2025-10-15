import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EvolutionaryTreeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NodeStorage<Integer, String> nodeStorage = EvolutionaryTreeReader.getNodeStorage();
        Node<Integer, String> rootNode = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Load Dataset");
            System.out.println("2. Display All Nodes");
            System.out.println("3. Search for a Species");
            System.out.println("4. Traverse the Tree (Pre-order)");
            System.out.println("5. Print Subtree of a Species");
            System.out.println("6. Print Ancestor Path");
            System.out.println("7. Find Most Recent Common Ancestor");
            System.out.println("8. Calculate Tree Height, Degree, and Breadth");
            System.out.println("9. Print Longest Evolutionary Path");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        try {
                            EvolutionaryTreeReader.readNodes();
                            EvolutionaryTreeReader.readLinks();
                            System.out.println("Dataset loaded successfully!");
                        } catch (IOException e) {
                            System.out.println("Error loading dataset: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("All Nodes:");
                        for (Node<Integer, String> n : nodeStorage.getAll()) {
                            System.out.println("ID: " + n.getId() + ", Name: " + n.getName());
                        }
                        break;

                    case 3:
                        System.out.print("Enter species ID to search: ");
                        int id = scanner.nextInt();
                        Node<Integer, String> node = nodeStorage.getById(id);
                        if (node != null) {
                            System.out.println("Id: " + node.getId());
                            System.out.println("Name: " + node.getName());
                            System.out.println("Child count: " + node.getChildCount());
                            System.out.println("Leaf node: " + (node.isLeaf() ? "Yes" : "No"));
                            System.out.println("Link: " + node.getLink());
                            System.out.println("Extinct: " + (node.isExtinct() ? "Yes" : "No"));
                            System.out.println("Confidence: " + (node.getConfidence() == 0 ? "No" : "Yes"));
                            System.out.println("Phylesis: " + (node.getPhylesis() == 0 ? "No" : "Yes"));
                        } else {
                            System.out.println("Species not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Traversing the tree in pre-order...");
                        rootNode = nodeStorage.getById(1); // Find root node by ID
                        if (rootNode != null) {
                            List<Integer> traversalOrder = new ArrayList<>();
                            nodeStorage.traversePreOrder(rootNode, traversalOrder); // Pre-order traversal
                            PreOrderWriter.writePreOrderToFile(traversalOrder); // Save to file
                            System.out.println("Pre-order traversal saved to file.");
                        } else {
                            System.out.println("Root node not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter species ID to print the subtree: ");
                        int subtreeId = scanner.nextInt();
                        Node<Integer, String> subtreeNode = nodeStorage.getById(subtreeId);
                        if (subtreeNode != null) {
                            List<Integer> subtree = new ArrayList<>();
                            nodeStorage.printSubtree(subtreeNode, subtree, 0);  // Start with depth 0
                        } else {
                            System.out.println("Species not found.");
                        }
                        break;

                    case 6:
                        System.out.print("Enter species ID to print ancestor path: ");
                        int speciesId = scanner.nextInt();
                        Node<Integer, String> species = nodeStorage.getById(speciesId);
                        if (species != null) {
                            List<String> ancestorPath = nodeStorage.getAncestorPath(species);
                            for (String ancestor : ancestorPath) {
                                System.out.println(ancestor);
                            }
                        } else {
                            System.out.println("Species not found.");
                        }
                        break;

                    case 7:
                        System.out.print("Enter the first species ID: ");
                        int firstId = scanner.nextInt();
                        System.out.print("Enter the second species ID: ");
                        int secondId = scanner.nextInt();
                        Node<Integer, String> firstSpecies = nodeStorage.getById(firstId);
                        Node<Integer, String> secondSpecies = nodeStorage.getById(secondId);
                        if (firstSpecies != null && secondSpecies != null) {
                            Node<Integer, String> commonAncestor = nodeStorage.findMostRecentCommonAncestor(firstSpecies, secondSpecies);
                            if (commonAncestor != null) {
                                System.out.println("The most recent common ancestor of " + firstSpecies.getId() + "-" + firstSpecies.getName() + " and "
                                        + secondSpecies.getId() + "-" + secondSpecies.getName() + " is " + commonAncestor.getId() + "-" + commonAncestor.getName());
                            } else {
                                System.out.println("No common ancestor found.");
                            }
                        } else {
                            System.out.println("One or both species not found.");
                        }
                        break;

                    case 8:
                        rootNode = nodeStorage.getById(1); // Find root node by ID
                        if (rootNode != null) {
                            int height = nodeStorage.calculateHeight(rootNode);
                            int degree = nodeStorage.calculateTreeDegree(rootNode);  // Calculate the maximum degree
                            int breadth = nodeStorage.calculateBreadth(rootNode);

                            System.out.println("Tree Height: " + height);
                            System.out.println("Maximum Degree: " + degree);
                            System.out.println("Number of Leaf Nodes (Breadth): " + breadth);
                        } else {
                            System.out.println("Root node not found.");
                        }
                        break;

                    case 9:
                        System.out.println("Printing the longest evolutionary path(s):");
                        List<List<Node<Integer, String>>> longestPaths = nodeStorage.findLongestEvolutionaryPaths();
                        for (List<Node<Integer, String>> path : longestPaths) {
                            for (Node<Integer, String> n : path) {
                                System.out.print(n.getId() + "-" + n.getName() + " ");
                            }
                            System.out.println();  // Move to next line after printing one path
                        }
                        break;

                    case 10:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please try again.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
