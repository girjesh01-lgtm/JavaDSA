package org.example;

public class Parent {

    public static class Child {

        public int age;
        public static String name;

        public static String getName() {
            return name;
        }

        public void createParentObjects() {
            Parent p = new Parent();
        }
    }
}
