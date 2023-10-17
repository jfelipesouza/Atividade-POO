package screens;


import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;

public class ToDoScreen {
    private ListView<String> toDoListView;
    private TextField taskInput;
    private HBox buttonBox;
    private VBox root;

    public ToDoScreen() {
        toDoListView = new ListView<>();
        taskInput = new TextField();
        taskInput.setPromptText("Adicione uma tarefa e pressione Enter");

        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> {
            String task = taskInput.getText();
            if (!task.isEmpty()) {
                toDoListView.getItems().add(task);
                taskInput.clear();
            }
        });

        Button removeButton = new Button("Remover");
        removeButton.setOnAction(e -> {
            int selectedIndex = toDoListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                toDoListView.getItems().remove(selectedIndex);
            }
        });

        buttonBox = new HBox(10, addButton, removeButton); // Espaçamento de 10 pixels entre os botões

        root = new VBox(10, toDoListView, taskInput, buttonBox); // Espaçamento de 10 pixels entre todos os itens
        root.setPadding(new Insets(10, 10, 10, 10)); // Padding de 10 pixels para todos os lados
        VBox.setMargin(buttonBox, new Insets(20, 0, 30, 0)); // Margin de 20 pixels no topo e 30 pixels na parte inferior dos botões
    }

    public Pane getUI() {
        return root;
    }
}