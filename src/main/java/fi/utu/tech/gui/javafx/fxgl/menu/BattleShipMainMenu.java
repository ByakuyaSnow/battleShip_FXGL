package fi.utu.tech.gui.javafx.fxgl.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import fi.utu.tech.gui.javafx.fxgl.BattleShipApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import static com.almasb.fxgl.dsl.FXGL.*;


public class BattleShipMainMenu extends FXGLMenu {

    public BattleShipMainMenu() {
        super(MenuType.MAIN_MENU);
        //BackGround
        var background = FXGL.getAssetLoader().loadTexture("Nebula_Red.png");

        //Title
        var title =getUIFactoryService().newText(getSettings().getTitle(),Color.WHITE,46.0);
        title.setStroke(Color.WHITESMOKE);
        title.setStrokeWidth(1.5);
        title.setEffect(new Bloom(0.6));
        centerTextBind(title, getAppWidth() / 2.0, 200);
        //Version
        var version = getUIFactoryService().newText(getSettings().getVersion(), Color.WHITE, 22.0);
        centerTextBind(version, getAppWidth() / 2.0, 220);

        //startGameMenuButton and disable binding
        var startGameMenuButton = new MenuButton("New Game", ()->fireStartGame());

        //MenuBox
        var menuBox = new VBox(
                4,
                new MenuButton("Game Setup", ()-> fireGameSetting()),
                startGameMenuButton,
                new MenuButton("Contribution", () -> showContribution()),
                new MenuButton("Exit", () -> fireExit())
        );

        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(80);
        menuBox.setTranslateY(620);


        getContentRoot().getChildren().setAll(background,title,menuBox);
    }

    private void fireGameSetting(){

        Button confirmBtn = getUIFactoryService().newButton("OK");
        confirmBtn.setPrefWidth(300);
        getDialogService().showBox("Game Setup",getSetUpContent(),confirmBtn);

        getDialogService().showInputBox("Please enter your name(Player2)", answer ->{
            getWorldProperties().setValue("PLAYER_2_NAME",answer);
            System.out.println("PLAYER_2_NAME:"+getWorldProperties().getString("PLAYER_2_NAME"));
        });

        getDialogService().showInputBox("Please enter your name(Player1)", answer ->{
            getWorldProperties().setValue("PLAYER_1_NAME",answer);
            System.out.println("PLAYER_1_NAME:"+getWorldProperties().getString("PLAYER_1_NAME"));
        });

    }

    private void fireStartGame(){

        if(isTooManyShip()){
            getDialogService().showMessageBox("GAME SETUP ERROR:\nThat could caused by there are too much ship on board");
        }else {
            fireNewGame();
        }

    }


    private void showContribution(){
        getDialogService().showMessageBox(
                "Aowen Shi(aowshi@utu.fi)\n"+
                   "Guanghang Chen(guchen@utu.fi)\n"+
                   "Jiachen Yao(jiayao@utu.fi)"
        );
    }

    private Parent getSetUpContent(){

        ChoiceBox<Integer> gridSizeChoice = new ChoiceBox<>();
        gridSizeChoice.getItems().addAll(5,6,7,8,9,10);
        gridSizeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                getWorldProperties().setValue("GRID_SIZE",t1);
                System.out.println("GRID_SIZE:"+ getWorldProperties().getInt("GRID_SIZE"));
                BattleShipApp.gridSize = t1;
            }
        });
        HBox gridSet = new HBox(
                getUIFactoryService().newText("GridSize:"),
                gridSizeChoice
        );


        ChoiceBox<Integer> carrierChoice = new ChoiceBox<>();
        carrierChoice.getItems().addAll(1,2,3);
        carrierChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                getWorldProperties().setValue("CARRIER_AMOUNT",t1);
                System.out.println("CARRIER_AMOUNT:"+getWorldProperties().getInt("CARRIER_AMOUNT"));
                BattleShipApp.carrierNum = t1;
            }
        });
        HBox carrierSet = new HBox(
                getUIFactoryService().newText("Carrier:"),
                carrierChoice
        );


        ChoiceBox<Integer> battleshipChoice = new ChoiceBox<>();
        battleshipChoice.getItems().addAll(1,2,3);
        battleshipChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                getWorldProperties().setValue("BATTLESHIP_AMOUNT",t1);
                System.out.println("BATTLESHIP_AMOUNT:"+getWorldProperties().getInt("BATTLESHIP_AMOUNT"));
                BattleShipApp.battleShipNum = t1;
            }
        });
        HBox battleShipSet = new HBox(
                getUIFactoryService().newText("BattleShip:"),
                battleshipChoice
        );


        ChoiceBox<Integer> cruiserChoice = new ChoiceBox<>();
        cruiserChoice.getItems().addAll(1,2,3);
        cruiserChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                getWorldProperties().setValue("CRUISER_AMOUNT",t1);
                System.out.println("CRUISER_AMOUNT:"+getWorldProperties().getInt("CRUISER_AMOUNT"));
                BattleShipApp.cruiserNum = t1;
            }
        });
        HBox cruiserSet = new HBox(
                getUIFactoryService().newText("Cruiser:"),
                cruiserChoice
        );


        ChoiceBox<Integer> submarineChoice = new ChoiceBox<>();
        submarineChoice.getItems().addAll(1,2,3);
        submarineChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                getWorldProperties().setValue("SUBMARINE_AMOUNT",t1);
                System.out.println("SUBMARINE_AMOUNT:"+getWorldProperties().getInt("SUBMARINE_AMOUNT"));
                BattleShipApp.submarineNum = t1;
            }
        });
        HBox submarineSet = new HBox(
                getUIFactoryService().newText("Submarine:"),
                submarineChoice
        );


        ChoiceBox<Integer> destroyerChoice = new ChoiceBox<>();
        destroyerChoice.getItems().addAll(1,2,3);
        destroyerChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                getWorldProperties().setValue("DESTROYER_AMOUNT",t1);
                System.out.println("DESTROYER_AMOUNT:"+getWorldProperties().getInt("DESTROYER_AMOUNT"));
                BattleShipApp.destroyerNum = t1;
            }
        });
        HBox destroyerSet = new HBox(
                getUIFactoryService().newText("Destroyer:"),
                destroyerChoice
        );

        VBox root = new VBox(gridSet,carrierSet,battleShipSet,cruiserSet,submarineSet,destroyerSet);

        return root;
    }


    private static class MenuButton extends Parent {
        MenuButton(String name, Runnable actioin){
            var text = getUIFactoryService().newText(name, Color.DARKRED, 80.00);
            text.setStrokeWidth(2.0);
            text.strokeProperty().bind(text.fillProperty());
            text.setEffect(new Bloom(0.6));

            text.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(Color.RED)
                            .otherwise(Color.DARKRED)
            );

            setOnMouseClicked(e -> actioin.run());

            setPickOnBounds(true);

            getChildren().add(text);
        }

    }

    private boolean isTooManyShip(){
        var state = getWorldProperties();

        try {
            int boardLength = state.getInt("GRID_SIZE");

            int carrierOccupied = state.getInt("CARRIER_AMOUNT") * 5;
            int battleshipOccupied = state.getInt("BATTLESHIP_AMOUNT") * 4;
            int cruiserOccupied = state.getInt("CRUISER_AMOUNT") * 3;
            int submarineOccupied = state.getInt("SUBMARINE_AMOUNT") * 3;
            int destroyOccupied = state.getInt("DESTROYER_AMOUNT") * 2;
            int totalOccupied = carrierOccupied + battleshipOccupied + cruiserOccupied + submarineOccupied + destroyOccupied;

            if (boardLength * boardLength <= totalOccupied * 2) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return true;
        }

    }


}
