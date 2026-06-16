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

        System.out.println("\n========2.HashMap 单词统计================");
        hashMapWordCountTest();

        System.out.println("\n=========3.异常处理=====================");
        execeptionTest();

        System.out.println("\n===========4. IO文件读写（try-with-resources）=====================");
        ioFileReadWriteTest();
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

    /**
     * HashMap 统计单词出现次数
     */
    private static void hashMapWordCountTest(){
        String text = "hello java hello world java java spring springboot";
        String[] words = text.split(" ");

        Map<String, Integer> countMap = new HashMap<>();

        for(String word : words){
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        // 遍历输出
        for (Map.Entry<String, Integer> entry : countMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * 异常处理： try-catch-finally、throw、throws、自定义异常
     */
    private static void execeptionTest(){
        try{
            // 可能抛出异常
            checkAge(-1);
        }catch (AgeIllegalException e) {
            // 捕获自定义异常
            System.err.println("捕获异常：" + e.getMessage());
            e.printStackTrace();
        } finally {
            // 必须执行：资源释放、日志记录等
            System.out.println("finally 块：资源清理");
        }
    }

    private static void checkAge(int age) throws AgeIllegalException {
        if(age < 0) {
            // 手动抛出异常
            throw new AgeIllegalException("年龄不能为负数：" + age);
        }
    }

    /**
     * IO 流：文本文件读写，使用 try-with-resources自动关闭流
     */
    private static void ioFileReadWriteTest(){
        // 会自动关闭资源， 无需 finally
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))
        ){
            String line;
            while((line = br.readLine()) != null){
                System.out.println("读取内容：" + line);
                bw.write(line);
                bw.newLine();// 换行
            }
            System.out.println("文件写入完成！");
        } catch(IOException e){
            // 异常不能空处理
            e.printStackTrace();
        }
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

    // ===========自定义异常===================
    static class AgeIllegalException extends Exception {
        public AgeIllegalException(String message) {
            super(message);
        }
    }

}


