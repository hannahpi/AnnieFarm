package edu.wc.cs152.anniefarm;

import android.view.MotionEvent;

/**
 * Created by shaffer on 4/30/16.
 */
public class TouchStateManager {

    private static TouchStateManager defaultInstance;

    public static TouchStateManager getInstance() {
        if (defaultInstance == null)
            defaultInstance = new TouchStateManager();
        return defaultInstance;
    }

    private Vec2d lastTouchLocation;
    private int touchDownCount;

    public TouchStateManager() {
        touchDownCount = 0;
        lastTouchLocation = null;
    }

    public void onTouchEvent(MotionEvent e) {
        switch(e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownCount++;
                lastTouchLocation = new Vec2d(e.getX(),e.getY());
                break;
            case MotionEvent.ACTION_UP:
                touchDownCount--;
        }
    }

    public boolean isTouchDown() {
        return touchDownCount > 0;
    }

    public Vec2d getLastTouchLocation() {
        return lastTouchLocation;
    }
}
