package fi.utu.tech.gui.javafx.fxgl.subscene;

import com.almasb.fxgl.scene.SubScene;
import fi.utu.tech.gui.javafx.fxgl.BattleShipApp;
import fi.utu.tech.gui.javafx.fxgl.components.BlueTeamComponent;
import fi.utu.tech.gui.javafx.fxgl.components.ShipComponent;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BluePlayerDeployScene extends SubScene {

    public BluePlayerDeployScene(){
        spawn("background");
        System.out.println("new BLUE DEPLOY scene");




    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("BLUE_DEPLOY_ON_CREATE");

        BattleShipApp.canContinue = true;

        getGameWorld().getEntitiesCopy().forEach(entity -> {
            if(entity.hasComponent(ShipComponent.class)){
                //entity.addComponent(new DraggableComponent());
                if(entity.hasComponent(BlueTeamComponent.class)){
                    entity.setVisible(true);

                }else{
                    entity.setVisible(false);
                }
            }
        });

    }
}
