package org.Game;

public class GuessGame {
    // 三个玩家对象
    Player p1;
    Player p2;
    Player p3;

    public void startGame(){
        // 1. 创建三个玩家
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();

        int guessp1 = 0;
        int guessp2 = 0;
        int guessp3 = 0;

        boolean p1isRight = false;
        boolean p2isRight = false;
        boolean p3isRight = false;

        // 生成0-9的随机数目标
        int targetNumber = (int) (Math.random() * 10);
        System.out.println("I'm thinking of a number between 0 and 9...");

        // 死循环，直到有人猜对
        while(true){
            System.out.println("Number to guess is " + targetNumber);

            // 让每个玩家猜测
            p1.guess();
            p2.guess();
            p3.guess();
            // 取出每个玩家猜测的数字
            guessp1 = p1.number;
            guessp2 = p2.number;
            guessp3 = p3.number;

            System.out.println("Player one guessed " + guessp1);
            System.out.println("Player two guessed " + guessp2);
            System.out.println("Player three guessed " + guessp3);

            // 判断是否猜对
            if(guessp1 == targetNumber){
                p1isRight = true;
            }
            if(guessp2 == targetNumber){
                p2isRight = true;
            }
            if(guessp3 == targetNumber){
                p3isRight = true;
            }

            // 只要有一个人对，游戏结束
            if(p1isRight || p2isRight || p3isRight){
                System.out.println("we have a winner!");
                System.out.println("Player one got it right? " + p1isRight);
                System.out.println("Player two got it right? " + p2isRight);
                System.out.println("Player three got it right? " + p3isRight);
                System.out.println("Game is over.");
                break;
            } else {
                System.out.println("Player will have to try again.");
            }
        }
    }
}
