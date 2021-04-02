package fi.utu.tech.gui.javafx.fxgl.subscene;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.scene.SubScene;
import fi.utu.tech.gui.javafx.fxgl.BattleShipApp;
import fi.utu.tech.gui.javafx.fxgl.components.BlueTeamComponent;
import fi.utu.tech.gui.javafx.fxgl.components.ShipComponent;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;



public class BluePlayerScene extends SubScene {

    //private boolean canContinue;


    public BluePlayerScene(){
        System.out.println("new BLUE PLAY scene");

        //this.canContinue = true;

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
        System.out.println("BLUE_SCENE_ON_CREATE");

        spawn("background");

        //canContinue = true;
        BattleShipApp.canContinue = true;

        getGameWorld().getEntitiesCopy().forEach(entity -> {
            if(entity.hasComponent(ShipComponent.class)){
                if(entity.hasComponent(BlueTeamComponent.class)){
                    entity.setVisible(true);
                }else{
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
        System.out.println("BLUE_ON_DESTROY");

        System.out.println(getGameWorld().getEntitiesByComponent(BlueTeamComponent.class).size());

    }
    /*
    public boolean isCanContinue() {
        return canContinue;
    }

    public void setCanContinue(boolean canContinue) {
        this.canContinue = canContinue;
    }

     */
}
