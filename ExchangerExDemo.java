package com.konor.HomeWorkJavaUtilConcurrent;

import java.util.concurrent.Exchanger;

public class ExchangerExDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new UseString(exchanger, "")).start();
        new Thread(new MakeString(exchanger, "")).start();

    }
}

class MakeString implements Runnable {
    Exchanger<String> exchanger;
    String str;

    public MakeString(Exchanger<String> exchanger, String str) {
        this.exchanger = exchanger;
        this.str = str;
    }

    @Override
    public void run() {
        char ch = 'A';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++)
                str += (char) ch++;
            try {
                str = exchanger.exchange(str);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

class UseString implements Runnable {
    Exchanger<String> exchanger;
    String str;

    public UseString(Exchanger<String> exchanger, String str) {
        this.exchanger = exchanger;
        this.str = str;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++)
            try {
                str = exchanger.exchange(new String());
                System.out.println("Got " + str);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

}

