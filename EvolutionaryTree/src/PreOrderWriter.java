import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class PreOrderWriter {
    public static void writePreOrderToFile(List<Integer> traversalOrder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pre-order.txt"))) {
            for (int id : traversalOrder) {
                writer.write(id + " ");
            }
            System.out.println("Pre-order traversal saved to pre-order.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}