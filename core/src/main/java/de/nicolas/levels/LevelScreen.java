package de.nicolas.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import de.nicolas.actors.Ball;
import de.nicolas.actors.Brick;
import de.nicolas.actors.Paddle;
import de.nicolas.actors.Wall;
import de.nicolas.utils.actors.BaseActor;
import de.nicolas.utils.screens.BaseScreen;

public class LevelScreen extends BaseScreen {

    private Paddle paddle;

    private BaseActor background;

    private Ball ball;

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

        ball = new Ball(0, 0, mainStage);
    }

    @Override
    public void update(float delta) {

        float mouseX = Gdx.input.getX();
        paddle.setX(mouseX - paddle.getWidth() / 2);
        paddle.boundToWorld();

        if(ball.isPaused()){
            ball.setX(paddle.getX() + paddle.getWidth() / 2 - ball.getWidth() / 2);
            ball.setY(paddle.getY() + paddle.getHeight() / 2 + ball.getHeight() / 2);
        }

        for (BaseActor wall : BaseActor.getList(mainStage, "de.nicolas.actors.Wall")){
            if (ball.overlaps(wall)){
                ball.bounceOff(wall);
            }
        }

        for (BaseActor brick : BaseActor.getList(mainStage, "de.nicolas.actors.Brick")){
            if (ball.overlaps(brick)){
                ball.bounceOff(brick);
                brick.remove();
            }
        }

        if (ball.overlaps(paddle)){
            float ballCenterX = ball.getX() + ball.getWidth() / 2;
            float paddlePercentHit = (ballCenterX - paddle.getX()) / paddle.getWidth();
            float bounceAngle = MathUtils.lerp(150, 30, paddlePercentHit);
            ball.setMotionAngle(bounceAngle);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(ball.isPaused()){
            ball.setPaused(false);
        }
        return false;
    }
}
