import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();
        boolean datasetLoaded = false;

        // Predefined file paths
        String nodesFilePath = "treeoflife_nodes.csv";
        String linksFilePath = "treeoflife_links.csv";

        while (true) {
            System.out.println("\n1. Load dataset");
            System.out.println("2. Search for species");
            System.out.println("3. Traverse tree (Pre-order and save to file)");
            System.out.println("4. Print subtree of a species in pre-order");
            System.out.println("5. Print ancestor path of a species");
            System.out.println("6. Find most recent common ancestor of two species");
            System.out.println("7. Calculate tree metrics (Height, Degree, and Breadth)");
            System.out.println("8. Find the longest evolutionary path(s)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("----------------------------");
                        if (datasetLoaded) {
                            System.out.println("Dataset has already been loaded.");
                        } else {
                            tree.loadDataset(nodesFilePath, linksFilePath);
                            datasetLoaded = true;
                            System.out.println("Dataset loaded successfully.");
                        }
                        System.out.println("----------------------------");
                        break;

                    case 2:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.print("Enter species ID to search: ");
                            int nodeId = scanner.nextInt();
                            Node<SpeciesData> species = tree.searchSpecies(nodeId);
                            if (species != null) {
                                tree.printSpeciesDetails(species);
                            } else {
                                System.out.println("Species not found with ID " + nodeId);
                            }
                        }
                        System.out.println("----------------------------");
                        break;

                    case 3:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.println("Traversing tree in pre-order...");
                            tree.traverseTreePreOrder();
                            System.out.println("Traversal saved successfully.");
                        }
                        System.out.println("----------------------------");
                        break;

                    case 4:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.print("Enter species ID to print the subtree: ");
                            int nodeId = scanner.nextInt();
                            Node<SpeciesData> species = tree.getNodeById(nodeId);
                            if (species != null) {
                                System.out.println("Subtree of species with ID " + nodeId + ":");
                                tree.printSubtreePreOrder(species, "");
                            } else {
                                System.out.println("Species not found with ID " + nodeId);
                            }
                        }
                        System.out.println("----------------------------");
                        break;

                    case 5:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.print("Enter species ID to print ancestor path: ");
                            int nodeId = scanner.nextInt();
                            Node<SpeciesData> species = tree.getNodeById(nodeId);
                            if (species != null) {
                                System.out.println("Ancestor path of species with ID " + nodeId + ":");
                                tree.printAncestorPath(species);
                            } else {
                                System.out.println("Species not found with ID " + nodeId);
                            }
                        }
                        System.out.println("----------------------------");
                        break;

                    case 6:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.print("Enter first species ID: ");
                            int nodeId1 = scanner.nextInt();
                            System.out.print("Enter second species ID: ");
                            int nodeId2 = scanner.nextInt();

                            Node<SpeciesData> mrca = tree.findMostRecentCommonAncestor(nodeId1, nodeId2);
                            if (mrca != null) {
                                // Retrieve species names for both node IDs
                                String speciesName1 = tree.getNodeById(nodeId1).getData().getSpeciesName();
                                String speciesName2 = tree.getNodeById(nodeId2).getData().getSpeciesName();
                                String commonAncestorName = mrca.getData().getSpeciesName();

                                // Print the message with species names included
                                System.out.println("The most recent common ancestor of " +
                                        nodeId1 + "-" + speciesName1 + " and " + 
                                        nodeId2 + "-" + speciesName2 + " is " +
                                        mrca.getData().getNodeId() + "-" + commonAncestorName);
                            } else {
                                System.out.println("No common ancestor found.");
                            }
                        }
                        System.out.println("----------------------------");
                        break;

                    case 7:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.println("Calculating tree metrics (Height, Degree, and Breadth)...");
                            tree.calculateTreeMetrics();
                        }
                        System.out.println("----------------------------");
                        break;

                    case 8:
                        System.out.println("----------------------------");
                        if (!datasetLoaded) {
                            System.out.println("Please load the dataset first.");
                        } else {
                            System.out.println("Finding the longest evolutionary path(s)...");
                            tree.printLongestEvolutionaryPath();
                        }
                        System.out.println("----------------------------");
                        break;

                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
