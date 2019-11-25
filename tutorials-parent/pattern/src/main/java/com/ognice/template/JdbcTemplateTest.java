package com.ognice.template;

public class JdbcTemplateTest {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.queryUsers());
    }

}
