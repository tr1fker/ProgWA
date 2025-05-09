package tr1fker.laboratorywork7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class ViewController {
    private final BookDAO dao = new BookDAO();

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        books.addAll(dao.loadBooks());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        bookTable.setItems(books);
    }

    @FXML
    private void handleAddBook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Добавить книгу");
        dialog.setHeaderText("Введите данные книги через запятую");
        dialog.setContentText("Пример: Название, Автор, 2024, Жанр");

        dialog.showAndWait().ifPresent(input -> {
            String[] parts = input.split(",");
            if (parts.length == 4) {
                try {
                    Book book = new Book(
                            parts[0].trim(),
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            parts[3].trim()
                    );
                    books.add(book);
                    dao.saveBook(book);

                } catch (NumberFormatException e) {
                    showError("Год должен быть числом");
                }
            } else {
                showError("Неверный формат. Введите 4 поля через запятую.");
            }
        });
    }

    @FXML
    private void handleDeleteBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            books.remove(selected);
            dao.deleteBook(selected);
        } else {
            showError("Выберите книгу для удаления.");
        }
    }

    @FXML
    private void handleEditBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Выберите книгу для редактирования.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selected.toString());
        dialog.setTitle("Редактировать книгу");
        dialog.setHeaderText("Отредактируйте данные книги через запятую");
        dialog.setContentText("Пример: Название, Автор, 2024, Жанр");

        dialog.showAndWait().ifPresent(input -> {
            String[] parts = input.split(",");
            if (parts.length == 4) {
                try {
                    selected.setTitle(parts[0].trim());
                    selected.setAuthor(parts[1].trim());
                    selected.setYear(Integer.parseInt(parts[2].trim()));
                    selected.setGenre(parts[3].trim());
                    bookTable.refresh();
                } catch (NumberFormatException e) {
                    showError("Год должен быть числом.");
                }
            } else {
                showError("Неверный формат.");
            }
        });
        dao.updateBook(selected);
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
