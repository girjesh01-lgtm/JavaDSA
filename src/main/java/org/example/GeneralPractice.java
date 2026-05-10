package org.example;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class GeneralPractice {

    public static void main(String[] args) {
        /*
        int i = -8;

        Integer num = 9;
        long l = -8;
        String abd = "mohan";
        int code = abd.hashCode();
        System.out.println(code);
        //for (int j=1; j<=16; j++) {
            //System.out.println(Integer.toBinaryString(i));
            //System.out.println(Long.toBinaryString(l));
          //  i = i >>> 1;
          //  l = l >>> 1;
            //System.out.println(i+"                 "+l);
        //}

         */

        List<Integer> list = List.of(1, 2, 3, 4);
        List<Integer> list1 = new ArrayList<>();
        /*list.stream()
                .filter(n -> {
                    System.out.println("filter " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("map " + n);
                    return n * n;
                }).toList();
         */

        Optional<Integer> resu = list1.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .findFirst();
        //.forEach(System.out::println);
        //System.out.println(resu.isPresent());

/*
        list.parallelStream()
                .forEach(n -> {
                    System.out.println(Thread.currentThread().getName() + " " + n);
                });




        List<Integer> list2 = new ArrayList<>();

        IntStream.range(0, 1000)
                .parallel()
                .forEach(list2::add);

        System.out.println(list2.size());
        BlockingQueue ab = new LinkedBlockingQueue();
        BlockingQueue abc = new ArrayBlockingQueue();

        Map<String, String> map = new HashMap<>();

 */

        /*List<String> names = new ArrayList<>(Arrays.asList("A","B","C", "D", "E"));

        List<String> myNames = names.subList(0, 1);

        printList(names);
        printList(myNames);

        //names.remove(2);


        printList(names);
        printList(myNames);



         */

        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 1, 2));
        printList(numbers);
        numbers.removeIf(x->x == 3);
        printList(numbers);
/*
        Iterator<Integer> it = numbers.iterator(); // saves modCount (e.g., 3)

        Collections.sort(numbers); // size is still 3, but modCount is now 4!

        // This will crash because the order shifted under the iterator's feet
        System.out.println(it.next());


        //Map<String, String> map = new HashMap<>();


 */

        Map<String, String> map = new HashMap<>();
        //Proxy.newProxyInstance(GridCut.class.getClassLoader(), new Class[]{GridCut.class}, new LoggingHandler());
    }

    private static <E> void printList(List<E> names) {
        System.out.println(" ");
        for (E name : names) {
            System.out.print(name + "  ");
        }
        System.out.println(" ");
    }


//1111 1111 1111 1111 1111 1111 1111 1000
}

