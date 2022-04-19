package cn.element.io.serialClone;

import java.io.*;
import java.time.*;

public class SerialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        var harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        // clone harry
        var harry2 = (Employee) harry.clone();

        // mutate harry
        harry.raiseSalary(10);

        // now harry and the clone are different
        System.out.println(harry);
        System.out.println(harry2);
    }
}

