package fi.utu.tech.gui.javafx;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class GameEntityFactory implements EntityFactory {

    /* 1x5 */
    @Spawns("carrier")
    public Entity newCarrier(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.CARRIER)
                .viewWithBBox("carrier.png")
                .build();
    }

    /* 1x4 */
    @Spawns("battleShip")
    public Entity newBattleShip(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.BATTLESHIP)
                .viewWithBBox("battle_ship.png")
                .build();
    }

    /* 1x3 */
    @Spawns("cruiser")
    public Entity newCruiser(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.CRUISER)
                .viewWithBBox("cruiser.png")
                .build();
    }

    @Spawns("destroyer")
    public Entity newDestroy(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.DESTROYER)
                .viewWithBBox("destroyer.png")
                .build();
    }

    @Spawns("submarine")
    public Entity newSubmarrine(SpawnData data){
        return entityBuilder(data)
                .type(EntityType.SUBMARINE)
                .viewWithBBox("submarine.png")
                .build();
    }

    //



}
