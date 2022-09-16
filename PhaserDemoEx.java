package com.konor.HomeWorkJavaUtilConcurrent;

import java.util.concurrent.Phaser;

public class PhaserDemoEx {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int curPhase;
        System.out.println("Thread start");

        new Thread(new OurThread2(phaser, "A")).start();
        new Thread(new OurThread2(phaser, "B")).start();
        new Thread(new OurThread2(phaser, "C")).start();

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase №-" + curPhase + " ends");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase №-" + curPhase + " ends");

        phaser.arriveAndDeregister();

        if (phaser.isTerminated())
            System.out.println("Phaser ends");
    }
}

class OurThread2 implements Runnable {
    Phaser phaser;
    String name;

    public OurThread2(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " starts first phase");
        phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + name + " starts second phase");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + name + " starts third phase");
        phaser.arriveAndDeregister();

    }
}
