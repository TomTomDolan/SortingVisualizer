package sample.sorts;

import javafx.animation.Transition;
import javafx.scene.paint.Color;
import sample.nodes.SortNode;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort extends AbstractSort {
    private static final Color PIVOT_COLOR = Color.DARKMAGENTA;
    private ArrayList<Transition> transitions;
    private float DX;

    public QuickSort() {
        this.transitions = new ArrayList<>();
    }

    private void quickSort(SortNode[] arr, int lo, int hi) {
        if(lo < hi) {
            int r = partition(arr, lo, hi);
            quickSort(arr, lo, r-1);
            quickSort(arr, r+1, hi);
        }
    }

    private int partition(SortNode[] arr, int lo, int hi) {
        int i = lo;
        transitions.add(colorNode(arr, PIVOT_COLOR, hi));

        for(int j = lo; j < hi; j++) {
            transitions.add(colorNode(arr, selectColor, j));
            if(arr[j].getValue() < arr[hi].getValue()) {
                transitions.add(swap(arr, DX, i, j));
                transitions.add(colorNode(arr, startColor, i));
                i++;
            }
            transitions.add(colorNode(arr, startColor, j));
        }
        transitions.add(swap(arr, DX, i, hi));
        transitions.add(colorNode(arr, startColor, i));
        return i;
    }

    @Override
    public ArrayList<Transition> startSort(SortNode[] arr, float DX) {
        this.DX = DX;
        quickSort(arr,0, arr.length-1);
        transitions.add(colorNode(Arrays.asList(arr), sortedColor));
        return transitions;
    }
}
