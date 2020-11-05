package com.example.example_4.fragments.TestFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.example_4.R;

import io.reactivex.rxjava3.core.Observable;

public class TestFragment extends Fragment {
    private boolean isRunning = true;
    private String last = "Left step";
    private static final String TAG = "TestFragment";
    Thread th1, th2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.testfragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        th1 = new Thread(new LeftLeg());
        th2 = new Thread(new RightLeg());
        th1.start();
        th2.start();
        //startRxJava();
        testRxJava();
    }

    private void testRxJava() {

    }

    private void startRxJava() {
        Observable.just("test", "test1", "test2")
                .subscribe(msg -> {
                    Log.i(TAG, "startRxJava: " + msg);
                });

    }

    private class LeftLeg implements Runnable {
        @Override
        public void run() {
            while (th1.isAlive()) sendStep("Left step");
        }
    }

    private synchronized void sendStep(String message) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (last.equals(message)) {
                    wait();
                } else {
                    Log.i(TAG, message);
                    last = message;
                    Thread.sleep(1000);
                    notifyAll();
                }
            }
            if (!th1.isAlive() || !th2.isAlive()) {
                Log.i(TAG, "sendStep: threads is stops = " + th1.isAlive() + th2.isAlive());
                return;
            }

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private class RightLeg implements Runnable {
        @Override
        public void run() {
            while (th2.isAlive()) sendStep("Right step");
        }
    }


    @Override
    public void onStop() {
        if(th1.isAlive()) th1.interrupt();
        if(th2.isAlive()) th2.interrupt();
        super.onStop();
        /*        isRunning = false;*/
    }
}
