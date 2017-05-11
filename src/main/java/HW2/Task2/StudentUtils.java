package HW2.Task2;

import java.util.List;

public class StudentUtils {

    public static List<Student> findStudentsWithCourse(List<Student> students, int course) {
        return FunctionUtils.allMatches(students, e -> e.getCourse() == course);
    }
    public static List<Student> findStudentsWithName(List<Student> students, String name) {
        return FunctionUtils.allMatches(students, e -> e.getName().equals(name));
    }
}
