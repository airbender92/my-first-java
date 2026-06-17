package org.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    // 员工实体类
    static class Employee {
        private String name;
        private String dept;
        private double salary;

        public  Employee(String name, String dept, double salary){
            this.name = name;
            this.dept = dept;
            this.salary = salary;
        }

        // getter
        public String getName() { return  name; }
        public String getDept() { return  dept; }
        public double getSalary() { return salary;}
    }

    public static void main(String[] args){
        // 1. 构造员工列表
        List<Employee> employees = Arrays.asList(
                new Employee("张三", "技术部", 9500),
                new Employee("李四", "技术部", 12500),
                new Employee("赵武", "市场部", 10500),
                new Employee("钱琪", "技术部", 11500),
                new Employee("孙八", "技术部", 8500)
        );

        // 2. Stream 链式处理
        List<String> top3TechNames = employees.stream()
                // 过滤：技术部 + 薪资 > 8000
                .filter(e -> "技术部".equals(e.getDept()))
                .filter(e -> e.getSalary() > 8000)
                // 排序：薪资倒序
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                // 取前三
                .limit(3)
                // 映射：只取姓名
                .map(Employee::getName)
                // 收集成新列表
                .collect(Collectors.toList());

        // ====reduce 计算技术部总薪资============
        double techTotalSalary = employees.stream()
                .filter(e -> "技术部".equals(e.getDept()))
                .mapToDouble(Employee::getSalary)
                .reduce(0, Double::sum); // 初始值0， 累加

        // ======按部门分组：Map<部门名称，List<员工>>=======
        Map<String, List<Employee>> groupByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept));

        System.out.println("\n2.按部门分组： ");
        groupByDept.forEach((dept, emps) -> {
            System.out.print(dept + ": ");
            emps.forEach(e -> System.out.print(e.getName() + " "));
            System.out.println();
        });

        // ====每个部门平均薪资=====
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalary)));

        // ===转成 Map<String Double>： 姓名 -> 薪资==========
        Map<String, Double> nameToSalary = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getSalary
                ));

        // 3. 输出结果
        System.out.println("技术部薪资前三（>8000）: " + top3TechNames);
        System.out.println("技术部总薪资: " + techTotalSalary);
        System.out.println("各部门平均薪资: " + avgSalaryByDept);
        System.out.println("姓名-薪资映射: " + nameToSalary);
    }
}
