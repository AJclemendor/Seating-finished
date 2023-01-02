public class Student {

    private String name;
    private String friends;
    private boolean glasses;


    public Student(String name, String friends, boolean glasses) {

        this.name = name;
        this.friends = friends;
        this.glasses = glasses;


    }

    public boolean isGlassesTOF() {
        return glasses;
    }


    public String getName() {
        return name;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

}
