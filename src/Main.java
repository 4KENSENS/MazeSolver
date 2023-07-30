import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        try {
            readMaze("maze.txt");
            visited = new boolean[rows][columns];

            System.out.println("Maze:");
            printMaze();

            List<Stack<int[]>> allSolutions = findAllSolutions(startX, startY, 0, new Stack<>());

            if (allSolutions.isEmpty()) {
                System.out.println("No solution found!");
            } else {
                System.out.println("Number of solutions: " + allSolutions.size());

                // Find the shortest solution
                shortestSteps = Integer.MAX_VALUE;
                shortestSolution = null;

                for (Stack<int[]> solution : allSolutions) {
                    if (solution.peek()[2] < shortestSteps) {
                        shortestSteps = solution.peek()[2];
                        shortestSolution = solution;
                    }
                }

                System.out.println("Shortest solution steps: " + shortestSteps);
                System.out.println("Shortest solution path:");
                updateShortestPath(shortestSolution);
                printMaze();
            }
        } catch (IOException e) {
            System.err.println("Error reading maze file: " + e.getMessage());
        }
    }

    // (Türkçe) Labirenti temsil eden karakter dizisi.
    // (English) Character array representing the maze.
    private static char[][] maze;
    // (Türkçe) Labirentin satır ve sütun sayıları.
    // (English) Number of rows and columns in the maze.
    private static int rows;
    private static int columns;
    // (Türkçe) Labirentin başlangıç noktasının koordinatları.
    // (English) Coordinates of the starting point of the maze.
    private static int startX;
    private static int startY;
    // (Türkçe) Labirentteki tüm hücrelerin ziyaret edilip edilmediğini tutan matris.
    // (English) Matrix that keeps whether all cells in the maze have been visited.
    private static boolean[][] visited;
    // (Türkçe) En kısa çözümün adım sayısı ve yolunu tutan değişkenler.
    // (English) Variables that hold the number of steps and path of the shortest solution.
    private static int shortestSteps;
    private static Stack<int[]> shortestSolution;

    // (Türkçe) Labirent dosyasını okuyarak labirenti oluşturan metot.
    // (English) A method that reads the maze file to create the maze.
    private static void readMaze(String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            rows = 0;
            while ((line = reader.readLine()) != null) {
                columns = line.length();
                rows++;
            }
            maze = new char[rows][columns];

            // Read maze again to fill the maze array
            reader = new BufferedReader(new FileReader(filename));
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < columns; col++) {
                    maze[row][col] = line.charAt(col);
                    if (maze[row][col] == 'S') {
                        startX = row;
                        startY = col;
                    }
                }
                row++;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    // (Türkçe) Labirenti ekrana yazdıran metot.
    // (English) A method that prints the maze to the screen.
    private static void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    // (Türkçe) Labirentteki tüm çözümleri bulan metot.
    // (English) A method that finds all solutions in the maze.
    private static List<Stack<int[]>> findAllSolutions(int x, int y, int step, Stack<int[]> currentPath) {
        List<Stack<int[]>> solutions = new ArrayList<>();
        if (x < 0 || x >= rows || y < 0 || y >= columns || maze[x][y] == '#' || visited[x][y]) {
            // If the cell is invalid, visited before, or a wall,
            // there is no path in this direction, so we return an empty solution list.
            return solutions;
        }

        visited[x][y] = true;

        if (maze[x][y] == 'F') {
            Stack<int[]> solutionPath = new Stack<>();
            solutionPath.addAll(currentPath);
            solutionPath.push(new int[]{x, y, step});
            solutions.add(solutionPath);
        } else {
            currentPath.push(new int[]{x, y, step});

            // Move down
            List<Stack<int[]>> downSolutions = findAllSolutions(x + 1, y, step + 1, currentPath);
            solutions.addAll(downSolutions);

            // Move up
            List<Stack<int[]>> upSolutions = findAllSolutions(x - 1, y, step + 1, currentPath);
            solutions.addAll(upSolutions);

            // Move right
            List<Stack<int[]>> rightSolutions = findAllSolutions(x, y + 1, step + 1, currentPath);
            solutions.addAll(rightSolutions);

            // Move left
            List<Stack<int[]>> leftSolutions = findAllSolutions(x, y - 1, step + 1, currentPath);
            solutions.addAll(leftSolutions);

            currentPath.pop();
        }

        visited[x][y] = false;
        return solutions;
    }

    // (Türkçe) Labirentteki en kısa yolun çözümünü belirten metot.
    // (English) A method that determines the shortest path in the maze.
    private static void updateShortestPath(Stack<int[]> path) {
        while (!path.isEmpty()) {
            int[] step = path.pop();
            int x = step[0];
            int y = step[1];
            if (maze[x][y] == '.') {
                maze[x][y] = '+';
            }
        }
    }
}
