package sample.sorts;

import javafx.animation.Transition;
import sample.SortingController;
import sample.nodes.SortNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSort extends AbstractSort {
    private ArrayList<Transition> transitions;
    private float DX;

    public BubbleSort() {
        this.transitions = new ArrayList<>();
    }

    private ArrayList<Transition> compareSortNode(SortNode[] arr, int i, int j) {
        ArrayList<Transition> transitions = new ArrayList<>();

        transitions.add(colorNode(arr, selectColor, i, j));

        if(arr[i].getValue() > arr[j].getValue()) {
            transitions.add(swap(arr, DX, i, j));
        }
        return transitions;
    }

    private void bubbleSort(SortNode[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length-1 - i; j++) {
                this.transitions.addAll(compareSortNode(arr, j, j+1));
            }
        }
    }

    @Override
    public ArrayList<Transition> startSort(SortNode[] arr, float DX) {
        this.DX = DX;

        bubbleSort(arr);

        this.transitions.add(colorNode(Arrays.asList(arr), sortedColor));

        return this.transitions;
    }
}
