package com.konor.HomeWorkJavaUtilConcurrent;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new BarAction());

        System.out.println("Thread start");
        new Thread(new MyThread2(cyclicBarrier, "First")).start();
        new Thread(new MyThread2(cyclicBarrier, "Second")).start();
        new Thread(new MyThread2(cyclicBarrier, "Third")).start();

    }
}

class MyThread2 implements Runnable {
    CyclicBarrier cyclicBarrier;
    String name;

    public MyThread2(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println(name);

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

class BarAction implements Runnable {

    @Override
    public void run() {
        System.out.println("Barrier reached ");
    }
}