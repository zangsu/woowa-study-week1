package v2;

import java.util.List;
import model.Student;

public class StudentSortV2 {
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        SortStudentByName sortByName = new SortStudentByName();

        List<Student> sortedByName = sortByName.sortStudent(students);

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
}
