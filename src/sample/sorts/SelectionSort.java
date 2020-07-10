package sample.sorts;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import sample.SortingController;
import sample.nodes.SortNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort extends AbstractSort {

    private ArrayList<Transition> transitions;
    private float DX;

    private static final Color MIN_COLOR = Color.LIGHTGREEN;
    private static final Color NEW_MIN_COLOR = Color.ORANGE;

    public SelectionSort() {
        this.transitions = new ArrayList<>();
    }

    private ParallelTransition colorNode(SortNode[] arr, int s1, int s2, Color s1Color, Color s2Color) {
        ParallelTransition pt = new ParallelTransition();

        pt.getChildren().addAll(colorNode(arr, s1Color, s1), colorNode(arr, s2Color, s2));
        return pt;
    }

    private void selectionSort(SortNode[] arr) {
        int minIndex;

        for(int i = 0; i < arr.length; i++) {
            minIndex = i;
            transitions.add(colorNode(arr, NEW_MIN_COLOR, minIndex));
            for(int j = i+1; j < arr.length; j++) {
                transitions.add(colorNode(arr, selectColor, j));
                if(arr[j].getValue() < arr[minIndex].getValue()) {
                    if(minIndex == i) {
                        transitions.add(colorNode(arr, minIndex, j, MIN_COLOR, NEW_MIN_COLOR));
                    }
                    else {
                        transitions.add(colorNode(arr, minIndex, j, Color.MAROON, NEW_MIN_COLOR));
                    }
                    minIndex = j;
                }
                else {
                    transitions.add(colorNode(arr, startColor, j));
                }
            }

            if(minIndex != i) {
                transitions.add(swap(arr, DX, i, minIndex));
            }

            transitions.add(colorNode(arr, startColor, i, minIndex));
        }
    }

    @Override
    public ArrayList<Transition> startSort(SortNode[] arr, float DX) {
        this.DX = DX;

        selectionSort(arr);

        transitions.add(colorNode(Arrays.asList(arr), sortedColor));

        return transitions;
    }
}
