package sample.sorts;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import sample.nodes.SortNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSort extends AbstractSort {

    private ArrayList<Transition> transitions;
    private float DX;


    public InsertionSort() {
        this.transitions = new ArrayList<>();
    }

    @Override
    public ArrayList<Transition> startSort(SortNode[] arr, float DX) {
        this.DX = DX;

        for(int i = 1; i < arr.length; i++) {
            ParallelTransition pt = new ParallelTransition();

            SortNode key = arr[i];
            int j = i - 1;

            transitions.add(colorNode(arr, selectColor, i));

            while(j >= 0 && arr[j].getValue() > key.getValue()) {
                pt.getChildren().add(arr[j].moveX(DX));
                arr[j+1] = arr[j];
                j--;
            }

            arr[j+1] = key;
            pt.getChildren().add(key.moveX((i - j - 1)*(-DX)));
            transitions.add(pt);
            transitions.add(colorNode(arr, startColor, j+1));
        }

        transitions.add(colorNode(Arrays.asList(arr), sortedColor));

        return transitions;
    }
}
