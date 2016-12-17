package edu.wc.cs152.anniefarm;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by shaffer on 4/27/16.
 */
public abstract class Sprite {
    private BitmapSequence bitmaps;
    protected Vec2d position;


    public Sprite(Vec2d v) {
        setPosition(v);
    }

    public void setBitmaps(BitmapSequence b) {
        bitmaps = b;
    }
    public BitmapSequence getBitmaps() { return bitmaps; }
    public void setPosition(Vec2d p) {
        position = p;
    }

    public Vec2d getPosition() {
        return position;
    }

    public void draw(Canvas c) {
        try {
            bitmaps.drawCurrent(c, position.getX(), position.getY(), null);
        } catch (Exception e) {
            Log.d("exception", e.toString());
            Log.d("exception", this.toString() + " " + this.getClass().toString());
        }
    }

    public void tick(double dt) {
        bitmaps.tick(dt);
    }

    public RectF getBoundingBox() {
        if (bitmaps==null)
            return new RectF(0,0,0,0);
        PointF size = bitmaps.getSize();
        return new RectF(getPosition().getX(),getPosition().getY(),getPosition().getX()+size.x,getPosition().getY()+size.y);
    }

    public boolean collidesWith(Sprite other) {
        boolean result = intersectionWith(other) != null;
        return result;
    }

    public float getDistance(Sprite other) {
        if (collidesWith(other))
            return 0;
        else {
            RectF otherRect = other.getBoundingBox();
            RectF rect = getBoundingBox();
            PointF size = bitmaps.getSize();
            if (position.getX() > otherRect.left && position.getX() < otherRect.right) { // in between
                return otherRect.top - rect.bottom;
            } else {
                return (float) Math.min(Math.sqrt(Math.pow(otherRect.top - rect.bottom, 2) + Math.pow(otherRect.left - rect.left, 2)),
                        Math.sqrt(Math.pow(otherRect.top - rect.bottom, 2) + Math.pow(otherRect.right - rect.right,2)));
            }

        }
    }

    /**
     * Return null if I don't intersect with other, otherwise return the overlap of our two bounding boxes.
     *
     * @param other
     * @return
     */
    public RectF intersectionWith(Sprite other) {
        RectF r = new RectF(getBoundingBox());
        if (!r.intersect(other.getBoundingBox()))
            return null;
        else
            return r;
    }

    public abstract boolean isActive();

    public void resolve(Collision collision, Sprite other) {

    }


}
