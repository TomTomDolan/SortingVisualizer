package sample.sorts;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.SortingController;
import sample.nodes.SortNode;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSort {
    Color startColor = Color.MAROON;
    Color selectColor = Color.CYAN;
    Color sortedColor = Color.ROYALBLUE;

    public ParallelTransition colorNode(List<SortNode> snList, Color color) {
        ParallelTransition pt = new ParallelTransition();

        for(SortNode sn : snList) {
            FillTransition ft = new FillTransition();
            ft.setShape(sn);
            ft.setToValue(color);
            ft.setDuration(Duration.millis(100));
            pt.getChildren().add(ft);
        }
        return pt;
    }

    public ParallelTransition colorNode(SortNode[] arr, Color color, int...a) {
        ParallelTransition pt = new ParallelTransition();

        for(int i=0; i < a.length; i++) {
            FillTransition ft = new FillTransition();
            ft.setShape(arr[a[i]]);
            ft.setToValue(color);
            ft.setDuration(Duration.millis(100));
            pt.getChildren().add(ft);
        }
        return pt;
    }

    public ParallelTransition swap(SortNode[] arr, float DX, int i, int j) {
        ParallelTransition pt = new ParallelTransition();

        int moveDist = j - i;

        pt.getChildren().addAll(arr[i].moveX(DX*moveDist), arr[j].moveX(-DX*moveDist));

        SortNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return pt;
    }

    public abstract ArrayList<Transition> startSort(SortNode[] arr, float DX);
}
