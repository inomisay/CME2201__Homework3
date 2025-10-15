import java.util.ArrayList;
import java.util.List;

public class Node<T, U> {
    private T id;              // Generic identifier
    private String name;       // Name of the node
    private int childCount;    // Number of children
    private boolean isLeaf;    // Whether the node is a leaf
    private boolean hasLink;   // Whether the node has a link
    private boolean isExtinct; // Whether the node is extinct
    private int confidence;    // Confidence level
    private int phylesis;      // Phylesis value
    private List<Node<T, U>> children; // List to store child nodes
    private Node<T, U> parent;         // Parent node reference
    private U data;            // Generic data associated with the node

    public Node(T id, String name, int childCount, boolean isLeaf, boolean hasLink, boolean isExtinct, 
                int confidence, int phylesis, U data) {
        this.id = id;
        this.name = name;
        this.childCount = childCount;
        this.isLeaf = isLeaf;
        this.hasLink = hasLink;
        this.isExtinct = isExtinct;
        this.confidence = confidence;
        this.phylesis = phylesis;
        this.children = new ArrayList<>(); // Initialize the list of children
        this.parent = null;               // Parent will be set when linking nodes
        this.data = data;                 
    }

    // Getter for id
    public T getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for data
    public U getData() {
        return data;
    }

    // Getter for isLeaf
    public boolean isLeaf() {
        return isLeaf;
    }

    // Getter for isExtinct
    public boolean isExtinct() {
        return isExtinct;
    }
    
    // Getter for childCount
    public int getChildCount() {
        return childCount;
    }

    // Getter for confidence
    public int getConfidence() {
        return confidence;
    }

    // Getter for phylesis
    public int getPhylesis() {
        return phylesis;
    }

    
    // Getter for the link
    public String getLink() {
        if (hasLink) {
            return "http://tolweb.org/" + name.replace(" ", "_") + "/" + id;
        } else {
            return "No link available";
        }
    }

    // Method to add a child node
    public void addChild(Node<T, U> child) {
        this.children.add(child);
        child.parent = this;  // Set the parent reference for the child node
    }

    // Getter for children
    public List<Node<T, U>> getChildren() {
        return children;
    }

    // Getter for parent
    public Node<T, U> getParent() {
        return parent;
    }

    // Setter for parent (if needed)
    public void setParent(Node<T, U> parent) {
        this.parent = parent;
    }
}
