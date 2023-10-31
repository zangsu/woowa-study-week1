package v3;

import v3.functionalinterface.Comparator;
import v3.functionalinterface.KeyExtractor;
import v3.functionalinterface.SortStudent;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentSortV3{
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        List<Student> sortedByName =
                SortStudent.sort(students, new KeyExtractor<Student, String>() {
                    @Override
                    public String getKey(Student student) {
                        return student.getName();
                    }
                }, new Comparator<String>() {
                    @Override
                    public int compareKey(String key1, String key2) {
                        return key1.compareTo(key2);
                    }
                });

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }

    }
}
