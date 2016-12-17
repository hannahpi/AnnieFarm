package edu.wc.cs152.anniefarm;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by shaffer on 4/27/16.
 */
public class GameLoop implements Runnable {

    private final double FPS = 30;

    private Thread thread;
    private GameSurfaceView view;
    private boolean stopped;

    public GameLoop(GameSurfaceView v) {
        view = v;
        stopped = true;
    }

    public void start() {
        if (!stopped) return;
        thread = new Thread(this);
        stopped = false;
        thread.start();
    }

    public void stop() {
        stopped = true;
    }

    @Override
    public void run() {
        Log.d("game", "Game loop running");
        while(!stopped && !Thread.interrupted()) {
            long startTime = System.currentTimeMillis();
            updateWorld();
            updateDisplay();
            long endTime = System.currentTimeMillis();
            delay((long)(1000/FPS - (endTime-startTime)));

        }
        Log.d("game", "Game loop stopped");
    }

    private void delay(long v) {
        if (v <= 0) v = 5;
        try {
            Thread.sleep(v);
        } catch (InterruptedException e) {
            stopped = true;
        }
    }

    private void updateDisplay() {
        Canvas c = view.getHolder().lockCanvas();
        if (c != null && !view.isPaused()) {
            try {
                view.drawWorld(c);
            } finally {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }
    }

    private void updateWorld() {
        view.tick(1/FPS);
    }
}
