import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private char[][] maze;
    private int rows;
    private int columns;
    private List<List<int[]>> allPaths;

    public Main(String filename) throws FileNotFoundException {
        loadMaze(filename);
    }

    private void loadMaze(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        rows = 0;
        columns = 0;

        // Önce kaç satır ve kaç sütun olduğunu tespit edelim
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            rows++;
            columns = line.length();
        }

        scanner.close();

        // Labirenti oluşturalım ve dosyayı tekrar okuyalım
        maze = new char[rows][columns];
        scanner = new Scanner(new File(filename));

        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int col = 0; col < columns; col++) {
                maze[row][col] = line.charAt(col);
            }
            row++;
        }

        scanner.close();
    }

    public void solve() {
        if (maze == null || rows == 0 || columns == 0) {
            System.out.println("Labirent yüklenemedi.");
            return;
        }

        allPaths = new ArrayList<>();
        boolean[][] visited = new boolean[rows][columns];

        // Başlangıç noktasını (S) bulalım
        int startX = -1, startY = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                    break;
                }
            }
            if (startX != -1) {
                break;
            }
        }

        if (startX == -1) {
            System.out.println("Başlangıç noktası (S) bulunamadı.");
            return;
        }

        dfs(startX, startY, new ArrayList<>(), visited);
        printAllPaths();
    }

    private void dfs(int x, int y, List<int[]> path, boolean[][] visited) {
        // Hedefe ulaşıldığında (F - çıkışa ulaşıldığında) labirent çözülmüş olacak
        if (maze[x][y] == '#' || visited[x][y]) {
            return;
        }

        // Hedefe ulaşıldı (F - çıkışa ulaşıldı)
        if (maze[x][y] == 'F') {
            path.add(new int[]{x, y});
            allPaths.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        visited[x][y] = true;
        path.add(new int[]{x, y});

        // DFS'i tetikle
        if (x > 0) {
            dfs(x - 1, y, path, visited); // Yukarı git
        }
        if (x < rows - 1) {
            dfs(x + 1, y, path, visited); // Aşağı git
        }
        if (y > 0) {
            dfs(x, y - 1, path, visited); // Sol'a git
        }
        if (y < columns - 1) {
            dfs(x, y + 1, path, visited); // Sağ'a git
        }

        // Hedefe ulaşılamadı, geri alalım
        path.remove(path.size() - 1);
        visited[x][y] = false;
    }

    private void printAllPaths() {
        if (allPaths.isEmpty()) {
            System.out.println("Labirent çözümü bulunamadı.");
        } else {
            int shortestPathLength = Integer.MAX_VALUE;
            int shortestPathIndex = -1;

            int pathNumber = 1;
            for (int i = 0; i < allPaths.size(); i++) {
                List<int[]> path = allPaths.get(i);
                char[][] solvedMaze = copyMaze(); // Labirenti çözmek için orijinal matristen bir kopya oluştur

                System.out.print("Yol " + pathNumber + ": ");
                for (int[] cell : path) {
                    int x = cell[0];
                    int y = cell[1];
                    solvedMaze[x][y] = '+';
                    System.out.print("(" + x + "." + y + ") ");
                }

                int pathLength = path.size();
                System.out.println("\nYol Uzunluğu: " + pathLength);

                // En kısa yolu tespit et
                if (pathLength < shortestPathLength) {
                    shortestPathLength = pathLength;
                    shortestPathIndex = i;
                }

                // Çözülmüş labirenti ekrana yazdır
                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < columns; k++) {
                        System.out.print(solvedMaze[j][k]);
                    }
                    System.out.println();
                }

                pathNumber++;
            }

            if (shortestPathIndex != -1) {
                System.out.println("\nEn Kısa Yol Uzunluğu: " + shortestPathLength);
                System.out.println("En Kısa Yol: ");
                List<int[]> shortestPath = allPaths.get(shortestPathIndex);
                for (int[] cell : shortestPath) {
                    int x = cell[0];
                    int y = cell[1];
                    System.out.print("(" + x + "." + y + ") ");
                }
                // En kısa yolun indeksine göre yol numarasını belirle
                int shortestPathNumber = shortestPathIndex+1;
                System.out.println("\nEn Kısa Yol " + shortestPathNumber);
            }
        }
    }


    private char[][] copyMaze() {
        char[][] copy = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(maze[i], 0, copy[i], 0, columns);
        }
        return copy;
    }

    public static void main(String[] args) {
        try {
            Main mazeSolver = new Main("maze.txt");
            mazeSolver.solve();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        }
    }
}
