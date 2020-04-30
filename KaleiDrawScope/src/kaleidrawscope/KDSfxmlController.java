package kaleidrawscope;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author lucky
 */
public class KDSfxmlController {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField fileName;

    @FXML
    private Label sizeBrush;

    @FXML
    private Label rainbowLabel;

    @FXML
    private Label color;

    @FXML
    private Label eraserLabel;

    @FXML
    private Label mirror;

    @FXML
    private Label channels;

    @FXML
    private CheckBox mirrorSym;

    @FXML
    private Button sizeUp;

    @FXML
    private Button sizeUp10;

    @FXML
    private Slider brushSize;

    @FXML
    private Button sizeDown;

    @FXML
    private Button sizeDown10;

    @FXML
    private CheckBox eraser;

    @FXML
    private CheckBox rainbow;

    @FXML
    private ImageView imageView;

    //  @FXML
    // private CheckBox symetry;
    @FXML
    private ChoiceBox<String> symetry;

    @FXML
    private Label symetryLabel;
    
    /**
         * *************************************************************************************
         * Title: JavaFX FXML
         * Author: Jakob Jenkov 
         * Date: 2020
         * Version: 2018-03-24 
         * Availability: http://tutorials.jenkov.com/javafx/fxml.html
         * Used: Properties of used FXML components and how to implement them
         *
         **************************************************************************************
         */
    

    public void initialize() {

        /**
         * *************************************************************************************
         * Title: paint 
         * Author: Almas Baimagambetov (almaslvl@gmail.com) 
         * Date: 2020
         * Code version: a2e61db on 10 Apr 2017 
         * Availability: https://github.com/AlmasB/FXTutorials
         * Used: Base insight on making simple paint in JavaFX, onSave method
         *
         **************************************************************************************
         */
        
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, 900, 900);
        sizeBrush.setText("Brush size: " + (int) brushSize.getValue());
        Color[] palette = new Color[]{Color.RED, Color.BLUE, Color.ORANGE, Color.ALICEBLUE, Color.ANTIQUEWHITE, Color.AQUA, Color.BISQUE, Color.BLACK, Color.BLUE, Color.CADETBLUE,
            Color.BLUEVIOLET, Color.BROWN, Color.CRIMSON, Color.DARKMAGENTA, Color.DEEPPINK, Color.GOLD, Color.FORESTGREEN, Color.LAWNGREEN, Color.RED};
        Random rng = new Random();

        /**
         * *************************************************************************************
         * Title: How to change the color of the rectangle after every cycle count?
         * Author: James_D (Stack Overflow)
         * Date: 2020
         * Code version: answered Aug 15 '17 at 12:38
         * Availability: https://stackoverflow.com/questions/45692035/how-to-change-the-color-of-the-rectangle-after-every-cycle-count
         * Used: Way to change the color parameter every update
         *
         **************************************************************************************
         */
        
        sizeBrush.setMinWidth(85.0);

        sizeUp.setOnMouseClicked(e -> {
            brushSize.setValue(brushSize.getValue() + 1);
            sizeBrush.setText("Brush size: " + (int) brushSize.getValue());
        });
        sizeDown.setOnMouseClicked(e -> {
            brushSize.setValue(brushSize.getValue() - 1);
            sizeBrush.setText("Brush size: " + (int) brushSize.getValue());
        });
        sizeUp10.setOnMouseClicked(e -> {
            brushSize.setValue(brushSize.getValue() + 10);
            sizeBrush.setText("Brush size: " + (int) brushSize.getValue());
        });
        sizeDown10.setOnMouseClicked(e -> {
            brushSize.setValue(brushSize.getValue() - 10);
            sizeBrush.setText("Brush size: " + (int) brushSize.getValue());
        });

        double Bw/* value of the canvas */ = canvas.getWidth();
        double Bh/* value of the canvas */ = canvas.getHeight();
        brushSize.setOnMouseDragged(e -> {
            sizeBrush.setText("Brush size: " + (int) brushSize.getValue());
        });

        canvas.setOnMouseDragged(e -> {

            double size = brushSize.getValue();
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            /*x of a ring*/ double Xr = x - (canvas.getWidth() / 2);


            //System.out.println("Xr = " + Xr);

            /*y of a ring*/ double Yr = y - (canvas.getHeight() / 2);
            //System.out.println("Yr = " + Yr);

            /*radius of the circle **2 */
            double R2 = Math.pow(Xr, 2.0) + Math.pow(Yr, 2.0);


            //System.out.println("R2 = " + R2);

            /*radius of the circle*/ double r = Math.sqrt(R2);


            //System.out.println("r = " + r);

            /*distance from the next point*/ double d = (2 * r) * (Math.sin(22.5 * 0.0174532925));


            //System.out.println("d = " + d);

            /*height of the 1/8 of the circle*/ double h = Math.sqrt((r * r) - ((d * d) / 4));


            //System.out.println("h = " + h);

            /*mid-point helpful x for the equation*/ double hX8 = Math.pow(((Xr + Yr) / 2), 2.0);
            /*mid-point helpful y for the equation*/ double hY8 = Math.pow(((Yr - Xr) / 2), 2.0);
            /*mid-point radius*/ double R8 = Math.sqrt(hX8 + hY8);
            /*divide by mid-point radius, multiply by full radius*/ double x8 = ((((Xr + Yr) / 2) / R8) * r) + (canvas.getWidth() / 2);
            /*divide by mid-point radius, multiply by full radius*/ double y8 = ((((Yr - Xr) / 2) / R8) * r) + (canvas.getHeight() / 2);
            /*wualaaa works like a charm*/

            double hX16a = Math.pow(((Xr + x8 - Bw / 2) / 2), 2.0);
            double hY16a = Math.pow(((Yr + y8 - Bh / 2) / 2), 2.0);
            double R16a = Math.sqrt(hX16a + hY16a);

            double hX16b = Math.pow(((x8 + Yr - Bw / 2) / 2), 2.0);
            double hY16b = Math.pow(((y8 - Xr - Bh / 2) / 2), 2.0);
            double R16b = Math.sqrt(hX16b + hY16b);

            double x16a = ((((Xr + x8 - Bw / 2) / 2) / R16a) * r) + (canvas.getWidth() / 2);
            double y16a = ((((Yr + y8 - Bh / 2) / 2) / R16a) * r) + (canvas.getHeight() / 2);

            double x16b = ((((x8 + Yr - Bw / 2) / 2) / R16b) * r) + (canvas.getWidth() / 2);
            double y16b = ((((y8 - Xr - Bh / 2) / 2) / R16b) * r) + (canvas.getHeight() / 2);

            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                if (rainbow.isSelected()) {
                    g.setFill(palette[rng.nextInt(palette.length)]);
                } else {
                    g.setFill(colorPicker.getValue());
                }
                /*base ( 1 )*/ g.fillRect(x, y, size, size);

                //System.out.println("x = " + x + "   I   y = " + y);
                //System.out.println(symetry.getValue());
                if (mirrorSym.isSelected()) {

                    if ("2".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*opposite ( 3 )*/ g.fillRect(Bw - x, y, size, size);
                    }
                    if ("4".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*( 2 )*/ g.fillRect(x, Bh - y, size, size);
                        /*opposite ( 3 )*/ g.fillRect(Bw - x, y, size, size);
                        /*( 4 )*/ g.fillRect(Bw - x, Bh - y, size, size);

                    }
                    if ("8".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*( 2 )*/ g.fillRect(x8, y8, size, size);
                        /*( 3 )*/ g.fillRect(x, Bh - y, size, size);
                        /*( 4 )*/ g.fillRect(x8, Bh - y8, size, size);
                        /*opposite ( 5 )*/ g.fillRect(Bw - x, y, size, size);
                        /*( 6 )*/ g.fillRect(Bw - x8, y8, size, size);
                        /*( 7 )*/ g.fillRect(Bw - x, Bh - y, size, size);
                        /*( 8 )*/ g.fillRect(Bw - x8, Bh - y8, size, size);
                        //System.out.println("x8 = " + x8 + "   I   y8 = " + y8);
                    }
                    if ("16".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*( 2 )*/ g.fillRect(x8, y8, size, size);
                        /*( 3 )*/ g.fillRect(x, Bh - y, size, size);
                        /*( 4 )*/ g.fillRect(x8, Bh - y8, size, size);
                        /*opposite ( 5 )*/ g.fillRect(Bw - x, y, size, size);
                        /*( 6 )*/ g.fillRect(Bw - x8, y8, size, size);
                        /*( 7 )*/ g.fillRect(Bw - x, Bh - y, size, size);
                        /*( 8 )*/ g.fillRect(Bw - x8, Bh - y8, size, size);

                        /*( 9 )*/ g.fillRect(x16a, y16a, size, size);
                        /*( 10 )*/ g.fillRect(x16a, Bh - y16a, size, size);
                        /*( 11 )*/ g.fillRect(Bw - x16a, y16a, size, size);
                        /*( 12 )*/ g.fillRect(Bw - x16a, Bh - y16a, size, size);
                        /*( 13 )*/ g.fillRect(x16b, y16b, size, size);
                        /*( 14 )*/ g.fillRect(x16b, Bh - y16b, size, size);
                        /*( 15 )*/ g.fillRect(Bw - x16b, y16b, size, size);
                        /*( 16 )*/ g.fillRect(Bw - x16b, Bh - y16b, size, size);
                    }

                } else {

                    if ("2".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*opposite ( 3 )*/ g.fillRect(Bw - x, Bh - y, size, size);
                    }
                    if ("4".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*( 2 )*/ g.fillRect(y, Bw - x, size, size);
                        /*opposite ( 3 )*/ g.fillRect(Bw - x, Bh - y, size, size);
                        /*( 4 )*/ g.fillRect(Bh - y, x, size, size);

                    }
                    if ("8".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*( 2 )*/ g.fillRect(x8, y8, size, size);
                        /*( 3 )*/ g.fillRect(y, Bw - x, size, size);
                        /*( 4 )*/ g.fillRect(y8, Bw - x8, size, size);
                        /*opposite ( 5 )*/ g.fillRect(Bw - x, Bh - y, size, size);
                        /*( 6 )*/ g.fillRect(Bw - x8, Bh - y8, size, size);
                        /*( 7 )*/ g.fillRect(Bh - y, x, size, size);
                        /*( 8 )*/ g.fillRect(Bh - y8, x8, size, size);
                        //System.out.println("x8 = " + x8 + "   I   y8 = " + y8);
                    }
                    if ("16".equals(symetry.getValue())) {
                        if (rainbow.isSelected()) {
                            g.setFill(palette[rng.nextInt(palette.length)]);
                        } else {
                            g.setFill(colorPicker.getValue());
                        }
                        /*( 2 )*/ g.fillRect(x8, y8, size, size);
                        /*( 3 )*/ g.fillRect(y, Bw - x, size, size);
                        /*( 4 )*/ g.fillRect(y8, Bw - x8, size, size);
                        /*opposite ( 5 )*/ g.fillRect(Bw - x, Bh - y, size, size);
                        /*( 6 )*/ g.fillRect(Bw - x8, Bh - y8, size, size);
                        /*( 7 )*/ g.fillRect(Bh - y, x, size, size);
                        /*( 8 )*/ g.fillRect(Bh - y8, x8, size, size);
                        
                        /*( 9 )*/ g.fillRect(x16a, y16a, size, size);
                        /*( 10 )*/ g.fillRect(y16a, Bw - x16a, size, size);
                        /*( 11 )*/ g.fillRect(Bw - x16a, Bh - y16a, size, size);
                        /*( 12 )*/ g.fillRect(Bh - y16a, x16a, size, size);
                        /*( 13 )*/ g.fillRect(x16b, y16b, size, size);
                        /*( 14 )*/ g.fillRect(y16b, Bw - x16b, size, size);
                        /*( 15 )*/ g.fillRect(Bw - x16b, Bh - y16b, size, size);
                        /*( 16 )*/ g.fillRect(Bh - y16b, x16b, size, size);
                    }
                }
            }

        });
    }

    public void onSave() {
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(fileName.getText() + ".png"));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onOpen() {
        ImageView image = new ImageView(fileName.getText() + ".png");
    }

    public void onExit() {
        Platform.exit();
    }

}
