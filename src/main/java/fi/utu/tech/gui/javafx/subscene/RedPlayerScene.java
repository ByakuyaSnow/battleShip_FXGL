package fi.utu.tech.gui.javafx.subscene;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.scene.SubScene;
import fi.utu.tech.gui.javafx.BattleShipApp;
import fi.utu.tech.gui.javafx.components.BlueTeamComponent;
import fi.utu.tech.gui.javafx.components.RedTeamComponent;
import fi.utu.tech.gui.javafx.components.ShipComponent;
import javafx.geometry.Point2D;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * todo: render content from GameLogic
 */

public class RedPlayerScene extends SubScene {


    public RedPlayerScene(){

        System.out.println("new RED PLAY scene");




        getContentRoot().getChildren().addAll();

    }



    @Override
    public void onEnteredFrom(Scene prevState) {
        animationBuilder()
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .translate(getContentRoot())
                .from(new Point2D(-350, 250))
                .to(new Point2D(50, 250))
                .buildAndPlay(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("RED_PLAYER_ON_CREATE");

        spawn("background_2");



        getGameWorld().getEntitiesCopy().forEach(entity -> {
            if(entity.hasComponent(ShipComponent.class)){
                if(entity.hasComponent(RedTeamComponent.class)){
                    entity.setVisible(true);
                }else {
                    entity.setVisible(false);
                }
            }
            if(entity.hasComponent(DraggableComponent.class)){
                entity.removeComponent(DraggableComponent.class);
            }

        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("RED_ON_DESTROY");

        System.out.println(getGameWorld().getEntitiesByComponent(RedTeamComponent.class).size());


    }
}
