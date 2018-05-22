package com.valencia.oscar.w2d2_as1;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class MyThread extends Thread{
    private int target = 0;
    private String threadName;
    private Thread thread;

    public MyThread(int target, String threadName, Thread thread) {
        this.target = target;
        this.threadName = threadName;
        this.thread = thread;
        ++threadsRunning;
    }

    private int threadsRunning = 0;

    @Override
    public void run() {
        try{
            for (int i = 1; i <=target; i++) {
                if (MainActivity.increasedNumber < target) {
                    stuffByThread();
                    Thread.sleep(1000);
                }
            }
        }catch(Exception e){
            Log.d("THREAD_TAG",e.toString());
        }finally{
            --threadsRunning;
            if (threadsRunning <= 0)
                this.notifyMain();
        }
    }

    public void stuffByThread(){
        Message msg = new Message();
        Bundle data = new Bundle();
        MainActivity.increasedNumber++;
        //data.putString(MainActivity.TEXT_VALUE, Integer.toString(MainActivity.increasedNumber)+" by thread: "+Long.toString(Thread.currentThread().getId()));
        data.putString(MainActivity.TEXT_VALUE, Integer.toString(MainActivity.increasedNumber)+" by thread: "+this.threadName);
        msg.setData(data);
        MainActivity.handler.sendMessage(msg);
        Log.d("THREAD_TAG", "Thread " + Long.toString(Thread.currentThread().getId()) + " increased to: " + MainActivity.increasedNumber);
    }

    public void notifyMain() {
        synchronized (thread) {
            try {
                thread.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
