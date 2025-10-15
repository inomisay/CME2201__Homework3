public class SpeciesData {
    private int nodeId; // Numeric identifier for the species in the tree
    private String speciesName; // Name of the species or "none" if unknown
    private int childNodes; // Number of child nodes
    private boolean isLeafNode; // Whether or not the node is a leaf
    private String tolorgLink; // Link to the TolWeb website describing this species
    private boolean isExtinct; // Whether the species is living (false) or extinct (true)
    private int confidence; // Confidence of placement in the tree: 0 - confident, 1 - problematic, 2 - unspecified
    private int phylesis; // Phylesis: 0 - monophyletic, 1 - uncertain monophyly, 2 - not monophyletic

    // Constructor to initialize the SpeciesData object
    public SpeciesData(int nodeId, String speciesName, int childNodes, boolean isLeafNode, String tolorgLink,
                       boolean isExtinct, int confidence, int phylesis) {
        this.nodeId = nodeId;
        this.speciesName = speciesName;
        this.childNodes = childNodes;
        this.isLeafNode = isLeafNode;
        this.tolorgLink = tolorgLink;
        this.isExtinct = isExtinct;
        this.confidence = confidence;
        this.phylesis = phylesis;
    }

    // Getters and setters
    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public int getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(int childNodes) {
        this.childNodes = childNodes;
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setLeafNode(boolean leafNode) {
        isLeafNode = leafNode;
    }

    public String getTolorgLink() {
        return tolorgLink;
    }

    public void setTolorgLink(String tolorgLink) {
        this.tolorgLink = tolorgLink;
    }

    public boolean isExtinct() {
        return isExtinct;
    }

    public void setExtinct(boolean extinct) {
        isExtinct = extinct;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int getPhylesis() {
        return phylesis;
    }

    public void setPhylesis(int phylesis) {
        this.phylesis = phylesis;
    }

    // Override the toString method to display the species name in a readable format
    @Override
    public String toString() {
        return speciesName;
    }

    // Method to display detailed information about the species in the requested format
    public void displayInfo() {
        System.out.println("Id: " + nodeId);
        System.out.println("Name: " + speciesName);
        System.out.println("Child count: " + childNodes);
        System.out.println("Leaf node: " + (isLeafNode ? "yes" : "no"));
        System.out.println("Link: http://tolweb.org/" + speciesName.replace(" ", "_") + "/" + nodeId);
        System.out.println("Extinct: " + (isExtinct ? "yes" : "no"));
        System.out.println("Confidence: " + (confidence == 0 ? "no" : "yes"));
        System.out.println("Phylesis: " + (phylesis == 0 ? "no" : "yes"));
    }
}
