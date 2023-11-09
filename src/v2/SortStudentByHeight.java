package v2;

import model.Student;
import v2.abstractclass.SortStudent;

class SortStudentByHeight extends SortStudent<Integer> {
    @Override
    public Integer getCriteria(Student student) {
        return student.getHeight();
    }
}
