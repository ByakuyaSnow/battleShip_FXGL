package fi.utu.tech.gui.javafx.fxgl.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.entity.component.Component;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class ShipComponent extends Component {

    int health;
    int type;



    public ShipComponent(int health) {
        this.health = health;
        this.type = health;

    }

    public void getCenter(){
        entity.getCenter();
    }

    public void rotate(){
        entity.setRotation(entity.getRotation()+90);
        System.out.println(isVertical());
    }

    public boolean isVertical(){
        return entity.getRotation()%180!=0;
    }

    public void hit(){
        health--;
        if(health<=0){
            play("explosion.wav");
            getDialogService().showMessageBox("A ship has been sunk(sinked)");
            entity.removeFromWorld();
        }

    }

    public boolean isDead(){
        return health<=0;
    }

    public int getType(){
        return this.type;
    }



}
