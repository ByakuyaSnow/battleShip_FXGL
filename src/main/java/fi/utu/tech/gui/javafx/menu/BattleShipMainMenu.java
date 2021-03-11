package fi.utu.tech.gui.javafx.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import static com.almasb.fxgl.dsl.FXGL.*;


public class BattleShipMainMenu extends FXGLMenu {

    public BattleShipMainMenu() {
        super(MenuType.MAIN_MENU);
        var gridWallpaper = FXGL.getAssetLoader().loadTexture("grid.jpg");
        var title = FXGL.getAssetLoader().loadTexture("title.png");
        title.setTranslateX(53);
        title.setTranslateY(70);



        var menuBox = new VBox(
                3,
                new MenuButton("New Game", () -> fireNewGame()),
                new MenuButton("Credits", () -> showCredits()),
                new MenuButton("Exit", () -> fireExit())
        );
        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(getAppWidth()/2.0 -190);
        menuBox.setTranslateY(getAppHeight()/2.0 + 80);


        getContentRoot().getChildren().setAll(gridWallpaper,title,menuBox);
    }


    private void showCredits(){
        getDialogService().showMessageBox(
                "Aowen Shi(aowshi@utu.fi)\n"+
                   "Guanghang Chen(guchen@utu.fi)\n"+
                   "Jiachen Yao(jiayao@utu.fi)"
        );
    }

    private static class MenuButton extends Parent {
        MenuButton(String name, Runnable actioin){
            var text = getUIFactoryService().newText(name, Color.GRAY, 70.00);
            text.setStrokeWidth(1.5);
            text.strokeProperty().bind(text.fillProperty());

            text.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(Color.BLACK)
                            .otherwise(Color.GRAY)
            );

            setOnMouseClicked(e -> actioin.run());

            setPickOnBounds(true);

            getChildren().add(text);
        }

    }

}
