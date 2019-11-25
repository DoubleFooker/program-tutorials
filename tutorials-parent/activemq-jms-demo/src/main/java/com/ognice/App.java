package com.ognice;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {

        URI url = new URI("tcp://127.0.0.1:61616");
        System.out.println(url.getScheme());
    }
}
