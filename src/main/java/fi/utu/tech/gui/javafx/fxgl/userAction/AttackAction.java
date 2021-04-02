package fi.utu.tech.gui.javafx.fxgl.userAction;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.input.UserAction;
import fi.utu.tech.gui.javafx.fxgl.BattleShipApp;
import fi.utu.tech.gui.javafx.fxgl.components.ShipComponent;
import javafx.geometry.Point2D;
import javafx.util.Duration;
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
        play("shot.wav");
        Point2D mousePos = getInput().getMousePositionWorld();

        if(isMosInBoard(mousePos)){
            spawn("miss");
        }

    }

    @Override
    protected void onActionEnd() {
        super.onActionEnd();
        //System.out.println("AttackAction_onActionEnd()");
        Point2D mousePos = getInput().getMousePositionWorld();


        if(isMosInBoard(mousePos)){
            BattleShipApp.canContinue = false;
        }

        getGameWorld().getEntitiesByComponent(ShipComponent.class).forEach(entity -> {

            if(isTheresShip(mousePos,entity)){

                var correctPos = new Point2D(mousePos.getX()-50,mousePos.getY()-50);

                spawn("hit");
                spawn("explosion",correctPos);
                play("hit.wav");
                BattleShipApp.canContinue = true;
                entity.getComponent(ShipComponent.class).hit();
            }

        });




        System.out.println("mouseUpPos__:"+mousePos);
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

    public boolean isMosInBoard(Point2D mosPos){
        var mosX = mosPos.getX();
        var mosY = mosPos.getY();

        var size = BattleShipApp.gridSize;


        boolean isYAllow = mosY>=300 & mosY<=300 + (50*size);

        boolean isXAllow_0 = mosX>=300 & mosX<=300 + (50*size);
        boolean isXAllow_1 = mosX>=1000 & mosX<=1000 + (50*size);

        if(isYAllow & isXAllow_0){
            System.out.println("IN_BOARD_0");
            return true;
        }

        if(isYAllow & isXAllow_1){
            System.out.println("IN_BOARD_1");
            return true;
        }

        System.out.println("NOT_IN_BOARD");
        return false;
    }




}
