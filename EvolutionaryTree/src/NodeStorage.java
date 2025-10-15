import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Custom storage for generic nodes
class NodeStorage<T, U> implements INodeStorage<T, U> {
    private List<Node<T, U>> nodes;

    public NodeStorage() {
        this.nodes = new ArrayList<>();
    }

    @Override
    public void add(Node<T, U> node) {
        nodes.add(node);
    }

    @Override
    public Node<T, U> getById(T id) {
        for (Node<T, U> node : nodes) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null; // Not found
    }

    @Override
    public List<Node<T, U>> getAll() {
        return nodes;
    }

    @Override
    public void traversePreOrder(Node<T, U> node, List<T> traversalOrder) {
        if (node != null) {
            traversalOrder.add(node.getId()); // Visit the node
            for (Node<T, U> child : node.getChildren()) {
                traversePreOrder(child, traversalOrder);
            }
        }
    }

    @Override
    public void printSubtree(Node<T, U> node, List<T> subtree, int depth) {
        if (node != null) {
            String indent = ".".repeat(depth * 2); // Two spaces per depth level
            System.out.print(indent);
            System.out.print(node.getId() + "-" + node.getName() + " ");
            System.out.println(node.isExtinct() ? "(-)" : "(+)");
            subtree.add(node.getId());

            for (Node<T, U> child : node.getChildren()) {
                printSubtree(child, subtree, depth + 1);
            }
        }
    }

    @Override
    public List<String> getAncestorPath(Node<T, U> node) {
        List<Node<T, U>> ancestors = new ArrayList<>();
        
        // Traverse and collect ancestors
        while (node != null) {
            ancestors.add(node);
            node = node.getParent();
        }
        
        // Build the path with proper indentation
        List<String> ancestorPath = new ArrayList<>();
        int depth = ancestors.size() - 1; // Start depth from the root ancestor

        for (int i = depth; i >= 0; i--) {
            Node<T, U> ancestor = ancestors.get(i);
            String indent = ".".repeat((depth - i) * 4); // Indentation increases as depth decreases
            String status = ancestor.isExtinct() ? "(-)" : "(+)";
            ancestorPath.add(indent + ancestor.getId() + "-" + ancestor.getName() + " " + status);
        }

        return ancestorPath;
    }


    @Override
    public Node<T, U> findMostRecentCommonAncestor(Node<T, U> firstSpecies, Node<T, U> secondSpecies) {
        List<Node<T, U>> firstAncestorPath = getAncestorPathList(firstSpecies);
        List<Node<T, U>> secondAncestorPath = getAncestorPathList(secondSpecies);

        Node<T, U> commonAncestor = null;
        int i = 0;
        while (i < firstAncestorPath.size() && i < secondAncestorPath.size()) {
            if (firstAncestorPath.get(i).getId().equals(secondAncestorPath.get(i).getId())) {
                commonAncestor = firstAncestorPath.get(i);
            } else {
                break;
            }
            i++;
        }
        return commonAncestor;
    }

    private List<Node<T, U>> getAncestorPathList(Node<T, U> node) {
        List<Node<T, U>> ancestorPath = new ArrayList<>();
        while (node != null) {
            ancestorPath.add(node);
            node = node.getParent();
        }
        Collections.reverse(ancestorPath);
        return ancestorPath;
    }

    @Override
    public int calculateHeight(Node<T, U> node) {
        if (node == null) {
            return 0;
        }

        int maxHeight = 0;
        for (Node<T, U> child : node.getChildren()) {
            int childHeight = calculateHeight(child);
            maxHeight = Math.max(maxHeight, childHeight);
        }

        return maxHeight + 1;
    }

    @Override
    public int calculateDegree(Node<T, U> node) {
        if (node == null) {
            return 0;
        }
        return node.getChildren().size();
    }

    @Override
    public int calculateTreeDegree(Node<T, U> root) {
        if (root == null) {
            return 0;
        }

        int maxDegree = calculateDegree(root);
        for (Node<T, U> child : root.getChildren()) {
            maxDegree = Math.max(maxDegree, calculateTreeDegree(child));
        }

        return maxDegree;
    }

    @Override
    public int calculateBreadth(Node<T, U> node) {
        if (node == null) {
            return 0;
        }

        if (node.getChildren().isEmpty()) {
            return 1;
        }

        int leafCount = 0;
        for (Node<T, U> child : node.getChildren()) {
            leafCount += calculateBreadth(child);
        }

        return leafCount;
    }

    @Override
    public List<List<Node<T, U>>> findLongestEvolutionaryPaths() {
        List<List<Node<T, U>>> longestPaths = new ArrayList<>();
        List<Node<T, U>> currentPath = new ArrayList<>();

        Node<T, U> rootNode = getById(getRootId());
        if (rootNode != null) {
            findLongestPathsDFS(rootNode, currentPath, longestPaths);
        }
        return longestPaths;
    }

    private T getRootId() {
        return nodes.isEmpty() ? null : nodes.get(0).getId(); // Assuming the first node is the root
    }

    private void findLongestPathsDFS(Node<T, U> currentNode, List<Node<T, U>> currentPath, List<List<Node<T, U>>> longestPaths) {
        if (currentNode == null) return;

        currentPath.add(currentNode);

        if (currentNode.isLeaf()) {
            if (longestPaths.isEmpty() || currentPath.size() > longestPaths.get(0).size()) {
                longestPaths.clear();
                longestPaths.add(new ArrayList<>(currentPath));
            } else if (currentPath.size() == longestPaths.get(0).size()) {
                longestPaths.add(new ArrayList<>(currentPath));
            }
        } else {
            for (Node<T, U> child : currentNode.getChildren()) {
                findLongestPathsDFS(child, currentPath, longestPaths);
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }
}
