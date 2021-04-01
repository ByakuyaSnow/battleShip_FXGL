package fi.utu.tech.gui.javafx.subscene;

import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.scene.SubScene;
import fi.utu.tech.gui.javafx.BattleShipApp;
import fi.utu.tech.gui.javafx.components.BlueTeamComponent;
import fi.utu.tech.gui.javafx.components.RedTeamComponent;
import fi.utu.tech.gui.javafx.components.ShipComponent;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class RedPlayerDeployScene extends SubScene {

    public RedPlayerDeployScene(){
        spawn("background_2");
        System.out.println("new Red DEPLOY scene");





    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("RED_DEPLOY_ON_CREATE");

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
