import java.util.List;

public interface INodeStorage<T, U> {

    /**
     * Adds a node to the storage.
     * 
     * @param node the node to be added
     */
    void add(Node<T, U> node);

    /**
     * Retrieves a node by its ID.
     * 
     * @param id the ID of the node
     * @return the node with the given ID, or null if not found
     */
    Node<T, U> getById(T id);

    /**
     * Retrieves all nodes from the storage.
     * 
     * @return a list of all nodes
     */
    List<Node<T, U>> getAll();

    /**
     * Performs a pre-order traversal of the tree.
     * 
     * @param node the starting node
     * @param traversalOrder a list to store the traversal order of node IDs
     */
    void traversePreOrder(Node<T, U> node, List<T> traversalOrder);

    /**
     * Prints the subtree of a given node in pre-order with indentation.
     * 
     * @param node the root node of the subtree
     * @param subtree a list to store the node IDs in the subtree
     * @param depth the current depth for indentation
     */
    void printSubtree(Node<T, U> node, List<T> subtree, int depth);

    /**
     * Gets the ancestor path of a given species node.
     * 
     * @param node the node representing the species
     * @return a list of strings representing the ancestor path
     */
    List<String> getAncestorPath(Node<T, U> node);

    /**
     * Finds the most recent common ancestor of two species nodes.
     * 
     * @param firstSpecies the first species node
     * @param secondSpecies the second species node
     * @return the most recent common ancestor node, or null if not found
     */
    Node<T, U> findMostRecentCommonAncestor(Node<T, U> firstSpecies, Node<T, U> secondSpecies);

    /**
     * Calculates the height of the tree starting from a given node.
     * 
     * @param node the starting node
     * @return the height of the tree
     */
    int calculateHeight(Node<T, U> node);

    /**
     * Calculates the degree of a given node (number of children).
     * 
     * @param node the node to calculate the degree for
     * @return the degree of the node
     */
    int calculateDegree(Node<T, U> node);

    /**
     * Calculates the maximum degree of the tree.
     * 
     * @param root the root node of the tree
     * @return the maximum degree in the tree
     */
    int calculateTreeDegree(Node<T, U> root);

    /**
     * Calculates the breadth of the tree (number of leaf nodes).
     * 
     * @param node the starting node
     * @return the number of leaf nodes in the tree
     */
    int calculateBreadth(Node<T, U> node);

    /**
     * Finds the longest evolutionary paths in the tree.
     * 
     * @return a list of the longest paths, each path represented as a list of nodes
     */
    List<List<Node<T, U>>> findLongestEvolutionaryPaths();
}
