
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class functions {


    public static ArrayList<String> createSeatingData(Path path) throws IOException {


        List<String> data = Files.readAllLines(path);
        ArrayList<String> returnData = new ArrayList<>();
        int len = data.size();
        int count = 0;
        for (int i = 0; i < len; i++) {
            // System.out.println(data.get(i));
            // System.out.println(i);
            if (i > 1 && data.get(i) != null) {
                returnData.add(count, data.get(i));

                count++;
            }
        }
        // System.out.println(returnData);
        return returnData;
    }

    public static void printStudents(ArrayList<String> studentArrList) {

        for (String line : studentArrList) {
            System.out.println(line);
        }

    }

    public static void printStudentsArray(String[] students) {
        for (String student : students) {
            System.out.println(student);
        }

    }

    public static void printKidsList(ArrayList<Student> kids) {
        for (Student kid : kids) {
            System.out.println(kid.getName());
        }
    }


    public static String[] partitionFriends(ArrayList<String> studentlist) {

        /* go through and take the array where each .get(i) is a different line
            sort out every friend name and check if there is a trailing , at the end
            if so remove it
            returns an array of strings where each index is a set of friends


         */

        String[] names = new String[studentlist.size()];
        int start = 0;
        int end = 0;
        for (int i = 0; i < names.length; i++) {
            studentlist.set(i, studentlist.get(i).replaceFirst("No", "Fi"));
            start = studentlist.get(i).indexOf("everyone\",");
            start += 10;

            if (studentlist.get(i).contains("Yes")) {
                end = studentlist.get(i).indexOf("Yes");
                //System.out.println(studentlist.get(i).indexOf("Yes"));
            } else {
                end = studentlist.get(i).indexOf("No");
                //System.out.println(studentlist.get(i).indexOf("No"));
            }

            names[i] = studentlist.get(i).substring(start, end).replace("\"", "").strip();
            if (names[i].lastIndexOf(",") == names[i].length() - 1) {
                names[i] = names[i].substring(0, names[i].length() - 1);
            }
            names[i] = names[i].replace("\"", "");


        }
        return names;
    }

    public static String[] partitionStudents(ArrayList<String> studentlist) {

        /*
        takes an arraylist of students and returns student names
        as spots in the names array
         */
        String[] names = new String[studentlist.size()];
        int start = 0;
        int end = 0;
        for (int i = 0; i < names.length; i++) {
            //System.out.println(studentlist.get(i));

            studentlist.set(i, studentlist.get(i).replaceFirst("No", "Fi"));
            if (studentlist.get(i).contains("Yes")) {
                start = studentlist.get(i).indexOf("Yes");
                start += 5;
                //System.out.println(studentlist.get(i).indexOf("Yes"));
            } else {
                start = studentlist.get(i).indexOf("No");
                start += 4;
                //System.out.println(studentlist.get(i).indexOf("No"));
            }

            end = studentlist.get(i).length() - 9;
            names[i] = studentlist.get(i).substring(start, end).strip();
            if (names[i].lastIndexOf(",") == names[i].length() - 1) {
                names[i] = names[i].substring(0, names[i].length() - 1);
            }


            names[i] = names[i].replace("\"", "");

        }
        return names;
    }

    public static boolean[] partitionGlassesList(ArrayList<String> studentlist) {

        boolean[] tof = new boolean[studentlist.size()];

        for (int i = 0; i < tof.length; i++) {
            studentlist.set(i, studentlist.get(i).replaceFirst("No", "Fi"));
            if (studentlist.get(i).contains("No")) {
                tof[i] = false;
            } else {
                tof[i] = true;
            }
        }
        return tof;
    }


    public static ArrayList<Student> createStudentArr(String[] studentNames, String[] friends, boolean[] glasses) {

        ArrayList<Student> kids = new ArrayList<>();
        for (int i = 0; i < studentNames.length; i++) {

            kids.add(i, new Student(friends[i], studentNames[i], glasses[i]));


        }
        return kids;
    }


    public static Student[][] createEmptyBoard(int rows, int cols, ArrayList<Student> kids) {
        Student[][] board = new Student[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (count < kids.size()) {
                    board[i][j] = kids.get(count);
                    count++;
                }
            }
        }
        return board;
    }

    public static void printBoard(Student[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {

                    System.out.print(board[i][j].getName() + ": ");
                } else {
                    System.out.print("empty ");
                }
            }
            System.out.println();

        }
    }


    public static Student[][] optmialBoard(Student[][] board, int row_weight, int intensity) {
        double start_unhapppy = getTotalUnHappy(board, row_weight);
        double new_unhappy = 0.0;
        Student[][] newBoard = board.clone();

        for (int run = 0; run<intensity; run++) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    for (int k = 0; k < board.length; k++) {
                        for (int c = 0; c < board[0].length; c++) {

                            studentSwap(i, j, k, c, newBoard);
                            new_unhappy = getTotalUnHappy(newBoard, row_weight);
                            if (new_unhappy < start_unhapppy) {
                                board = newBoard;
                                start_unhapppy = new_unhappy;

                                //System.out.println(new_unhappy);
                                //System.out.println(start_unhapppy + " start ");
                            } else {
                                studentSwap(k, c, i, j, newBoard);
                                // newBoard = board;
                            }
                        }
                    }

                }
            }
        }


        return board;


    }


    public static double getUnhappySingleStudent(Student[][] board, int row, int col, int row_weight) {
        double unhappy = 0.0;


        String[] friends = board[row][col].getFriends().split(",");

         if (board[row][col].isGlassesTOF()) {
            if (row > 2) {
                unhappy += 4*row;
            }
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    if (!(board[i][j].getName().equals(board[row][col].getName()))) {
                        for (String name : friends) {
                            String friend_name = board[i][j].getName().replace(",", "").toLowerCase().strip();

                            //System.out.println(name.toLowerCase());
                            //System.out.println(friend_name.toLowerCase());
                            // if you want to see names being compared
                            if (friend_name.equals(name.toLowerCase().strip())) {

                                double first = Math.pow((row - i), 2);
                                double second = Math.pow((col - j), 2);
                                unhappy = Math.sqrt((first + second));

                                if (i != row) {
                                    unhappy += row_weight;
                                    // System.out.println(row_weight);
                                }

                            }
                        }
                    }
                }
            }
        }
        //System.out.println(unhappy);
        // if you want to see unhappy value
        return unhappy;
    }

    public static double getTotalUnHappy(Student[][] board, int row_weight) {
        double total_unhappy = 0.0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    total_unhappy += getUnhappySingleStudent(board, i, j, row_weight);

                }
            }
        }
        //System.out.println(total_unhappy );
        return total_unhappy;
    }

    public static void studentSwap(int row, int col, int new_row, int new_col, Student[][] board) {

        if (board[row][col] == null || board[new_row][new_col] == null) {
            // System.out.println("Null student swap");
            return;
        }
        Student dummy = board[row][col];
        board[row][col] = board[new_row][new_col];
        board[new_row][new_col] = dummy;
    }

    public static void writeToCSV(String[][] board, String fileName) {
        // check if board is valid
        if (board == null || board.length == 0) {
            //System.out.println("Invalid board");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // write the header
            bw.write("AJ Seating Chart");
            bw.newLine();

            // write the data
            for (String[] row : board) {
                try {
                    for (int j = 0; j < row.length; j++) {

                        bw.write(row[j]);
                        if (j < row.length - 1) {
                            bw.write(",");
                        }
                    }
                }
                catch (Exception e) {
                    //System.out.println("NULL SPOT BYPASSED");

                }
                bw.newLine();
            }

            System.out.println("Data written to CSV file successfully!");
            System.out.println("If you feel the chart was not good try increasing the intensity number or using a custom" +
                    " \n row weight the higher the weight the more the algo values students in the same row,\n 0 = no " +
                    "extra row value");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

