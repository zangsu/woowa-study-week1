package v3.functionalinterface;

import java.util.ArrayList;
import java.util.List;
import model.Student;

public class SortStudent{

    public static <Key> List<Student> sort(List<Student> students,
                              KeyExtractor<Student, Key> extractor,
                              Comparator<Key> comparator) {
        List<Student> sortedList = new ArrayList<>();

        for (Student student : students) {
            int index = 0;
            Key studentKey = extractor.getKey(student);
            for (; index < sortedList.size(); index++) {
                Student innerStudent = sortedList.get(index);
                Key innerStudentKey = extractor.getKey(innerStudent);
                if(comparator.compareKey(innerStudentKey, studentKey) > 0)
                break;
            }
            sortedList.add(index, student);
        }

        return sortedList;
    }
}
