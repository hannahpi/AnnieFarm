package edu.wc.cs152.anniefarm;

/**
 * Created by shaffer on 4/28/16.
 */
public class PlatformSprite extends Sprite {
    static int resId = R.drawable.platform1;
    public PlatformSprite(Vec2d p) {
        super(p);
        BitmapSequence s = new BitmapSequence();
        s.addImage(BitmapRepo.getInstance().getImage(resId), 1);
        setBitmaps(s);
    }

    static public void setSprite(int newResId) {
        resId = newResId;
    }

    @Override
    public boolean isActive() {
        return false;
    }

}
