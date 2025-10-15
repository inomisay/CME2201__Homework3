import java.util.List;
import java.util.ArrayList;

public class Node<T extends SpeciesData> {
    int nodeId;
    T data;
    List<Node<T>> childNodes;
    Node<T> parentNode;

    // Constructor
    public Node(int nodeId, T data) {
        this.nodeId = nodeId;
        this.data = data;
        this.childNodes = new ArrayList<>();
    }

    // Add a child node to the current node
    public void addChild(Node<T> child) {
        this.childNodes.add(child);
        child.setParentNode(this);
    }

    // Getter and Setter for parentNode
    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    public Node<T> getParentNode() {
        return this.parentNode;
    }

    // Method to check if the node is a leaf node (no children)
    public boolean isLeafNode() {
        return childNodes.isEmpty();
    }

    // Method to display the species information
    public void displayInfo() {
        if (this.data != null) {
            this.data.displayInfo();  // Call the displayInfo() method from SpeciesData
        } else {
            System.out.println("No species data available.");
        }
    }

    // Get data for this node
    public T getData() {
        return this.data;
    }

    // Get the first child node (if exists, otherwise null)
    public Node<T> getLeft() {
        if (childNodes.size() > 0) {
            return childNodes.get(0);  // Return the first child as the "left" node
        }
        return null;
    }

    // Get the second child node (if exists, otherwise null)
    public Node<T> getRight() {
        if (childNodes.size() > 1) {
            return childNodes.get(1);  // Return the second child as the "right" node
        }
        return null;
    }

    // Getter for all child nodes (could be useful for traversals, etc.)
    public List<Node<T>> getChildNodes() {
        return this.childNodes;
    }
}
