package org.Game;

public class Player {
    // 玩家猜测的数字
    int number = 0;

    // 玩家自己猜测一个0-9的随机数
    public void guess(){
        number = (int) (Math.random() * 10);
        System.out.println("I'm guessing " + number);
    }
}
