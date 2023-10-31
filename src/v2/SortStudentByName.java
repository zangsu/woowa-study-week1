package v2;

import model.Student;
import v2.abstractclass.SortStudent;

class SortStudentByName extends SortStudent<String> {
    @Override
    public String getCriteria(Student student) {
        return student.getName();
    }
}
