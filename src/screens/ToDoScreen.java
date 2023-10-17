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

        buttonBox = new HBox(10, addButton, removeButton); 

        root = new VBox(10, toDoListView, taskInput, buttonBox); 
        root.setPadding(new Insets(10, 10, 10, 10)); 
        VBox.setMargin(buttonBox, new Insets(20, 0, 30, 0)); 
    }

    public Pane getUI() {
        return root;
    }
}