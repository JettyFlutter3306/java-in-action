package cn.element.lambda.test;

import cn.element.lambda.interfaces.Employee;
import org.junit.Test;

import java.util.Arrays;

public class TestEmployeeSort {
    
    @Test
    public void testComparableSort() {
        Employee[] staff = new Employee[]{
                new Employee("Harry Hacker", 35000),
                new Employee("Carl Cracker", 75000),
                new Employee("Tony Tester", 38000)
        };

        Arrays.sort(staff);

        for (Employee e : staff) {
            System.out.println("name = " + e.getName() + ", salary = " + e.getSalary());
        }
    }

}
