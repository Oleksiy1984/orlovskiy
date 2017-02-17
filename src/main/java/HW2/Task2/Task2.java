package HW2.Task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Задание 2
 *
 * @author Alexey
 */
public class Task2 {

    public static void print() {


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
        printStudents(students, 2);
    }

    public static void printStudents(List<Student> students, int course) {

        System.out.printf("Список студентов, которые обучаются на %d курсе:\n", course);
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }
}

