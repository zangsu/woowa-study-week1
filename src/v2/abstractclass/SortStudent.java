package v2.abstractclass;

import java.util.ArrayList;
import java.util.List;
import model.Student;

public abstract class SortStudent <K extends Comparable<K>>{

    public abstract K getCriteria(Student student);
    public List<Student> sortStudent(List<Student> students){
        List<Student> sortedList = new ArrayList<>();

        //삽입 정렬
        for (Student student : students) {
            int index = 0;
            for (; index < sortedList.size(); index++) {
                Student innerStudent = sortedList.get(index);
                if(getCriteria(innerStudent).compareTo(getCriteria(student)) > 0)
                    break;
            }
            sortedList.add(index, student);
        }

        return sortedList;
    }
}
