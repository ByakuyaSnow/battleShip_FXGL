package fi.utu.tech.gui.javafx.fxgl;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.SimpleGameMenu;

import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import com.almasb.fxgl.input.Input;
import fi.utu.tech.gui.javafx.fxgl.components.*;
import fi.utu.tech.gui.javafx.fxgl.subscene.BluePlayerScene;
import fi.utu.tech.gui.javafx.fxgl.userAction.AttackAction;
import fi.utu.tech.gui.javafx.fxgl.menu.BattleShipMainMenu;
import fi.utu.tech.gui.javafx.fxgl.subscene.BluePlayerDeployScene;
import fi.utu.tech.gui.javafx.fxgl.subscene.RedPlayerDeployScene;
import fi.utu.tech.gui.javafx.fxgl.subscene.RedPlayerScene;
import fi.utu.tech.gui.javafx.fxgl.userAction.DeployAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Map;



public class BattleShipApp extends GameApplication {

    public int sceneIndex = -2;


    public static int gridSize;
    public static int carrierNum;
    public static int battleShipNum;
    public static int cruiserNum;
    public static int submarineNum;
    public static int destroyerNum;

    public static Entity captureEntity;
    public static Entity attackEntity;

    public BluePlayerScene blueScene;
    public RedPlayerScene redScene;

    public BluePlayerDeployScene blueDeployScene;
    public RedPlayerDeployScene redDeployScene;

    public static boolean canContinue = true;

    //public static ArrayList<Entity> blueShips,redShips;



    @Override
    protected void initSettings(GameSettings gameSettings) {
        //those settings can not be changed during runtime

        gameSettings.setWidth(1920);
        gameSettings.setHeight(1080);
        gameSettings.setTitle("BattleShip_FXGL");
        gameSettings.setVersion("0.1");

        //gameSettings.setIntroEnabled(true);
        gameSettings.setFullScreenAllowed(true);
        gameSettings.setFullScreenFromStart(true);
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setGameMenuEnabled(true);

        gameSettings.setSceneFactory(new SceneFactory(){
            @Override
            public FXGLMenu newMainMenu() {
                return new BattleShipMainMenu();
            }

            @Override
            public FXGLMenu newGameMenu(){
                return new SimpleGameMenu();
            }
        });

    }

    @Override
    protected void onPreInit() {
        FXGL.getSettings().setGlobalMusicVolume(0.2);
        FXGL.getSettings().setGlobalSoundVolume(0.5);
        FXGL.loopBGM("CrashedShip.mp3");
    }

    @Override
    protected void initUI() {
        PropertyMap state = getWorldProperties();

        runOnce(()->{
            nextScene();
            getDialogService().showMessageBox("Use LEFT BTN to shot,\nChange Scenes with SPACE,\nDeploy Ships with RIGHT BTN and R");
            getDialogService().showMessageBox("Welcome game,\nAll you need to do is sink all ships of Zerg/Terran.");
        },Duration.ZERO);


        int boardLength = gridSize;
        /*
        Pane player1Board = new Pane();
        for(int x=0; x<boardLength; x++){
            for(int y=0; y<boardLength; y++){
                var box = new Rectangle(50,50);
                box.setFill(Color.TRANSPARENT);
                box.setStrokeWidth(5);
                box.setStroke(Color.WHITE);
                box.setTranslateX(300+x*50);
                box.setTranslateY(300+y*50);
                box.setTranslateZ(-250);
                player1Board.getChildren().add(box);
            }
        }

        Pane player2Board = new Pane();
        for(int x=0; x<boardLength; x++){
            for(int y=0; y<boardLength; y++){
                var box = new Rectangle(50,50);
                box.setFill(Color.TRANSPARENT);
                box.setStrokeWidth(5);
                box.setStroke(Color.WHITE);
                box.setTranslateX(1000+x*50);
                box.setTranslateY(300+y*50);
                box.setTranslateZ(-250);
                player2Board.getChildren().add(box);
            }
        }
        */
        for(int x=0; x<boardLength; x++){
            for(int y=0; y<boardLength; y++){
                var tile = getAssetLoader().loadTexture("Tile.png");
                tile.setTranslateX(300+x*50);
                tile.setTranslateY(300+y*50);
                tile.setTranslateZ(-250);
                getGameScene().addUINodes(tile);
            }
        }

        for(int x=0; x<boardLength; x++){
            for(int y=0; y<boardLength; y++){
                var tile = getAssetLoader().loadTexture("Tile.png");
                tile.setTranslateX(1000+x*50);
                tile.setTranslateY(300+y*50);
                tile.setTranslateZ(-250);
                getGameScene().addUINodes(tile);
            }
        }

        Text textPixels = new Text("DEFAULT");
        textPixels.setStroke(Color.WHITE);
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(50); // y = 100

        Text player1Label = new Text("PLAYER_1");
        player1Label.setStroke(Color.RED);
        player1Label.setTranslateX(1200);
        player1Label.setTranslateY(900);

        Text player2Label = new Text("PLAYER_2");
        player2Label.setStroke(Color.BLUE);
        player2Label.setTranslateX(500);
        player2Label.setTranslateY(900);



        getGameScene().addUINodes(textPixels,player1Label,player2Label);
    }



    @Override
    protected void initInput() {
        Input input = getInput();

        onKeyUp(KeyCode.SPACE,"Change Scene", () -> {
            System.out.println("INDEX_SCENE:"+sceneIndex);
            nextScene();
            //BattleShipApp.isPlayer1Turn = !BattleShipApp.isPlayer1Turn;
        });

        onKeyDown(KeyCode.R,()->{
            try {
                if(captureEntity.hasComponent(ShipComponent.class)){
                    captureEntity.getComponent(ShipComponent.class).rotate();
                }
            }catch (NullPointerException e){

            }
        });

        input.addAction(new DeployAction("Deploy"),MouseButton.SECONDARY);

        input.addAction(new AttackAction("Attack"),MouseButton.PRIMARY);

        /*
        onKeyDown(KeyCode.ENTER,()->{
            this.captureEntity.setPosition(getCloseAnchorPoint(this.captureEntity.getPosition()));
            getGameWorld().getEntitiesByComponent(ShipComponent.class).forEach(entity -> {
                Point2D rightPlace = getCloseAnchorPoint(entity.getPosition());
                entity.setPosition(rightPlace);
            });

        });
         */


    }

    private void nextScene() {

        try {
            if(sceneIndex==1){
                getDialogService().showConfirmationBox("Do you want to start BLUE_TURN?",answer->{
                    if(answer){
                        //getSceneService().pushSubScene(new BluePlayerScene());
                        getSceneService().pushSubScene(blueScene);
                        getSceneService().popSubScene();
                        sceneIndex = 0;
                    }
                });
            }else if(sceneIndex==0){
                getDialogService().showConfirmationBox("Do you want to start RED_TURN",answer->{
                    if(answer){
                        //getSceneService().pushSubScene(new RedPlayerScene());
                        getSceneService().pushSubScene(redScene);
                        getSceneService().popSubScene();
                        sceneIndex = 1;
                    }
                });
            }else if(sceneIndex==-1){
                getDialogService().showConfirmationBox("Deploy your ships_2",answer->{
                    if(answer){
                        //getSceneService().pushSubScene(new RedPlayerDeployScene());
                        getSceneService().pushSubScene(redDeployScene);
                        getSceneService().popSubScene();
                        sceneIndex = 0;
                    }
                });
            }else if(sceneIndex==-2){
                getDialogService().showConfirmationBox("Deploy your ships_1",answer->{
                    if(answer){
                        //getSceneService().pushSubScene(new BluePlayerDeployScene());
                        getSceneService().pushSubScene(blueDeployScene);
                        getSceneService().popSubScene();
                        sceneIndex = -1;
                    }
                });
            }
        }catch (IllegalArgumentException e){

        }


    }


    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("IS_DEPLOY",true);
        vars.put("IS_BLUE_PLAYER_TURN",true);
    }

    @Override
    protected void initGame() {
        //set up all the stuff that needs to be ready before the games starts
        getGameWorld().addEntityFactory(new GameEntityFactory());

        PropertyMap state = getWorldProperties();

        this.sceneIndex = -2;

        spawn("background");
        var container =  spawn("shipContainer",1600,200);
        spawn("shipContainer",1600,500);

        runOnce(()->{
            System.out.println("Init Scene");
            this.blueScene = new BluePlayerScene();
            this.redScene = new RedPlayerScene();
            this.blueDeployScene = new BluePlayerDeployScene();
            this.redDeployScene = new RedPlayerDeployScene();
            /*
            this.gridSize = state.getInt("GRID_SIZE");
            this.carrierNum = state.getInt("CARRIER_AMOUNT");
            this.battleShipNum = state.getInt("BATTLESHIP_AMOUNT");
            this.cruiserNum = state.getInt("CRUISER_AMOUNT");
            this.submarineNum = state.getInt("SUBMARINE_AMOUNT");
            this.destroyerNum = state.getInt("DESTROYER_AMOUNT");
             */
        },Duration.ZERO);

        setupShipEntity();

        //Game LOOP
        run(()->{
            try{
                if(isBlueWin()){
                    showGameOver();
                    getDialogService().showMessageBox("Blue Team Win");
                }
                if(isRedWin()){
                    showGameOver();
                    getDialogService().showMessageBox("Red Team Win");
                }
            }catch (IllegalArgumentException e){

            }
        },Duration.seconds(1));

        //todo: add game loop
        run( ()->{

            if(!BattleShipApp.canContinue){
                System.out.println("nextScene");
                nextScene();
            }

        } ,Duration.seconds(0.5));

    }

    private void setupShipEntity(){
        PropertyMap state = getWorldProperties();

        var carrierNum = this.carrierNum;
        var battleshipNum = this.battleShipNum;
        var cruiserNum = this.cruiserNum;
        var submarineNum = this.submarineNum;
        var destroyerNum = this.destroyerNum;

        state.setValue("BLUE_SHIP",new ArrayList<Entity>());
        state.setValue("RED_SHIP",new ArrayList<Entity>());

        var blueShips = (ArrayList<Entity>)state.getObject("BLUE_SHIP");
        var redShips = (ArrayList<Entity>)state.getObject("RED_SHIP");

        //CARRIER
        for(int i=0; i<carrierNum; i++){
            var blue = spawn("carrier_blue",1600,200);
            blueShips.add(blue);
            redShips.add(spawn("carrier_red",1600,500));
        }
        //BATTLESHIP
        for(int i=0; i<battleshipNum; i++){
            blueShips.add(spawn("battleship_blue",1600,250));
            redShips.add(spawn("battleship_red",1600,550));
        }
        //CRUISER
        for(int i=0; i<cruiserNum;i++){
            blueShips.add(spawn("cruiser_blue",1600,300));
            redShips.add(spawn("cruiser_red",1600,600));
        }
        //SUBMARINE
        for(int i=0; i<submarineNum;i++){
            blueShips.add(spawn("submarine_blue",1600,350));
            redShips.add(spawn("submarine_red",1600,650));
        }
        //DESTROYER
        for(int i=0; i<destroyerNum;i++){
            blueShips.add(spawn("destroyer_blue",1600,400));
            redShips.add(spawn("destroyer_red",1600,700));
        }

    }

    @Override
    protected void initPhysics() {
        //getPhysicsWorld().addCollisionHandler(new HitShipHandler());

    }

    public static Point2D getCloseAnchorPoint(Point2D point){
        int x = (int)point.getX();
        int y = (int) point.getY();
        int closestX = x/50*50;
        int closestY = y/50*50;
        return new Point2D(closestX,closestY);
    }



    public static void showGameOver(){
        getDialogService().showConfirmationBox("Game Over Do you want play again?",yes->{
            if(yes){
                getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
                getGameController().startNewGame();
            }else {
                getGameController().gotoGameMenu();
            }
        });
    }

    public boolean isBlueWin(){
        return getGameWorld().getEntitiesByComponent(RedTeamComponent.class).size()==0;
    }

    public boolean isRedWin(){
        return getGameWorld().getEntitiesByComponent(BlueTeamComponent.class).size()==0;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
