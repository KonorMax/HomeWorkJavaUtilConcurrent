package com.konor.HomeWorkJavaUtilConcurrent;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("Thread running ");

        new Thread(new MyThread(countDownLatch)).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread ends");
    }
}

class MyThread implements Runnable {
    CountDownLatch cdl;

    public MyThread(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            cdl.countDown();
        }

    }
}

