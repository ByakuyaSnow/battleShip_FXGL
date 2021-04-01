package fi.utu.tech.gui.javafx.menu;

import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class ColorBlock extends Rectangle {

    public ColorBlock(int size, Color color){
        super(size,size,color);

        setArcHeight(8);
        setArcWidth(8);

        setStrokeType(StrokeType.INSIDE);
        setStrokeWidth(2.5);
        setStroke(Color.color(0.138, 0.138, 0.375, 0.66));

        var shadow = new InnerShadow(25,Color.BLACK);
        shadow.setOffsetX(-3);
        shadow.setOffsetY(-3);

        setEffect(shadow);
    }


}
