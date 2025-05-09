package tr1fker.laboratorywork7;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchAppController {

    @FXML
    private TextField searchField;
    @FXML
    private CheckBox checkboxList1;
    @FXML
    private CheckBox checkboxList2;
    @FXML
    private ChoiceBox<String> list1;
    @FXML
    private ChoiceBox<String> list2;
    @FXML
    private Text searchResultsText;

    private List<String> itemsList1 = new ArrayList<>();
    private List<String> itemsList2 = new ArrayList<>();

    @FXML
    public void initialize() {
        // Инициализация списков с примерами
        list1.getItems().addAll("Пример 1", "Пример 2", "Пример 3");
        list2.getItems().addAll("Пример A", "Пример B", "Пример C");

        // Добавляем элементы в соответствующие списки
        itemsList1.addAll(list1.getItems());
        itemsList2.addAll(list2.getItems());
    }

    @FXML
    public void searchInLists() {
        String searchText = searchField.getText().toLowerCase();
        StringBuilder result = new StringBuilder();

        // Поиск в Списке 1, если выбран чекбокс
        if (checkboxList1.isSelected()) {
            List<String> resultList1 = itemsList1.stream()
                    .filter(item -> item.toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
            result.append("Список 1: ").append(resultList1.isEmpty() ? "Ничего не найдено" : String.join(", ", resultList1)).append("\n");
        }

        // Поиск в Списке 2, если выбран чекбокс
        if (checkboxList2.isSelected()) {
            List<String> resultList2 = itemsList2.stream()
                    .filter(item -> item.toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
            result.append("Список 2: ").append(resultList2.isEmpty() ? "Ничего не найдено" : String.join(", ", resultList2)).append("\n");
        }

        // Обновляем текст на интерфейсе
        searchResultsText.setText(result.toString());
    }

    @FXML
    public void addToList1() {
        String textToAdd = searchField.getText();
        if (!textToAdd.isEmpty() && !itemsList1.contains(textToAdd)) {
            itemsList1.add(textToAdd);
            list1.getItems().add(textToAdd);
            searchField.clear();
        }
    }

    @FXML
    public void addToList2() {
        String textToAdd = searchField.getText();
        if (!textToAdd.isEmpty() && !itemsList2.contains(textToAdd)) {
            itemsList2.add(textToAdd);
            list2.getItems().add(textToAdd);
            searchField.clear();
        }
    }

    @FXML
    public void deleteFromList1() {
        String selectedItem = list1.getValue();
        if (selectedItem != null) {
            itemsList1.remove(selectedItem);
            list1.getItems().remove(selectedItem);
        } else {
            showAlert("Ошибка", "Выберите элемент для удаления");
        }
    }

    @FXML
    public void deleteFromList2() {
        String selectedItem = list2.getValue();
        if (selectedItem != null) {
            itemsList2.remove(selectedItem);
            list2.getItems().remove(selectedItem);
        } else {
            showAlert("Ошибка", "Выберите элемент для удаления");
        }
    }

    @FXML
    public void editInList1() {
        String selectedItem = list1.getValue();
        if (selectedItem != null) {
            String newText = searchField.getText();
            if (!newText.isEmpty()) {
                int index = itemsList1.indexOf(selectedItem);
                itemsList1.set(index, newText);
                list1.getItems().set(index, newText);
                searchField.clear();
            } else {
                showAlert("Ошибка", "Введите новый текст для редактирования");
            }
        } else {
            showAlert("Ошибка", "Выберите элемент для редактирования");
        }
    }

    @FXML
    public void editInList2() {
        String selectedItem = list2.getValue();
        if (selectedItem != null) {
            String newText = searchField.getText();
            if (!newText.isEmpty()) {
                int index = itemsList2.indexOf(selectedItem);
                itemsList2.set(index, newText);
                list2.getItems().set(index, newText);
                searchField.clear();
            } else {
                showAlert("Ошибка", "Введите новый текст для редактирования");
            }
        } else {
            showAlert("Ошибка", "Выберите элемент для редактирования");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
