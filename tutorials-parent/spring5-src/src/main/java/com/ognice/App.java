package com.ognice;

import com.ognice.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("application.xml");
        Object dbfkbean = classPathXmlApplicationContext.getBean("dbfkbean");
        System.out.println(((User) dbfkbean).toString());

    }
}
