package HW2.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Задание 3
 *
 * @author Alexey
 */
public class Task3 {
    public static void print() {
        Map<Integer,String> employees = new HashMap();
        employees.put(50,"Alan Kay");
        employees.put(1,"Scott Knaster");
        employees.put(5, "Daniel Kottke");
        employees.put(2,"Chris Lattner");
        employees.put(4,"Bruce Leak");
        employees.put(6,"Kevin Lynch");
        employees.put(100, "Jerry Manock");
        employees.put(20, "Bob Mansfield");
        employees.put(15,"Mike Matas");
        employees.put(11,"Yoky Matsuoka");
        getEmploeeByID(employees,11);
    }
    public static void getEmploeeByID(Map<Integer,String> hasmap,int id){
        if(hasmap.containsKey(id)) {
            System.out.printf("%s, ID=%d",hasmap.get(id),id);
        }
        else {
            System.out.printf("There is no such employee with ID=%d",id);
        }
    }
}
