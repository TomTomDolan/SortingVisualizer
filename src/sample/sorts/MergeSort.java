package sample.sorts;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import sample.nodes.SortNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MergeSort extends AbstractSort {
    private ArrayList<Transition> transitions;

    private SortNode[] temp;

    private float DX;

    public MergeSort() {
        this.transitions = new ArrayList<>();
    }

    private ArrayList<Transition> mergeSort(SortNode[] arr, int l, int r) {
        ArrayList<Transition> transitions = new ArrayList<>();

        if(l < r) {
            int m = (l + r)/2;
            transitions.addAll(mergeSort(arr, l, m));
            transitions.addAll(mergeSort(arr, m+1, r));
            transitions.addAll(merge(arr, l, m, r));
        }
        return transitions;
    }

    private ArrayList<Transition> merge(SortNode[] arr, int l, int m, int r) {
        ArrayList<Transition> transitions = new ArrayList<>();
        ArrayList<SortNode> tempList = new ArrayList<>();

        for(int i = l; i <= r; i++) {
            temp[i] = arr[i];
            tempList.add(temp[i]);
        }

        int lPtr = l;
        int rPtr = m + 1;
        int k = l;

        while(lPtr <= m && rPtr <= r) {
            if(temp[lPtr].getValue() <= temp[rPtr].getValue()) {
                arr[k++] = temp[lPtr++];
            }
            else {
                arr[k++] = temp[rPtr++];
            }
        }

        while(lPtr <= m) {
            arr[k++] = temp[lPtr++];
        }

        while(rPtr <= r) {
            arr[k++] = temp[rPtr++];
        }

        transitions.add(colorNode(tempList, selectColor));

        ParallelTransition pt = new ParallelTransition();

        for(int x = l; x <= r; x++) {
            for(int y = l; y <= r; y++) {
                if(temp[x].equals(arr[y])) {
                    pt.getChildren().add(temp[x].moveX(DX*(y-x)));
                }
            }
        }

        transitions.add(pt);
        transitions.add(colorNode(tempList, selectColor));

        return transitions;
    }

    @Override
    public ArrayList<Transition> startSort(SortNode[] arr, float DX) {
        this.DX = DX;
        this.temp = new SortNode[arr.length];
        transitions.addAll(mergeSort(arr, 0, arr.length-1));
        transitions.add(colorNode(Arrays.asList(arr), sortedColor));
        return transitions;
    }
}
