package edu.wc.cs152.anniefarm;

/**
 * Created by water on 12/15/2016.
 */

public class DeathSprite extends Sprite {
    boolean done = false;
    BitmapSequence death = new BitmapSequence();

    public DeathSprite(Vec2d vec2d) {
        super(vec2d);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death2), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death3), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death4), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death5), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death6), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death7), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death8), .4);
        death.addImage(BitmapRepo.getInstance().getImage(R.drawable.death9), .4);
        setBitmaps(death);
    }

    public boolean isActive() {
        return false;
    }

    public boolean isDone() {
        return getBitmaps().isDone();
    }


}
