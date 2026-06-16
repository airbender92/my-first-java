package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 必备使用技能综合demo
 * 异常处理 + 常用集合 + 泛型 + IO流
 */
public class JavaBasicSkillDemo {
    public static void main(String[] args){
        System.out.println("===1.泛型与ArrayList练习===");
        genericAndArrayListTest();
    }

    /**
     * 泛型 + ArrayList + Collections.sort 排序
     */
    private static void genericAndArrayListTest(){
        // 泛型：编译时类型检查，避免强转
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Zhang san");
        studentNames.add("Li si");
        studentNames.add("wang wu");

        System.out.println("排序前：" + studentNames);

        // 工具类 Collections 排序
        Collections.sort(studentNames);
        System.out.println("排序后：" + studentNames);

        // 泛型类简单演示
        Box<String> box = new Box<>();
        box.setItem("Hello Generics");
        System.out.println("泛型类内容：" + box.getItem());
    }

    // ==============泛型类========================
    static class Box<T>{
        private T item;
        public  T getItem(){
            return item;
        }

        public void setItem(T item){
            this.item = item;
        }
    }
}


