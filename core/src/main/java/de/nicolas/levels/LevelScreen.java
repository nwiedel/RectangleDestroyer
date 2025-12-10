package de.nicolas.levels;

import com.badlogic.gdx.Gdx;
import de.nicolas.actors.Brick;
import de.nicolas.actors.Paddle;
import de.nicolas.actors.Wall;
import de.nicolas.utils.actors.BaseActor;
import de.nicolas.utils.screens.BaseScreen;

public class LevelScreen extends BaseScreen {

    private Paddle paddle;

    private BaseActor background;

    @Override
    public void initialize() {
        background = new BaseActor(0, 0, mainStage);
        background.loadTexture("assets/space.png");
        BaseActor.setWorldBounds(background);

        paddle = new Paddle(320, 32, mainStage);

        new Wall(0, 0, 20, 600, mainStage); // linke Wand
        new Wall(780, 0, 20, 600, mainStage); // rechte Wand
        new Wall(0, 550, 800, 50, mainStage); // obere Wand

        Brick tempBrick = new Brick(0, 0,mainStage);
        float brickWidth = tempBrick.getWidth() + 5;
        float brickHeight = tempBrick.getHeight() + 5;
        tempBrick.remove();
        int totalRows = 10;
        int totalCols = 10;
        float marginX = (800 - totalCols * brickWidth) / 2;
        float marginY = (600 - totalRows * brickHeight) - 100;

        for (int rowNum = 0; rowNum < totalRows; rowNum++){
            for (int colNum = 0; colNum < totalCols; colNum++){
                float x = marginX + brickWidth * colNum;
                float y = marginY + brickHeight * rowNum;
                new Brick(x, y, mainStage);
            }
        }
    }

    @Override
    public void update(float delta) {

        float mouseX = Gdx.input.getX();
        paddle.setX(mouseX - paddle.getWidth() / 2);
        paddle.boundToWorld();
    }
}
