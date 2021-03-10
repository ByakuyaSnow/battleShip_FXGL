package fi.utu.tech.gui.javafx;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

public class BattleShipApp extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        //those settings can not be changed during runtime
        gameSettings.setWidth(1025);
        gameSettings.setHeight(1136);
        gameSettings.setTitle("BattleShip_FXGL");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        //set up all the stuff that needs to be ready before the games starts
        //FXGL.getGameWorld().addEntityFactory(new GameEntityFactory());
        player = FXGL.entityBuilder()
                .at(300,300)
                .view("player_1.png")
                .buildAndAttach();
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.D,()-> {
            player.translateX(5);
            FXGL.inc("pixelsMoved",+5);
        });
        FXGL.onKey(KeyCode.A, ()->{
           player.translateX(-5);
            FXGL.inc("pixelsMoved",-5);
        });
        FXGL.onKey(KeyCode.W, ()->{
           player.translateY(-5);
            FXGL.inc("pixelsMoved",+5);
        });
        FXGL.onKey(KeyCode.S,()->{
            player.translateY(5);
            FXGL.inc("pixelsMoved",+5);
        });
        FXGL.onKey(KeyCode.F,()->{
            FXGL.play("mg_8mm.wav");
        });

    }

    @Override
    protected void initUI() {
        //add grid texture
        var gridTexture = FXGL.getAssetLoader().loadTexture("grid.jpg");
        FXGL.getGameScene().addUINode(gridTexture);
        //add title texture
        var titleTexture = FXGL.getAssetLoader().loadTexture("title.png");
        titleTexture.setTranslateX(53);
        titleTexture.setTranslateY(70);
        getGameScene().addUINode(titleTexture);

        Text textPixels = new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(50);

        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty("pixelsMoved").asString());
        FXGL.getGameScene().addUINode(textPixels);


    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved",0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
