package edu.wc.cs152.anniefarm;

/**
 * Created by water on 12/14/2016.
 */

public abstract class MonsterSprite extends PhysicalSprite {
    private Collision lastPlatform;
    private int health=1;

    public MonsterSprite(Vec2d v) {
        super(v);
        setAcceleration(new Vec2d(0,600));      //susceptible to gravity
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void setHealth(int newHealth) { health=newHealth; }
    public int getHealth() { return health;}
    abstract void act();
    abstract boolean isAware(Sprite s);

    public boolean isDead() {
        return health <= 0;
    }

    public void takeDamage(int dmg) {
        health-=dmg;
    }

    public Vec2d drawVectorTo(Sprite s) {
        return (position.subtract(s.getPosition()));
    }

}
