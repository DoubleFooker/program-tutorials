package com.ognice.lambda.function;

import com.ognice.lambda.demo.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Function<T,R>  R applt(T t);
 * <p>
 * Predicate boolean test(T t);
 * Consumer<T> accept(T t);
 * Supplier T get();
 * <p>
 * 静态方法 可用方法推断方式写：Integer::paresInt 2。实例方法：String::new
 */
public class FunctionDemo {

    private static List<Apple> predicateFilter(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> longPredicateFilter(List<Apple> apples, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static void consumerFilter(List<Apple> apples, Consumer<Apple> consumer) {
        for (Apple apple : apples) {
            consumer.accept(apple);
        }
    }

    /**
     * function<T,R>
     * R为返回值
     *
     * @param apple
     * @param function
     * @return
     */
    private static String testFunction(Apple apple, Function<Apple, String> function) {
        return function.apply(apple);
    }

    /**
     * supplier
     */
    private static Apple getBySupplier(Supplier<Apple> supplier) {
        return supplier.get();
    }

    ;

    public static void main(String[] args) {


        List<Apple> apples = Arrays.asList(new Apple(100, "red"), new Apple(1000, "green"));
        /**
         *只有一个参数,做判断使用
         */
        List<Apple> greenList = predicateFilter(apples, apple -> apple.getColor().equals("green"));
        List<Apple> greenList2 = longPredicateFilter(apples, w -> w <= 100);
        System.out.println(greenList2);

        /**
         * consumer accept 执行consumer传入方法
         */
        consumerFilter(apples, apple -> {
            if (apple.getWeight() > 100) {
                System.out.println(apple.getColor());
            }
        });

        /**
         * function 执行传入方法，并有返回值
         */

        String result = testFunction(new Apple(120, "blue"), apple -> apple.getColor());
        System.out.println(result);

        /**
         * supplier
         */
        Apple apple = getBySupplier(() -> new Apple(120, "color"));
        Supplier<String> str = String::new;
        int length = str.get().length();
    }
}
