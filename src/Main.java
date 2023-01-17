import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;


public class Main extends functions {


    public static int ROWS;
    public static int COLUMNS;
    public static String filepath;

    public static String[] friends;
    public static String[] studentNames;
    public static boolean[] glasses;
    public static ArrayList<Student> students;

    public static void main(String[] args) throws IOException {


        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int ROWS = input.nextInt();
        System.out.println("Enter the number of columns: ");
        int COLUMNS = input.nextInt();

        filepath = "";
        // if the file path exists, then it will read the file otherwise keep asking

        while (filepath.equals("")) {
            System.out.println("Enter the file path and dont append .csv: ");
            filepath = input.next() + ".csv";

            if (!Files.exists(Paths.get(filepath))) {
                System.out.println("File does not exist");
                filepath = "";
            }
        }





        String unknown = filepath;

        Path path = Paths.get(filepath);

        ArrayList<String> seatingData = new ArrayList<>();
        ArrayList<String> seatingDataFriend = new ArrayList<>();
        ArrayList<String> seatingDataGlasses = new ArrayList<>();

        ArrayList<String[]> seatingDataArray = new ArrayList<>();

        seatingData = createSeatingData(path);
        seatingDataFriend = createSeatingData(path);
        seatingDataGlasses = createSeatingData(path);

        // System.out.println(seatingData);

        //printStudents(seatingData);
        friends = partitionFriends(seatingData);
        studentNames = partitionStudents(seatingDataFriend);
        glasses = partitionGlassesList(seatingDataGlasses);


        // create student arr list of objects
        students = createStudentArr(friends, studentNames, glasses);
        //printKidsList(students);
        //printStudentsArray(friends);
        // printKidsList(students);


        Student[][] board = createEmptyBoard(ROWS, COLUMNS, students);
        // printBoard(board); -- prints pre board


        board = optmialBoard(board);

        // printBoard(board); -- prints post board

        //writeDataLineByLine(board, ROWS, COLUMNS);

        File csvFile = new File("Output.csv");
        FileWriter fileWriter = new FileWriter(csvFile);

        //write header line here if you need.

        String[][] names = new String[ROWS][COLUMNS];
        for (int i =0; i<board.length; i++){
            for (int j = 0; j<board[i].length; j++) {
                if (board[i][j] != null) {
                    names[i][j] = board[i][j].getName();
                    while (names[i][j].length() < 20) {
                        names[i][j] += " ";
                    }
                } else {
                    names[i][j] = "FILLER";
                    while (names[i][j].length() < 20) {
                        names[i][j] += " ";
                    }
                }
            }
        }

        writeToCSV(names, "Output.csv");








    }
}


