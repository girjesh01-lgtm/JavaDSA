package org.example;

import java.util.ArrayList;
import java.util.List;

class Outer {
    int x = 10;

    class Inner {
        void print() {
            System.out.println(x);
        }
    }

    List<String> list = new ArrayList<>();


}
