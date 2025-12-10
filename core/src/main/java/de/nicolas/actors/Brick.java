package de.nicolas.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.nicolas.utils.actors.BaseActor;

public class Brick extends BaseActor {

    public Brick(float x, float y, Stage stage) {
        super(x, y, stage);
        loadTexture("assets/brick-gray.png");
    }
}
