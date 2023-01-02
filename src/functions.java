import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class functions  {


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

    public static void printKids(ArrayList<Student> kids) {
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
            for (int i=0; i<names.length; i++) {
                studentlist.set(i,studentlist.get(i).replaceFirst("No","Fi"));
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
                if (names[i].lastIndexOf(",") == names[i].length()-1) {
                    names[i] = names[i].substring(0,names[i].length()-1);
                }


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
        for (int i=0; i<names.length; i++) {
            //System.out.println(studentlist.get(i));

            studentlist.set(i,studentlist.get(i).replaceFirst("No","Fi"));
            if (studentlist.get(i).contains("Yes")) {
                start = studentlist.get(i).indexOf("Yes");
                start +=5;
                //System.out.println(studentlist.get(i).indexOf("Yes"));
            } else {
                start = studentlist.get(i).indexOf("No");
                start += 4;
                //System.out.println(studentlist.get(i).indexOf("No"));
            }

            end = studentlist.get(i).length()-9;
            names[i] = studentlist.get(i).substring(start, end).strip();
        /*    if (names[i].lastIndexOf(",") == names[i].length()-1) {
                names[i] = names[i].substring(0,names[i].length()-1);
            }
*/

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


    public static ArrayList<Student> createStudentArr(String[] studentNames, String[] friends, boolean[] glasses){

        ArrayList<Student> kids = new ArrayList<>();
        for (int i=0; i<studentNames.length; i++) {

            kids.add(i, new Student(friends[i], studentNames[i], glasses[i]));


        }
        return kids;
    }
}
