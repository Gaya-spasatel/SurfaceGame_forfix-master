package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;
    private boolean boosting;
    //private final int GRAVITY = -10;
    private int maxX;
    private int minX;
    private int screenX;
    private int screenY;

    private final int MIN_SPEED = 10;
    private final int MAX_SPEED = 40;

    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY){
        Random n = new Random();
        this.screenX = screenX;
        this.screenY = screenY;
        x = screenX-n.nextInt(screenX/2);
        y = n.nextInt(screenY);
        speed = n.nextInt(20);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        maxX = screenX - bitmap.getHeight();
        minX = 0;
        boosting = false;


        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }
    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }
    public void update() {

        if (boosting) {
            speed += 5;
        } else {
            speed -= 5;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        x -= speed;

        if (x < minX) {
            Random n = new Random();
            x = screenX-n.nextInt(screenX/2);
            y = n.nextInt(screenY);
        }


        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x){this.x = x;}

    public void setY(int x){this.y = x;}
    public void setcoordinats(){
        Random n = new Random();
        x = screenX-n.nextInt(screenX/2);
        y = n.nextInt(screenY);
    }
    public int getSpeed() {
        return speed;
    }
}
