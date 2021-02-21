package datastructures.graphs;

import java.util.LinkedList;

public class Graph {
    int numVertices;
    LinkedList<Integer>[] adjListArray;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numVertices; i++) {
            sb.append("Adjacency list of vertex ").append(i).append(" : ");
            sb.append(this.adjListArray[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
