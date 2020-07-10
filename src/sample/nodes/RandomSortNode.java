package sample.nodes;

import javafx.scene.paint.Color;
import sample.SortingController;

import java.util.Random;

public class RandomSortNode {

    public static SortNode[] getRandomSortNodes(int n) {
        SortNode[] sortNodes = new SortNode[n];
        Random rand = new Random();

        for(int i = 0; i < n; i++) {
            sortNodes[i] = new SortNode(1 + rand.nextInt(n));
            sortNodes[i].setX(i * SortingController.WIDTH / n);
            sortNodes[i].setFill(Color.MAROON);
            setDimensions(sortNodes[i], n);
        }

        return sortNodes;
    }

    private static void setDimensions(SortNode sn, int n) {
        sn.setWidth(SortingController.WIDTH/n - SortingController.X_GAP);
        sn.setHeight((SortingController.HEIGHT - SortingController.TOP_GAP)/n*sn.getValue());
    }
}
