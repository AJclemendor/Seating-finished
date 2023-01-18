import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Seating extends functions{

    public static String weight = "filler";
    public static String setting = "filler";
    public static int intensity = 3;
    public static int row_weight;
    public static int ROWS;
    public static int COLUMNS;
    public static String filepath;
    public static String exportpath;

    public static String[] friends;
    public static String[] studentNames;
    public static boolean[] glasses;
    public static ArrayList<Student> students;


    public static void SeatingRunner(String input_pathing, String output_pathing, String intensity_inp, String row_weight_inp, String row, String col) throws IOException {






        row_weight = Integer.valueOf(row_weight_inp);
        intensity = Integer.valueOf(intensity_inp);
        COLUMNS = Integer.valueOf(col);
        ROWS = Integer.valueOf(row);
        filepath = input_pathing;
        exportpath = output_pathing;
        System.out.println(ROWS);
        System.out.println(COLUMNS);





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


        board = optmialBoard(board, row_weight, intensity);

        // printBoard(board); -- prints post board

        //writeDataLineByLine(board, ROWS, COLUMNS);

        File csvFile = new File(exportpath);
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

        writeToCSV(names, exportpath);









    }

}


