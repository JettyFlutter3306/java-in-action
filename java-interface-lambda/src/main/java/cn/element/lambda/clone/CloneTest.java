package cn.element.lambda.clone;

public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee orignial = new Employee("John Q.Public", 50000);
        orignial.setHireDay(2000, 1, 1);
        Employee copy = orignial.clone();
        copy.raiseSalary(10);
        copy.setHireDay(2002, 12, 31);
        System.out.println("original = "+ orignial);
        System.out.println("copy = " + copy);
    }

}
