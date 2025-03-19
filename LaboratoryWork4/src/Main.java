import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("cells.txt")) {
            int centerX = 5, centerY = 5, radius = 5; // Центр и радиус круга
            while(radius > 0) {
                writer.write("Radius:" + radius + "\n");
                PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingDouble(c -> c.distance));

                for (int x = -radius + centerX; x <= radius + centerX; x++) {
                    for (int y = -radius + centerY; y <= radius + centerY; y++) {
                        double dist = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y));
                        if (dist <= radius) {
                            queue.add(new Cell(x, y, dist));
                        }
                    }
                }
                while (!queue.isEmpty()) {
                    Cell cell = queue.poll();
                    writer.write(cell.x + " " + cell.y + "\n");
                }
                radius--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
