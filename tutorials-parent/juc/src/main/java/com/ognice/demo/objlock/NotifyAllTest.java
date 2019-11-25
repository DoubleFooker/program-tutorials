package com.ognice.demo.objlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/26
 */
public class NotifyAllTest {


    public static void main(String[] args) {
        CarCompetion carCompetion = new CarCompetion();
        final ExecutorService carPool =
                Executors.newFixedThreadPool(carCompetion.totalCarNum);
        for (int i = 0; i < carCompetion.totalCarNum; i++) {
            carPool.execute(new Car(i, carCompetion));
        }
    }
}
