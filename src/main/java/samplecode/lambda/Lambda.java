package samplecode.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Lambda {
    public static void main(String[] args) {

        String str1 = new Function<String, String>() {
            
            public String apply(String str) {
                return "Hello " + str;
            }
        }.apply("World1");
        System.out.println(str1);

        Function<String, String> f2 = (String str) -> {
            return "Hello " + str;
        };
        String str2 = f2.apply("World2");
        System.out.println(str2);

        Function<String, String> f3 = str -> "Hello " + str;
        String str3 = f3.apply("World3");
        System.out.println(str3);

        //Function<String, Integer> f = str -> Integer.parseInt(str);
        Function<String, Integer> f = Integer::parseInt;
        int num = f.apply("100");
        System.out.println(num);

        // staticメソッド参照
        /*
            Consumer<List<Integer>> con = new Consumer<List<Integer>>() {
            public void accept(List<Integer> list) {
                Collections.sort(list);
                }};
         */
        List<Integer> list = Arrays.asList(3,1,2);
        Consumer<List<Integer>> con1 = Collections::sort;
        con1.accept(list);
        System.out.println(list);

        // インスタンスメソッド参照
        // for(int a : list) {
        //     System.out.println(a);
        // }
        // list.forEach( a -> System.out.println(a));
        list.forEach(System.out::println);


    }
    
}
