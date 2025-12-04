package demoOOP;

public class Student extends Person {
    private String nameSchool;
    public Student(String name, int age, float height, String nameSchool) {
        super(name, age, height);
        this.nameSchool = nameSchool;

    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.printf("Shool: " + this.nameSchool);
    }
    public static void main(String[] args) {

        Student a = new Student("Trung", 21, 1.67f, "FPT");
        a.getInfo();
    }
}
