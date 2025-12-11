package de.nicolas.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Ball extends BaseActor {

    private boolean paused;

    public Ball(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("assets/ball.png");

        setSpeed(400);
        setMotionAngle(90);
        setBoundaryPolygon(12);
        setPaused(true);
    }

    public boolean isPaused(){
        return paused;
    }

    public void setPaused(boolean paused){
        this.paused = paused;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!isPaused()){
            // simuliere Gravitation
            setAcceleration(10);
            accelerateAtAngle(270);
            applyPhysics(delta);
        }
    }

    public void bounceOff(BaseActor other){
        Vector2 v = this.preventOverlap(other);
        if(Math.abs(v.x) >= Math.abs(v.y)){
            this.velocityVec.x += -1;
        } else {
            this.velocityVec.y *= -1;
        }
    }
}
