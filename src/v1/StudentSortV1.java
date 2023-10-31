package v1;

import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentSortV1 {

    public void main(String[] args) {
        List<Student> students = Student.getStudents();

        List<Student> sortedByName = sortStudentByName(students);

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }

    private static List<Student> sortStudentByName(List<Student> students) {
        List<Student> sortedList = new ArrayList<>();

        //삽입 정렬
        for (Student student : students) {
            int index = 0;
            for (; index < sortedList.size(); index++) {
                Student innerStudent = sortedList.get(index);
                if(innerStudent.getName().compareTo(student.getName()) > 0)
                    break;
            }
            sortedList.add(index, student);
        }

        return sortedList;
    }
}
