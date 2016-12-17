package edu.wc.cs152.anniefarm;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by shaffer on 4/27/16.
 */
public class GameSurfaceView extends SurfaceView {

    private GameLoop gameLoop;
    private World world;
    private boolean pause;

    public GameSurfaceView(Context context) {
        super(context);
        gameLoop = new GameLoop(this);
        world = new World(this);
        connectHolder();
        pause = false;
    }

    public void pause() {
        pause = !pause;
    }

    public boolean isPaused() {
        return pause;
    }

    private void connectHolder() {
        getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                gameLoop.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.d("game", "surface destroyed!!!!!");
                gameLoop.stop();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWorld(canvas);
    }

    public void drawWorld(Canvas c) {
        world.draw(c);
    }

    public void tick(double v) {
        if (!pause)
            world.tick(v);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        TouchEventQueue.getInstance().enqueue(e);
        TouchStateManager.getInstance().onTouchEvent(e);
        return true;
    }
}
