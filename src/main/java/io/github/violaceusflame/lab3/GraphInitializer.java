package io.github.violaceusflame.lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphInitializer {
    private static final String REGEX = "( )+";
    private static final String LINKED = "1";

    private final FileInputParser fileInputParser;
    private final Graph<String> graph;
    private List<String> inputStrings = new ArrayList<>();
    private List<String> vertices = new ArrayList<>();
    private final List<List<String>> adjacencyMatrix = new ArrayList<>();

    public GraphInitializer(FileInputParser fileInputParser, Graph<String> graph) {
        this.fileInputParser = fileInputParser;
        this.graph = graph;
    }

    public void initialize() {
        parseVertices();
        parseAdjacencyMatrix();
        validateInput();
        addVertices();
        linkVertices();
    }

    private void validateInput() {
        if (vertices.size() != adjacencyMatrix.size()) {
            throw new IllegalArgumentException("The number of vertices does not match the number of adjacency matrix");
        }
        // TODO дописать другие критерии для валидации ввода
    }

    private void linkVertices() {
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            for (int j = 0; j < adjacencyMatrix.get(i).size(); j++) {
                if (adjacencyMatrix.get(i).get(j).equals(LINKED)) {
                    graph.addLinkedVertex(vertices.get(i), vertices.get(j));
                }
            }
        }
    }

    private void parseAdjacencyMatrix() {
        for (int i = 1; i < inputStrings.size(); i++) {
            String adjacencyMatrixRow = inputStrings.get(i).trim();
            String[] values = adjacencyMatrixRow.split(REGEX);
            ArrayList<String> matrixRowList = new ArrayList<>();
            adjacencyMatrix.add(matrixRowList);
            matrixRowList.addAll(Arrays.asList(values).subList(1, values.length));
        }
    }

    private void addVertices() {
        for (String vertex : vertices) {
            graph.addVertex(vertex);
        }
    }

    private void parseVertices() {
        inputStrings = fileInputParser.getResult();
        String inputFirstString = inputStrings.get(0).trim();
        vertices = Arrays.stream(inputFirstString.split(REGEX)).toList();
    }
}
