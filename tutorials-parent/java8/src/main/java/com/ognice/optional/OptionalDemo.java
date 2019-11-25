package com.ognice.optional;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        /**
         *         创建Optional
         *         原理：对object进行包装
         *
         */

        // Optional<String> optional = Optional.empty();
        Optional<String> optional = Optional.of("dbfk");
        //不允许为null，否则报npe
        Optional<Object> objectOptional = Optional.of(new Object());
        //允许
        Optional<Object> objectOptional1 = Optional.ofNullable(null);
        /**
         * 空值 get 报NoSuchElementException
         *     if (value == null) {
         *             throw new NoSuchElementException("No value present");
         *         }
         *         return value;
         */
        // System.out.println(optional.get());
        /**
         * orelseGet 有值就返回value 没有就返回传入的值（supplier）
         */
        // System.out.println(optional.orElseGet(() -> "dbfk"));
        /**
         * 没有就抛出异常
         */
        // System.out.println(optional.orElseThrow(NullPointerException::new));

        /**
         * filter 传入predicate,返回optional
         * filter的option需要有值不然报nosuchElement
         * 返回boolean值
         */
        String dbfk = optional.filter(e -> e.equals("dbfk")).get();
        System.out.println("filter返回值" + dbfk);

        /**
         * map，传入function,返回optional
         */
        Optional<Integer> optionalO = optional.map(s -> s.length());
        //System.out.println(optionalO.isPresent());

        /**
         * ifprecent 是否有值 可传入consumer
         *
         *
         */
        optionalO.ifPresent(System.out::println);

        /**
         * flatMap 传入的function的返回值是optional
         */
        optionalO.flatMap(q -> objectOptional.filter(e -> e.toString().length() > 0));


    }
}
