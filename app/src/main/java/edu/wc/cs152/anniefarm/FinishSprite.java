package edu.wc.cs152.anniefarm;

/**
 * Created by water on 12/15/2016.
 */

public class FinishSprite extends Sprite {
    BitmapSequence finish= new BitmapSequence();

    public FinishSprite(Vec2d vec2d) {
        super(vec2d);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins2), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins3), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins4), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins5), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins6), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins7), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins8), .08);
        finish.addImage(BitmapRepo.getInstance().getImage(R.drawable.fins9), .08);
        setBitmaps(finish);
    }

    public boolean isActive() {
        return false;
    }

    public void resolve(Collision collision, Sprite other) {
        //next level
    }
}
