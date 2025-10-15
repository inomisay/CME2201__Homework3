import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvolutionaryTreeReader {
    private static NodeStorage<Integer, String> nodeStorage = new NodeStorage<>(); // Use specific types for Node
    private static List<Link> links = new ArrayList<>();

    public static void readNodes() throws IOException {
        String nodesFile = "treeoflife_nodes.csv"; // File name hardcoded
        try (BufferedReader br = new BufferedReader(new FileReader(nodesFile))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                try {
                    String[] data = parseCSVLine(line);

                    int nodeId = Integer.parseInt(data[0].trim());
                    String nodeName = data[1].trim();
                    int childNodes = Integer.parseInt(data[2].trim());
                    boolean leafNode = Integer.parseInt(data[3].trim()) == 1;
                    boolean tolorgLink = Integer.parseInt(data[4].trim()) == 1;
                    boolean extinct = Integer.parseInt(data[5].trim()) == 1;
                    int confidence = Integer.parseInt(data[6].trim());
                    int phylesis = Integer.parseInt(data[7].trim());

                    Node<Integer, String> node = new Node<>(nodeId, nodeName, childNodes, leafNode, tolorgLink, extinct, confidence, phylesis, nodeName);
                    nodeStorage.add(node); // Add the node to the storage
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                    throw e;
                }
            }
        }
    }

    private static String[] parseCSVLine(String line) {
        Pattern csvPattern = Pattern.compile("((?:\"[^\"]*\")|[^,]+)");
        Matcher matcher = csvPattern.matcher(line);
        List<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group().replace("\"", "").trim());
        }
        return tokens.toArray(new String[0]);
    }

    public static void readLinks() throws IOException {
        String linksFile = "treeoflife_links.csv"; // File name hardcoded
        try (BufferedReader br = new BufferedReader(new FileReader(linksFile))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int sourceNodeId = Integer.parseInt(data[0].trim());
                int targetNodeId = Integer.parseInt(data[1].trim());

                Node<Integer, String> sourceNode = nodeStorage.getById(sourceNodeId);
                Node<Integer, String> targetNode = nodeStorage.getById(targetNodeId);

                if (sourceNode != null && targetNode != null) {
                    sourceNode.addChild(targetNode);  // Set the child for the source node
                    //System.out.println("Linking source node " + sourceNodeId + " -> child " + targetNodeId); // Debug line
                } else {
                    //System.out.println("Error linking nodes: " + sourceNodeId + " -> " + targetNodeId);
                }
            }
        }
    }

    public static NodeStorage<Integer, String> getNodeStorage() {
        return nodeStorage;
    }

    public static List<Link> getLinks() {
        return links;
    }
}
