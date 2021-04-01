package fi.utu.tech.gui.javafx.userAction;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import fi.utu.tech.gui.javafx.BattleShipApp;
import fi.utu.tech.gui.javafx.components.ShipComponent;
import fi.utu.tech.gui.javafx.components.SubmarineComponent;
import javafx.geometry.Point2D;
import org.jetbrains.annotations.NotNull;


import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class AttackAction extends UserAction {
    public AttackAction(@NotNull String name) {
        super(name);
    }

    @Override
    protected void onAction() {
        super.onAction();
        Point2D mousePos = getInput().getMousePositionWorld();

        //System.out.println("mouseCurrentPos:"+mousePos);

    }

    @Override
    protected void onActionBegin() {
        super.onActionBegin();

        Point2D mousePos = getInput().getMousePositionWorld();

        spawn("miss");


    }

    @Override
    protected void onActionEnd() {
        super.onActionEnd();
        //System.out.println("AttackAction_onActionEnd()");
        Point2D mousePos = getInput().getMousePositionWorld();



        getGameWorld().getEntitiesByComponent(ShipComponent.class).forEach(entity -> {

            if(isTheresShip(mousePos,entity)){
                spawn("hit");
                entity.getComponent(ShipComponent.class).hit();
            }



        });


        //System.out.println("mouseUpPos__:"+mousePos);
    }



    private boolean isTheresShip(Point2D mousePos, Entity entity){
        int mouseX = (int) mousePos.getX();
        int mouseY = (int) mousePos.getY();

        Point2D centerPos = entity.getCenter();
        int centerX = (int) centerPos.getX();
        int centerY = (int) centerPos.getY();

        int halfWidth =  (int) entity.getWidth()/2;
        int halfHeight = (int) entity.getHeight()/2;

        boolean isVertical = entity.getComponent(ShipComponent.class).isVertical();

        if(isVertical){
            //consider Y
            boolean xInBox = mouseX>centerX-halfHeight && mouseX<centerX+halfHeight;
            boolean yInBox = mouseY>centerY-halfWidth && mouseY<centerY+halfWidth;

            if(xInBox && yInBox){
                return true;
            }else {
                return false;
            }

        }else {
            //consider X
            boolean xInBox = mouseX>centerX-halfWidth && mouseX<centerX+halfWidth;
            boolean yInBox = mouseY>centerY-halfHeight && mouseY<centerY+halfHeight;

            if(xInBox && yInBox){
                return true;
            }else {
                return false;
            }

        }



    }



}
