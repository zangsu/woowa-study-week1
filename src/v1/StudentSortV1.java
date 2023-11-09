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
        //1. 정렬된 리스트 생성
        List<Student> sortedList = new ArrayList<>();

        //2. 객체 정렬
        //2-1. 객체에서 정렬할 키 추출
        //2-2. 키를 기준으로 객체 정렬

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

        //3. 정렬된 리스트 반환
        return sortedList;
    }


}
