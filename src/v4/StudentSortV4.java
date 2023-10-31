package v4;

import java.util.List;
import model.Student;

public class StudentSortV4 {
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        List<Student> sortedByName = SortStudent.sort(students,
                student -> student.getName(),
                (key1, key2) -> key1.compareTo(key2));

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
}
