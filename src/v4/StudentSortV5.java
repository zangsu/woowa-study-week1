package v4;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import model.Student;

public class StudentSortV5 {
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        List<Student> sortedByName =
                SortStudent.sort(students, Student::getName, String::compareTo);

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
}
