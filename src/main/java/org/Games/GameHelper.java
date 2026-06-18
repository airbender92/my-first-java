package org.Games;

import java.io.*;

public class GameHelper {
    public String getUserInput(String prompt){
        String inputLine = null;
        // 打印提示文字，等待用户输入；
        System.out.print(prompt + " ");
        try{
            // 构建控制台读取流
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            // 读取用户输入一整行，回车结束
            inputLine = is.readLine();
            // 如果用户直接回车，无内容，返回null
            if(inputLine.length() == 0){
                return null;
            }
        }catch(IOException e){
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }
}
