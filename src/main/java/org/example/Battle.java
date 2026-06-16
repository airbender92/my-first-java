package org.example;

// 接口：可攻击的行为
interface Attackable {
    void attack(Character target);
}

// 抽象角色类
abstract class Character {
    private String name;
    private int hp;

    public Character(String name, int hp){
        this.name = name;
        this.hp = hp;
    }

    // 受到伤害扣血
    public void takeDamage(int damage){
        hp -= damage;
        // 血量最低不小于0
        if(hp < 0) {hp = 0;}
        System.out.println(name + " 受到 " + damage + " 伤害，剩余HP: " + hp);
        if(hp == 0) {
            System.out.println(name + " 已阵亡！");
        }
    }

    // 判断是否存活
    public  boolean isAlive(){
        return hp > 0;
    }

    // 获取角色名称
    public String getName(){
        return name;
    }
}

// 战士：继承角色，实现攻击接口
class Warrior extends Character implements Attackable {
    public Warrior(String name){
        super(name, 100);
    }

    @Override
    public void attack(Character target){
        System.out.println("\n 【攻击释放】" + getName() + " 挥剑猛击！");
        target.takeDamage(25);
    }
}

// 法师：同样继承角色，但攻击方式不同
class Mage extends Character implements Attackable {
    public Mage(String name){
        super(name, 70);
    }

    @Override
    public void attack(Character target){
        System.out.println("\n 【攻击释放】" + getName() + " 释放火球术！");
        target.takeDamage(45);
    }
}

// 战斗主类，程序入口
public class Battle {
    public static void main(String[] args){
        // 演示1：基础多态，战士攻击法师
        System.out.println("====第一轮战斗：亚瑟 vs 吉安娜======");
        Attackable warrior = new Warrior("亚瑟"); // 接口引用接收实现类（多态）
        Character mage = new Mage("吉安娜");
        warrior.attack(mage);

        // 演示2：双向对战，法师反击战士
        System.out.println("\n=========第二轮战斗：吉安娜反击亚瑟===============");
        Attackable mageAttacker = (Attackable) mage; // 向下转型，调用attack方法；
        Character warriorDefender = (Character) warrior;
        mageAttacker.attack(warriorDefender);

        // 演示3：多次攻击直到一方阵亡
        System.out.println("\n=========持续直到对方阵亡==========");
        while (mage.isAlive() && warriorDefender.isAlive()){
            warrior.attack(mage);
            if(!mage.isAlive()){
                break;
            }
            mageAttacker.attack(warriorDefender);
        }
    }
}
