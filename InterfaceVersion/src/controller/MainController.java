package controller;
import animatefx.animation.FadeIn;
import gtranslate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.DatabaseConnection;
import javazoom.jl.decoder.JavaLayerException;
import main.IsConnected;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController implements Initializable {
    private Connection connection = DatabaseConnection.getConnection();

    private ObservableList<String> items = FXCollections.observableArrayList();
    private FilteredList<String> filteredData = new FilteredList<>(items, e -> true);
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    @FXML
    private ListView<String> listview1;
    @FXML
    HBox homepage;
    @FXML
    BorderPane pane1;
    @FXML
    Button dlt;
    @FXML
    Button edit;
    @FXML
    Button add;
    @FXML
    TextField search;
    @FXML
    AnchorPane apane;
    private String description;
    @FXML
    private WebView webview1;
    private WebEngine webEngine;
    InputStream sound = null;

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    void updateWord(String word, String description, String old) {
        String query = "update av set word=?,description=? where word='" + old + "' ";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, description);
            preparedStatement.execute();
            refreshTableData();
            listview1.setItems(filteredData);
            preparedStatement.close();
            rs.close();


        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    void addWord(String word, String description) {
        String query = "INSERT INTO av (word, description) VALUES(?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, word);
            preparedStatement.setString(2, description);
            preparedStatement.execute();
            preparedStatement.close();
            refreshTableData();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @FXML
    public void searchWord() {
        // loadDatabaseData();
        listview1.setItems(filteredData);
        search.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(word -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (word.toLowerCase().startsWith(lowerCaseFilter)) { // contains
                    return true;
                }

                return false;
            });
            listview1.setItems(filteredData);
        });
    }

    @FXML
    public void speak () throws IOException, InterruptedException {
        if (IsConnected.IsConnecting() && getSeclecting()!=null ) {
            try {
                InputStream sound = null;
                Audio audio = Audio.getInstance();
                sound = audio.getAudio(getSeclecting(), Language.ENGLISH);
                audio.play(sound);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            Media pick = new Media(this.getClass().getResource("/music/error.mp3").toString());
            MediaPlayer player = new MediaPlayer(pick);
            player.play();
        }

    }
    @FXML
    public void dltWord() {

        try {

            String s = listview1.getSelectionModel().getSelectedItem();
            System.out.println(getSeclecting());
            if (s != null) {
                String query = "delete from av  where word=? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, s);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                rs.close();
                refreshTableData();
                webEngine.loadContent("");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private String getSeclecting() {

        return listview1.getSelectionModel().getSelectedItem();
    }

    private String getDescription() {
        String query = "Select * from av WHERE word=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, listview1.getSelectionModel().getSelectedItem());
            rs = preparedStatement.executeQuery();
            description = rs.getString("description");
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }
    @FXML
    public void loadaddDialog() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/addDialog.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
    }

    @FXML
    public void loadupdateDialog() {
        if (listview1.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/updateDialog.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            updateController ud = loader.getController();

            ud.initData(getSeclecting(), getDescription());

            Parent p = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("No word selected!");
            alert.showAndWait();
        }


    }

    private void loadDatabaseData() {

        String query = "select word   from  av";
        try {
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                items.add(rs.getString(1));
                listview1.setItems(items);
            }
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }


    }

    private void refreshTableData() {
        ObservableList<String> itemsCopy = FXCollections.observableArrayList();

        try {
            String query = "select word   from  av";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                itemsCopy.add(rs.getString(1));

            }
            items.clear();
            items.addAll(itemsCopy);
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        listview1.setItems(itemsCopy);
    }

    @FXML
    private void getSeletedItems() {
        listview1.setOnMouseClicked(e -> {

            try {
                String query = "Select * from av WHERE word=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, listview1.getSelectionModel().getSelectedItem());
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    webEngine.loadContent(rs.getString("html"));
                }
                preparedStatement.close();
                rs.close();
            } catch (SQLException ee) {
                System.out.println(ee.getMessage());

            }
        });


    }

    @FXML
    public void loadMain() {

        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/mainCenter.fxml"));
            loadDatabaseData();
            new FadeIn(root1).play();


            pane1.setCenter(root1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void loadGoogleTranslate() {
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/GoogleTranslate.fxml"));
            pane1.setCenter(root1);
            new FadeIn(root1).play();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        webEngine = webview1.getEngine();
        loadDatabaseData();
        getSeletedItems();
    }
}
