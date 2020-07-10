package sample.sorts;

import javafx.animation.Transition;
import javafx.scene.paint.Color;
import sample.nodes.SortNode;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort extends AbstractSort {
    private static final Color ROOT_COLOR = Color.GOLD;
    private ArrayList<Transition> transitions;
    private float DX;

    public HeapSort() {
        transitions = new ArrayList<>();
    }

    private void heapify(SortNode[] arr, int i, int size) {
        int right = 2*i + 1;
        int left = 2*i + 2;
        int max = i;

        if(right < size && arr[right].getValue() > arr[max].getValue()) {
            max = right;
        }
        if(left < size && arr[left].getValue() > arr[max].getValue()) {
            max = left;
        }

        if(max != i) {
            transitions.add(swap(arr, DX, i, max));
            heapify(arr, max, size);
        }


    }

    private void heapSort(SortNode[] arr) {
        transitions.add(colorNode(Arrays.asList(arr), selectColor));

        //build the initial max heap
        for(int i = arr.length/2-1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        transitions.add(colorNode(Arrays.asList(arr), startColor));

        for(int i = arr.length-1; i > 0; i--) {
            transitions.add(colorNode(arr, ROOT_COLOR, 0));
            transitions.add(swap(arr, DX, 0, i));
            transitions.add(colorNode(arr, startColor, i));
            transitions.add(colorNode(Arrays.asList(arr).subList(0, i), selectColor));
            heapify(arr, 0, i);
            transitions.add(colorNode(Arrays.asList(arr).subList(0, i), startColor));
        }
    }

    @Override
    public ArrayList<Transition> startSort(SortNode[] arr, float DX) {

        this.DX = DX;
        heapSort(arr);

        transitions.add(colorNode(Arrays.asList(arr), Color.ROYALBLUE));
        return transitions;
    }
}
