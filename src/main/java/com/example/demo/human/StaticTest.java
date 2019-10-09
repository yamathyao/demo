package com.example.demo.human;

/**
 * Created by GEEX177 on 2019/9/30.
 */
public class StaticTest {

    static class Soft {

    }
    static class QQ extends Soft {

    }
    static class _360 extends Soft {}
    public  static class Person{
        public void hardChoice(QQ qq){
            System.out.println("father choose qq");
        }
        public void hardChoice(Soft soft){
            System.out.println("father choose soft");
        }
        public void hardChoice(_360 _360){
            System.out.println("father choose 360");
        }
    }
    public static class Father extends Person{
        public void hardChoice(QQ qq){
            System.out.println("father choose qq");
        }
        public void hardChoice(Soft soft){
            System.out.println("father choose soft");
        }
        public void hardChoice(_360 _360){
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father{

        public void hardChoice(QQ qq){
            System.out.println("son choose qq");
        }
        public void hardChoice(Soft soft){
            System.out.println("son choose soft");
        }

        public void hardChoice(_360 _360){
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {

        Person father =new Father();
        Person son =new Son();

        Soft qq = new QQ();
        Soft _360 = new _360();
        father.hardChoice(qq);
        son.hardChoice(_360);

    }
}
