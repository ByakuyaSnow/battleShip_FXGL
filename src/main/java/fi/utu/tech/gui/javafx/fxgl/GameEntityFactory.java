package fi.utu.tech.gui.javafx.fxgl;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.*;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import fi.utu.tech.gui.javafx.fxgl.components.*;
import fi.utu.tech.gui.javafx.fxgl.components.*;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameEntityFactory implements EntityFactory {

    //BLUE: 1x2
    @Spawns("destroyer_blue")
    public Entity newBlueDestroyer(SpawnData data){
        var texture = FXGL.texture("ships/ship_b_2_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.BLUE)
                .type(BattleShipType.DESTROYER)
                .with(new ShipComponent(2))
                .with(new DestroyerComponent())
                .with(new BlueTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //BLUE: 1x3_1
    @Spawns("submarine_blue")
    public Entity newBlueSubmarine(SpawnData data){
        var texture = FXGL.texture("ships/ship_b_3_1_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.BLUE)
                .type(BattleShipType.SUBMARINE)
                .with(new ShipComponent(3))
                .with(new SubmarineComponent())
                .with(new BlueTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //BLUE: 1x3_2
    @Spawns("cruiser_blue")
    public Entity newBlueCruiser(SpawnData data){
        var texture = FXGL.texture("ships/ship_b_3_2_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.BLUE)
                .type(BattleShipType.CRUISER)
                .with(new ShipComponent(3))
                .with(new CruiserComponent())
                .with(new BlueTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //BLUE: 1x4
    @Spawns("battleship_blue")
    public Entity newBlueBattleship(SpawnData data){
        var texture = FXGL.texture("ships/ship_b_4_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.BLUE)
                .type(BattleShipType.BATTLESHIP)
                .with(new ShipComponent(4))
                .with(new BattleshipComponent())
                .with(new BlueTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //BLUE: 1x5
    @Spawns("carrier_blue")
    public Entity newBlueCarrier(SpawnData data){
        var texture = FXGL.texture("ships/ship_b_5_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.BLUE)
                .type(BattleShipType.CARRIER)
                .with(new ShipComponent(5))
                .with(new CarrierComponent())
                .with(new BlueTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //RED: 1x2
    @Spawns("destroyer_red")
    public Entity newRedDestroyer(SpawnData data){
        var texture = FXGL.texture("ships/ship_r_2_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.RED)
                .type(BattleShipType.DESTROYER)
                .with(new ShipComponent(2))
                .with(new DestroyerComponent())
                .with(new RedTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //RED: 1x3_1
    @Spawns("submarine_red")
    public Entity newRedSubmarine(SpawnData data){
        var texture = FXGL.texture("ships/ship_r_3_1_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.RED)
                .type(BattleShipType.SUBMARINE)
                .with(new ShipComponent(3))
                .with(new SubmarineComponent())
                .with(new RedTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //RED: 1x3_2
    @Spawns("cruiser_red")
    public Entity newRedCruiser(SpawnData data){
        var texture = FXGL.texture("ships/ship_r_3_2_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.RED)
                .type(BattleShipType.CRUISER)
                .with(new ShipComponent(3))
                .with(new CruiserComponent())
                .with(new RedTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //RED: 1x4
    @Spawns("battleship_red")
    public Entity newRedBattleship(SpawnData data){
        var texture = FXGL.texture("ships/ship_r_4_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.RED)
                .type(BattleShipType.BATTLESHIP)
                .with(new ShipComponent(4))
                .with(new BattleshipComponent())
                .with(new RedTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }

    //RED: 1x5
    @Spawns("carrier_red")
    public Entity newRedCarrier(SpawnData data){
        var texture = FXGL.texture("ships/ship_r_5_h.png");

        return FXGL.entityBuilder(data)
                .viewWithBBox(texture)
                .type(BattleShipType.SHIP)
                .type(BattleShipType.RED)
                .type(BattleShipType.CARRIER)
                .with(new ShipComponent(5))
                .with(new CarrierComponent())
                .with(new RedTeamComponent())
                .with(new DraggableComponent())
                .with(new CollidableComponent(true))
                //.collidable()
                .build();
    }


    @Spawns("background")
    public Entity newBackground(SpawnData data){
        var texture = FXGL.texture("bg.jpg");

        return FXGL.entityBuilder(data)
                .view(texture)
                .zIndex(-500)
                .build();

    }

    @Spawns("background_2")
    public Entity newBackground2(SpawnData data){
        var texture = FXGL.texture("bg2.jpg");

        return FXGL.entityBuilder(data)
                .view(texture)
                .zIndex(-500)
                .build();
    }

    @Spawns("shipContainer")
    public Entity newShipContainer(SpawnData data){
        var textute = FXGL.texture("ShipContainer.png");

        return FXGL.entityBuilder(data)
                .view(textute)
                .zIndex(-450)
                .build();

    }

    @Spawns("hit")
    public Entity newHit(SpawnData data){
        Point2D pos = BattleShipApp.getCloseAnchorPoint(getInput().getMousePositionWorld());

        var texture = FXGL.texture("Player1Hit.png");

        return FXGL.entityBuilder(data)
                .at(pos)
                .view(texture)
                .zIndex(-400)
                .build();
    }


    @Spawns("miss")
    public Entity newMiss(SpawnData data){
        Point2D pos = BattleShipApp.getCloseAnchorPoint(getInput().getMousePositionWorld());

        var texture = FXGL.texture("Player1.png");

        return FXGL.entityBuilder(data)
                .at(pos)
                .view(texture)
                .zIndex(-400)
                .build();
    }

    @Spawns("explosion")
    public Entity newExplosion(SpawnData data){

        var texture = texture("sprites/explosion.png");

        return entityBuilder()
                .from(data)
                .view(texture.toAnimatedTexture(16, Duration.seconds(0.66)).play())
                .with(new ExpireCleanComponent(Duration.seconds(0.66)))
                .build();
    }

    @Spawns("hit_effect")
    public Entity newHitEffect(SpawnData data){
        Point2D pos = getInput().getMousePositionWorld();
        var texture = texture("sprites/hit_effect.png");

        return entityBuilder(data)
                .at(pos)
                .view(texture.toAnimatedTexture(7,Duration.seconds(0.33)).play())
                .with(new ExpireCleanComponent(Duration.seconds(0.33)))
                .build();
    }




}
