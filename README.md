Maze Solver Project By Kerem "Kensens" BOYLU

#Türkçe
NASIL ÇALIŞIR?

1. `maze.txt` Dosyasının İçeriğini Matrise Atamak:
   - Program, `readMaze` metodunda verilen `maze.txt` dosyasını okuyarak labirentin karakter dizisini (`maze`) oluşturur.
   - Dosyanın satır ve sütun sayıları belirlenirken `rows` ve `columns` değişkenleri kullanılır.
   - Dosyanın satırları `BufferedReader` yardımıyla okunarak `maze` matrisine atılır. Bu matris labirenti temsil eder ve labirentteki her hücrenin karakterini içerir.

2. Başlangıç Noktasını Tespit Etmek:
   - `readMaze` metodunda labirent dosyasını okurken, `S` karakteri (başlangıç noktası) bulunur ve bu hücrenin koordinatları (`startX` ve `startY`) belirlenir.
   - `S` karakteri, labirentte başlangıç noktasını temsil eder ve bu noktadan hareket ederek çözüm yolları aranacaktır.

3. Tüm Olası Yolları Bulmak:
   - `findAllSolutions` metodunda, Labirentteki tüm olası çözümler aranır.
   - `findAllSolutions` metodunun başlangıç noktası olarak `startX` ve `startY` ile başlar.
   - Labirentte hareket ederken ziyaret edilen hücreleri `visited` matrisiyle işaretler ve geçerli yolu (`currentPath`) bir yığıtta takip eder.
   - Yol bulma sürecinde, her bir yöne (yukarı, aşağı, sağa, sola) özyineleme (recursive) çağrıları yapar ve tüm olası yolları keşfetmeye çalışır.
   - Eğer hedef hücre (`F`) ulaşılırsa, bu yol bir çözüm olarak `solutions` listesine eklenir.

4. En Kısa Yolu İşaretlemek:
   - Tüm olası çözümler (`solutions`) elde edildikten sonra, bu çözümler arasından en kısa yol seçilir.
   - En kısa yolun adım sayısı (`shortestSteps`) hesaplanır ve bu yolun yığıtı (`shortestSolution`) belirlenir.
   - En kısa yol, `updateShortestPath` metodunu kullanarak labirent üzerinde `+` karakteri ile işaretlenir.

5. Sonuçları Göstermek:
   - Eğer hiçbir çözüm yoksa, "No solution found!" mesajı ekrana yazdırılır.
   - Eğer çözümler varsa, tüm çözümler ekrana yazdırılır ve en kısa yolun adım sayısı ve yolu gösterilir.

Bu proje, verilen labirent dosyasını okuyarak labirentteki olası çözümleri bulan ve en kısa yolu işaretleyen bir Java programıdır. Labirentteki tüm olası yollar keşfedilir ve en kısa yol belirlenir.



#English
HOW DOES IT WORK?

1. Reading the Contents of the `maze.txt` File into a Matrix:
   - The program reads the `maze.txt` file and creates the character array representation of the maze (`maze`) in the `readMaze` method.
   - The variables `rows` and `columns` are used to determine the number of rows and columns while reading the file.
   - The lines of the file are read using `BufferedReader` and stored into the `maze` matrix. This matrix represents the maze, and each cell contains a character representing its content.

2. Finding the Starting Point:
   - While reading the maze file in the `readMaze` method, the `S` character (the starting point) is located, and the coordinates of this cell (`startX` and `startY`) are determined.
   - The `S` character represents the starting point in the maze, and the solution paths will be searched starting from this point.

3. Finding All Possible Paths:
   - In the `findAllSolutions` method, all possible solutions in the maze are searched.
   - The method starts with the coordinates `startX` and `startY` as the starting point.
   - While traversing the maze, the visited cells are marked using the `visited` matrix, and the current path (`currentPath`) is tracked using a stack.
   - During the path-finding process, recursive calls are made for each direction (up, down, right, left) to explore all possible paths.
   - If the destination cell (`F`) is reached, this path is added to the `solutions` list as a solution.

4. Marking the Shortest Path:
   - After obtaining all possible solutions (`solutions`), the shortest path is selected among them.
   - The number of steps in the shortest path (`shortestSteps`) is calculated, and the stack representing this path (`shortestSolution`) is determined.
   - The shortest path is marked on the maze using the `+` character with the help of the `updateShortestPath` method.

5. Displaying the Results:
   - If there is no solution, the message "No solution found!" is displayed on the screen.
   - If there are solutions, all solutions are printed on the screen, along with the number of steps and the path of the shortest solution.

This project is a Java program that reads a given maze file, finds all possible solutions in the maze, and marks the shortest path. It explores all possible paths in the maze and determines the shortest one.
