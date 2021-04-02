package fi.utu.tech.gui.javafx.fxgl.subscene;

import com.almasb.fxgl.scene.SubScene;
import fi.utu.tech.gui.javafx.fxgl.BattleShipApp;
import fi.utu.tech.gui.javafx.fxgl.components.RedTeamComponent;
import fi.utu.tech.gui.javafx.fxgl.components.ShipComponent;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RedPlayerDeployScene extends SubScene {

    public RedPlayerDeployScene(){
        spawn("background_2");
        System.out.println("new Red DEPLOY scene");





    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("RED_DEPLOY_ON_CREATE");

        BattleShipApp.canContinue = true;

        getGameWorld().getEntitiesCopy().forEach(entity -> {
            if(entity.hasComponent(ShipComponent.class)){
                //entity.addComponent(new DraggableComponent());
                if(entity.hasComponent(RedTeamComponent.class)){
                    entity.setVisible(true);
                    //entity.addComponent(new DraggableComponent());
                }else{
                    entity.setVisible(false);
                }
            }
        });

    }
}
