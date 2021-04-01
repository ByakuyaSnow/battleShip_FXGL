package fi.utu.tech.gui.javafx.userAction;

import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.input.UserAction;
import fi.utu.tech.gui.javafx.BattleShipApp;
import javafx.geometry.Point2D;
import org.jetbrains.annotations.NotNull;
import static com.almasb.fxgl.dsl.FXGL.*;

public class DeployAction extends UserAction {

    public DeployAction(@NotNull String name) {
        super(name);
    }

    @Override
    protected void onAction() {
        super.onAction();
       // System.out.println("DeployAction_onAction()");


    }

    @Override
    protected void onActionBegin() {
        super.onActionBegin();
        System.out.println("DeployAction_onActionBegin()");
        var dragableEntities = getGameWorld().getEntitiesByComponent(DraggableComponent.class);
        dragableEntities.forEach(entity -> {
            if(entity.getComponent(DraggableComponent.class).isDragging()){
                BattleShipApp.captureEntity = entity;
            }
        });
    }

    @Override
    protected void onActionEnd() {
        super.onActionEnd();
        System.out.println("DeployAction_onActionEnd()");

        try{
            Point2D rightPlace = BattleShipApp.getCloseAnchorPoint(BattleShipApp.captureEntity.getPosition());
            BattleShipApp.captureEntity.setPosition(rightPlace);
        }catch (NullPointerException e){
            System.out.println("captureEntity do not exist!:" + e.getMessage());
        }

    }
}
