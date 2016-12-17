package edu.wc.cs152.anniefarm;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by shaffer on 4/27/16.
 */
public class BitmapSequence {
    private ArrayList<SequenceElement> bitmaps;
    private boolean blink=false;
    private boolean lastImage=false;
    private boolean done= false;
    private int current;
    private double timeShowingCurrent;

    public BitmapSequence() {
        bitmaps = new ArrayList<>();
        current = 0;
    }

    public void addImage(Bitmap bm, double duration) {
        boolean tmpBlink = blink;
        if (tmpBlink) {
            removeBlink();
        }
        bitmaps.add(new SequenceElement(bm, duration));
        if (tmpBlink) {
            addBlink();
        }
    }

    public void addBlink() {
        if (blink) return;
        blink = true;
        bitmaps.add(new SequenceElement(BitmapRepo.getInstance().getImage(R.drawable.blank), .075));
    }

    public void removeBlink() {
        if (!blink) return;
        bitmaps.remove(bitmaps.size()-1);
        if (current>=bitmaps.size())
            current=bitmaps.size()-1;
        blink = false;

    }


    public void drawCurrent(Canvas c, float x, float y, @Nullable Paint p) {
        c.drawBitmap(getCurrent().bitmap, x, y, p);
    }

    private SequenceElement getCurrent() {
        return bitmaps.get(current);
    }

    public void nextBitmap() {
        current = (current + 1) % bitmaps.size();
        if (current==bitmaps.size()-1)
            lastImage=true;
        timeShowingCurrent = 0;
    }

    public void tick(double dt) {
        timeShowingCurrent += dt;
        if (timeShowingCurrent > getCurrent().duration)
        {
            if (lastImage)
                done = true;
            nextBitmap();
        }
    }

    public boolean isDone() {
        return done;
    }

    public int size() {
        return bitmaps.size();
    }

    public PointF getSize() {
        return new PointF(getCurrent().bitmap.getWidth(), getCurrent().bitmap.getHeight());
    }
}

class SequenceElement {
    Bitmap bitmap;
    double duration;

    public SequenceElement(Bitmap bm, double duration) {
        bitmap = bm;
        this.duration = duration;
    }
}