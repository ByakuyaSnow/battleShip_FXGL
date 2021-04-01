package fi.utu.tech.gui.javafx.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import fi.utu.tech.gui.javafx.BattleShipApp;
import fi.utu.tech.gui.javafx.BattleShipType;
import fi.utu.tech.gui.javafx.components.ShipComponent;

public class HitShipHandler extends CollisionHandler {
    public HitShipHandler() {
        super(BattleShipType.BULLET, BattleShipType.SHIP);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity ship) {
        bullet.removeFromWorld();

        ship.getComponent(ShipComponent.class).hit();
        /*
        if(!ship.getComponent(ShipComponent.class).isAlive()){
            ship.getComponent(ShipComponent.class).die();
        }

         */

    }
}
