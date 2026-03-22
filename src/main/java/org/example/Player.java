package org.example;

interface Player {
    int currentScore=0;

    default public void play() {
        System.out.println("playing the game");
    }

    public static void field() {
        System.out.println("playing the game");
    }
}

interface Gamer {
    int currentScore=0;

    default public void play() {
        System.out.println("playing the game");
    }

    public static void sleep() {
        System.out.println("playing the game");
    }
}

 class person implements Player, Gamer {

    @Override
    public void play() {
        Player.super.play();
    }
}

class Parent {
    static void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child");
    }
}
//Parent obj; = new Child();
//obj.show();

