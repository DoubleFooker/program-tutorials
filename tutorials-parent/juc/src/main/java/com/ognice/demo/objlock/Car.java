package com.ognice.demo.objlock;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/26
 */
public class Car implements Runnable {
    private int carNum;
    private CarCompetion competion = null;

    public Car(int carNum, CarCompetion competion) {
        this.carNum = carNum;
        this.competion = competion;
    }

    @Override
    public void run() {
        synchronized (competion) {
            competion.nowCarNum++;
            while (competion.nowCarNum < competion.totalCarNum) {
                try {
                    competion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            competion.notifyAll();
        }
        startCar();
    }

    private void startCar() {
        System.out.println("Car num " + this.carNum + " start to run.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Car num " + this.carNum + " get to the finish line.");
    }
}

