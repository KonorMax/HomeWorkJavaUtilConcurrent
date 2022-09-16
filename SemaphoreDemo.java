package com.konor.HomeWorkJavaUtilConcurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        new Thread(new IncrementThread("First ",sem)).start();
        new Thread(new IncrementThread("Second ",sem)).start();
    }
}

class ShareResource{
    static int count = 0;
}

class IncrementThread implements Runnable{
    String name;
    Semaphore semaphore;

    public IncrementThread(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        System.out.println("Thread running " + name);
        try {
        System.out.println("Tread " + name + "wait for permits ");

            semaphore.acquire();

        System.out.println("Tread " + name + "got permit ");
        for (int i =0; i< 5; i++) {
            ShareResource.count++;
            System.out.println(name + ": " + ShareResource.count);
            Thread.sleep(10);
        }
            } catch (InterruptedException e) {
            System.out.println("Thread interruption ");

            }
        System.out.println("Thread " + name + "release permit ");
        semaphore.release();
    }

}
class DecrementThread implements Runnable{
    String name;
    Semaphore semaphore;

    public DecrementThread(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        System.out.println("Thread running " + name);
        try {
            System.out.println("Tread " + name + "wait for permits ");

            semaphore.acquire();

            System.out.println("Tread " + name + "got permit ");
            for (int i =0; i< 5; i++) {
                ShareResource.count--;
                System.out.println(name + ": " + ShareResource.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interruption");

        }
        System.out.println("Thread " + name + " release permit");
        semaphore.release();
    }

}


