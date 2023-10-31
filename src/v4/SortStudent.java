package v4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import model.Student;

public class SortStudent {
    public static <Key> List<Student> sort(List<Student> students,
                                           Function<Student, Key> extractor,
                                           Comparator<Key> comparator) {
        List<Student> sortedList = new ArrayList<>();

        for (Student student : students) {
            int index = 0;
            Key studentKey = extractor.apply(student);
            for (; index < sortedList.size(); index++) {
                Student innerStudent = sortedList.get(index);
                Key innerStudentKey = extractor.apply(innerStudent);

                if(comparator.compare(innerStudentKey, studentKey) > 0)
                    break;
            }
            sortedList.add(index, student);
        }

        return sortedList;
    }
}
