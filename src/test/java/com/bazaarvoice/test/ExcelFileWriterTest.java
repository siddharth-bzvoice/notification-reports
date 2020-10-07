package com.bazaarvoice.test;

import java.util.ArrayList;
import java.util.List;

public class ExcelFileWriterTest {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();

        Person p1 = new Person("A", "a@roytuts.com", "Kolkata");
        Person p2 = new Person("B", "b@roytuts.com", "Mumbai");
        Person p3 = new Person("C", "c@roytuts.com", "Delhi");
        Person p4 = new Person("D", "d@roytuts.com", "Chennai");
        Person p5 = new Person("E", "e@roytuts.com", "Bangalore");
        Person p6 = new Person("F", "f@roytuts.com", "Hyderabad");

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);
        persons.add(p6);

        ExcelFileWriter.writeToExcel("excel-person.xlsx", persons);

        List<Employee> employeeList = new ArrayList<>();

        Employee e1 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 1234.67);
        Employee e2 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 35345454.9090);
        Employee e3 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 945958578.79457);
        Employee e4 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 7597549.99809);
        Employee e5 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 5685769.9090);
        Employee e6 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 786564865.90805);
        Employee e7 = new Employee("Siddharth", "123", "1234567", "siddharth@gmail.com", 7756856.877945);

        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);
        employeeList.add(e6);
        employeeList.add(e7);

        ExcelFileWriter.writeToExcel("excel-employee.xlsx", employeeList);

    }

}
