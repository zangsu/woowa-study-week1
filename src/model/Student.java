package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    int height;
    String name;

    public Student(String name, int height) {
        this.height = height;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public static List<Student> getStudents(){
        List<Student> students = new ArrayList<>();

        students.add(new Student("def", 198));
        students.add(new Student("abc", 178));
        students.add(new Student("ghi", 162));
        students.add(new Student("zab", 157));
        students.add(new Student("jkl", 162));

        return students;
    }
}


