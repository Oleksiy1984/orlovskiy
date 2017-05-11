package HW2.Task2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Задание 2
 *
 * @author Alexey
 */
public class Task2 {


    public static List<Student> all() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alan Kay", 5));
        students.add(new Student("Scott Knaster", 4));
        students.add(new Student("Daniel Kottke", 3));
        students.add(new Student("Chris Lattner", 2));
        students.add(new Student("Bruce Leak", 1));
        students.add(new Student("Kevin Lynch", 5));
        students.add(new Student("Jerry Manock", 4));
        students.add(new Student("Bob Mansfield", 4));
        students.add(new Student("Mike Matas", 2));
        students.add(new Student("Yoky Matsuoka", 2));
        return students;
    }

    public static List<Student> studentMatch(List<Student> students, int course) {
        List<Student> listStudents = new ArrayList<>();
        System.out.printf("Список студентов, которые обучаются на %d курсе:\n", course);
        for (Student student : students) {
            if (student.getCourse() == course) {
                listStudents.add(student);
                System.out.println(student.getName());
            }
        }
        return listStudents;
    }



    @Test
    public void testfindStudentsWithCourse(){
        Assert.assertEquals(studentMatch(all(),2),
                StudentUtils.findStudentsWithCourse(all(),2));
    }

    @Test
    public void testfindStudentsWithName(){
        System.out.println(StudentUtils.findStudentsWithName(all(),"Mike Matas"));
        Assert.assertEquals(1,
                StudentUtils.findStudentsWithName(all(),"Mike Matas").size());
    }
}

