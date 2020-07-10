package sample;

import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import sample.nodes.RandomSortNode;
import sample.nodes.SortNode;
import sample.sorts.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SortingController extends BorderPane {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 800;
    public static final int NUM_NODES = 50;
    public static final int TOP_GAP = 100;
    public static final int X_GAP = 5;

    private Pane sortDisplay;
    private HBox buttons;
    private HBox sizeFields;

    private Button sortButton;
    private Button randomButton;
    private ChoiceBox<AbstractSort> choiceBox;
    public Slider sizeSlider;
    private Label sizeText;
    private CheckBox darkMode;

    private float DX;

    private static AbstractSort abstractSort;

    private SortNode[] sortNodes;

    public SortingController() {
        this.sortDisplay = new Pane();
        this.buttons = new HBox();
        this.sizeFields = new HBox();

        this.setCenter(sortDisplay);
        this.setTop(buttons);
        this.setBottom(sizeFields);

        this.sortButton = new Button("Sort");
        this.randomButton = new Button("Randomize");
        this.choiceBox = new ChoiceBox<AbstractSort>();
        this.darkMode = new CheckBox("Dark Mode");

        this.sizeSlider = new Slider(10, 90, 50);
        sizeSlider.setMajorTickUnit(20);
        sizeSlider.setMinorTickCount(10);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setShowTickLabels(true);

        this.sizeText = new Label("Size: " + this.getSizeSlider());

        buttons.getChildren().add(this.sortButton);
        buttons.getChildren().add(this.randomButton);
        buttons.getChildren().add(this.choiceBox);
        buttons.getChildren().add(this.darkMode);

        buttons.setAlignment(Pos.CENTER);

        sizeFields.getChildren().add(this.sizeSlider);
        sizeFields.getChildren().add(this.sizeText);
        sizeFields.setAlignment(Pos.CENTER);

        DX = (float)WIDTH / this.getSizeSlider();

        ArrayList<AbstractSort> sortList = new ArrayList<>();
        sortList.add(new BubbleSort());
        sortList.add(new InsertionSort());
        sortList.add(new SelectionSort());
        sortList.add(new MergeSort());
        sortList.add(new QuickSort());
        sortList.add(new HeapSort());
        sortNodes = RandomSortNode.getRandomSortNodes(SortingController.NUM_NODES);


        sortDisplay.getChildren().addAll(Arrays.asList(sortNodes));

        sortButton.setOnAction(event -> {
            sortButton.setDisable(true);
            randomButton.setDisable(true);
            sizeSlider.setDisable(true);

            abstractSort = choiceBox.getSelectionModel().getSelectedItem();

            SequentialTransition sq = new SequentialTransition();

            sq.getChildren().addAll(abstractSort.startSort(sortNodes, DX));

            sq.setOnFinished(e-> {
                randomButton.setDisable(false);
            });

            sq.play();
        });

        randomButton.setOnAction(event -> {
            sortButton.setDisable(false);
            sizeSlider.setDisable(false);
            sortDisplay.getChildren().clear();
            sortNodes = RandomSortNode.getRandomSortNodes(this.getSizeSlider());
            sortDisplay.getChildren().addAll(Arrays.asList(sortNodes));
        });

        choiceBox.setItems(FXCollections.observableArrayList(sortList));

        choiceBox.setConverter(new StringConverter<AbstractSort>() {
            @Override
            public String toString(AbstractSort sort) {
                if(sort == null){
                    return"";
                }
                else {
                    return sort.getClass().getSimpleName();
                }
            }

            @Override
            public AbstractSort fromString(String string) {
                return null;
            }
        });

        sizeSlider.setOnMouseDragged(event -> {
            sizeText.setText("Size: " + this.getSizeSlider());
            sortDisplay.getChildren().clear();
            sortNodes = RandomSortNode.getRandomSortNodes((int)sizeSlider.getValue());
            System.out.println(this.getSizeSlider());
            sortDisplay.getChildren().addAll(Arrays.asList(sortNodes));
            DX = (float) WIDTH / this.getSizeSlider();
        });

        darkMode.setOnMouseClicked(event -> {
            if(darkMode.isSelected()) {
                System.out.println("Selected");
                System.out.println(System.getProperty("user.dir"));
                this.getStylesheets().add("dark.css");
//                buttons.getStylesheets().add("dark.css");
//                sortDisplay.getStylesheets().add("dark.css");
//                sizeFields.getStylesheets().add("dark.css");
//                buttons.setStyle("-fx-background-color: rgb(68, 68, 68)");
//                sortDisplay.setStyle("-fx-background-color: rgb(68, 68, 68)");
//                sizeFields.setStyle("-fx-background-color: rgb(68, 68, 68)");
            }
            else {
                System.out.println("Not Selected");
                this.getStylesheets().remove("dark.css");
            }
        });

    }

    public int getSizeSlider() {
        return (int)sizeSlider.getValue();
    }
}
