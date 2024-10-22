package io.github.violaceusflame.lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        GraphInitializer graphInitializer = new GraphInitializer(
                new FileInputParser("src/main/java/io/github/violaceusflame/lab3/graph_adjacency_list1.txt"),
                graph);
        graphInitializer.initialize();
        dialog(graph);
    }

    private static void dialog(Graph<String> graph) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Введите букву вершины: ");
                    String vertexData = scanner.nextLine();
                    if (!graph.addVertex(vertexData)) {
                        System.out.println("Такая вершина уже есть в графе!");
                        break;
                    }
                    graph.addVertex(vertexData);
                    System.out.println("Начните вводить буквы связанных вершин");
                    System.out.println("Для завершения ввода введите 'exit'");
                    List<String> vertices = new ArrayList<>();
                    String userInput;
                    while (!(userInput = scanner.nextLine()).equals("exit")) {
                        if (!graph.containsVertex(userInput)) {
                            System.out.println("Такой вершины нет в графе");
                            continue;
                        }
                        vertices.add(userInput);
                    }
                    for (String vertex : vertices) {
                        graph.addLinkedVertex(vertexData, vertex);
                    }
                    break;
                case "2":
                    System.out.println("Введите вершину, которую нужно удалить");
                    String removeVertex = scanner.nextLine();
                    if (!graph.removeVertex(removeVertex)) {
                        System.out.println("Такой вершины нет в графе");
                        break;
                    }
                    break;
                case "3":
                    System.out.println("Введите вершины, для которых нужно найти ребро");
                    String targetVertex = scanner.nextLine();
                    if (!graph.containsVertex(targetVertex)) {
                        System.out.println("Такой вершины нет в графе");
                        break;
                    }
                    String linkedVertex = scanner.nextLine();
                    if (!graph.containsVertex(linkedVertex)) {
                        System.out.println("Такой вершины нет в графе");
                        break;
                    }
                    boolean linked = graph.isLinked(targetVertex, linkedVertex);
                    System.out.printf("Вершины %s и %s связаны: %s%n", targetVertex, linkedVertex, linked);
                    break;
                case "4":
                    String a = scanner.nextLine();
                    if (!graph.containsVertex(a)) {
                        System.out.println("Такой вершины нет в графе");
                        break;
                    }
                    String b = scanner.nextLine();
                    if (!graph.containsVertex(a)) {
                        System.out.println("Такой вершины нет в графе");
                        break;
                    }
                    if (graph.isLinked(a, b)) {
                        System.out.println("Связь между заданными вершинами уже существует");
                        break;
                    }
                    graph.addLinkedVertex(a, b);
                    break;
                case "5":
                    isRunning = false;
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("[МЕНЮ]");
        System.out.println("1. Добавить вершину со ссылками на другие вершины");
        System.out.println("2. Исключить вершину из графа");
        System.out.println("3. Найти ребро, связывающего заданные вершины");
        System.out.println("4. Связать вершины");
        System.out.println("5. Выйти");
        System.out.print(">>> ");
    }
}
