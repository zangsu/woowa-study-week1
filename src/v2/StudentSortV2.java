package v2;

import java.util.List;
import model.Student;
import v2.abstractclass.SortStudent;

public class StudentSortV2 {
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        SortStudentByName sortByName = new SortStudentByName();
        SortStudentByHeight sortStudentByHeight = new SortStudentByHeight();

        List<Student> sortByHeight = new SortStudent<Integer>() {
            @Override
            public Integer getCriteria(Student student) {
                return student.getHeight();
            }
        }.sortStudent(students);

        List<Student> sortedByName = sortStudentByHeight.sortStudent(students);

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
}
