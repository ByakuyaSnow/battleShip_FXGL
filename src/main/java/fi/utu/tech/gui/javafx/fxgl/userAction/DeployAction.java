package fi.utu.tech.gui.javafx.fxgl.userAction;

import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import fi.utu.tech.gui.javafx.fxgl.BattleShipApp;
import fi.utu.tech.gui.javafx.fxgl.components.BattleshipComponent;
import fi.utu.tech.gui.javafx.fxgl.components.DestroyerComponent;
import fi.utu.tech.gui.javafx.fxgl.components.ShipComponent;
import javafx.geometry.Point2D;
import org.jetbrains.annotations.NotNull;
import static com.almasb.fxgl.dsl.FXGL.*;

public class DeployAction extends UserAction {

    public static Point2D startPos;

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

        try {
            DeployAction.startPos = BattleShipApp.captureEntity.getPosition();
        }catch (NullPointerException e){

        }

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
            /*
            Point2D rightPlace = BattleShipApp.getCloseAnchorPoint(BattleShipApp.captureEntity.getPosition());
            if(BattleShipApp.captureEntity.hasComponent(BattleshipComponent.class)||BattleShipApp.captureEntity.hasComponent(DestroyerComponent.class)){
                System.out.println("POS_CORRECT!");
                if(BattleShipApp.captureEntity.getComponent(ShipComponent.class).isVertical()){
                    var correctX = rightPlace.getX()+25.0;
                    var correctY = rightPlace.getY()+25.0;
                    rightPlace = new Point2D(correctX,correctY);
                }
            }
            BattleShipApp.captureEntity.setPosition(rightPlace);

             */
            if(isMosInBoard(getInput().getMousePositionWorld())){
                Point2D rightPlace = BattleShipApp.getCloseAnchorPoint(BattleShipApp.captureEntity.getPosition());
                if(BattleShipApp.captureEntity.hasComponent(BattleshipComponent.class)||BattleShipApp.captureEntity.hasComponent(DestroyerComponent.class)){
                    System.out.println("POS_CORRECT!");
                    if(BattleShipApp.captureEntity.getComponent(ShipComponent.class).isVertical()){
                        var correctX = rightPlace.getX()+25.0;
                        var correctY = rightPlace.getY()+25.0;
                        rightPlace = new Point2D(correctX,correctY);
                    }
                }
                BattleShipApp.captureEntity.setPosition(rightPlace);
            }else {
                BattleShipApp.captureEntity.setPosition(startPos);
            }



        }catch (NullPointerException e){
            System.out.println("captureEntity do not exist!:" + e.getMessage());
        }

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
