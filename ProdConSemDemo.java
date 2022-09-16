package com.konor.HomeWorkJavaUtilConcurrent;

import java.util.concurrent.Semaphore;

public class ProdConSemDemo {
    public static void main(String[] args) {
        Q q = new Q();
        new Thread(new Consumer(q),"Consumer").start();
        new Thread(new Producer(q),"Producer").start();
    }
}

class Q {
    int n;
    static Semaphore  conSem = new Semaphore(0);
    static Semaphore  prodSem = new Semaphore(1);

    void get (){
        try {
            conSem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Get " +  n);
        prodSem.release();
    }
    void put(int n){
        try {
            prodSem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.n = n;
        System.out.println("Put " +  n);
        conSem.release();

    }

}

class Producer implements Runnable{
    Q q;

    public Producer(Q q) {
        this.q = q;
        //new Thread(this," Producer").start();
    }


    @Override
    public void run() {
        for (int i = 0;i < 20; i++) q.put(i);

    }
}
class Consumer implements Runnable{
    Q q;

    public Consumer(Q q) {
        this.q = q;
        //new Thread(this,"Consumer").start();
    }

    @Override
    public void run() {
        for (int i =0; i < 20; i++) q.get();

    }
}