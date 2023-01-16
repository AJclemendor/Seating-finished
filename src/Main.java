import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;


public class Main extends functions{


    public static final int ROWS = 4;
    public static final int COLUMNS = 8;


    public static String[] friends;
    public static String[] studentNames;
    public static boolean[] glasses;
    public static ArrayList<Student> students;

    public static void main(String[] args) throws IOException {
        String unknown = "seating_sheet.csv";

        Path path = Paths.get("seating_sheet.csv");

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
        printBoard(board);


        board = optmialBoard(board);

        printBoard(board);

    }




}