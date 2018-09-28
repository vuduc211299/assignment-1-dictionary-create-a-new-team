package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import main.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Connection connection = DatabaseConnection.LoginConnector();

    ObservableList<String> items = FXCollections.observableArrayList();
    FilteredList<String> filteredData = new FilteredList<>(items, e -> true);
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    @FXML
    private ListView<String> listview1;
    @FXML
    private Text des;
    @FXML
    private Text pro;
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

    public void loadDatabaseData() {

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
    public void refreshTableData() {
        ObservableList<String> itemsCopy = FXCollections.observableArrayList();

        try {
            String query = "select word   from  av";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                itemsCopy.add(rs.getString(1));
                listview1.setItems(itemsCopy);
            }

            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }


@FXML
    public void dltWord() {

        try {

            String s = listview1.getSelectionModel().getSelectedItem();
            System.out.println(s);
            if (s != null) {
                String query = "delete from av  where word=? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, s);


                preparedStatement.executeUpdate();

                preparedStatement.close();
                rs.close();
               refreshTableData();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
 /*

    @FXML
    public void loadaddDialog() {
        try {
            FXMLLoader loader=new FXMLLoader();
            Parent root;
            root = loader.load(getClass().getResource("addDialog.fxml"));
            // pane1.setCenter(root1);
            pm.setScene(new Scene(root));
            pm.setTitle("Add Word");
            pm.show();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
    @FXML
    public void loadupdateDialog() {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("updateDialog.fxml"));
            pm.setScene(new Scene(root1));
            pm.setTitle("Edit Word");
            pm.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
 */
    @FXML
    public void searchWord() {

        listview1.setItems(filteredData);
        search.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.toLowerCase().startsWith(lowerCaseFilter)) { // contains
                    return true;
                }

                return false;
            });
            listview1.setItems(filteredData);
        });
    }


    @FXML
    public void getSeletedItems() {
        listview1.setOnMouseClicked(e -> {
           // System.out.println(listview1.getSelectionModel().getSelectedItem());

            try {
                String query = "Select * from av WHERE word=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, (String) listview1.getSelectionModel().getSelectedItem());
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    des.setText(rs.getString("description"));
                    pro.setText(rs.getString("pronounce"));
                }
                preparedStatement.close();
                rs.close();
            } catch (SQLException  ee) {
                System.out.println(ee.getMessage());

            }
        });


    }

    @FXML
    public void loadMain() {

        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/mainCenter.fxml"));
            loadDatabaseData();


            pane1.setCenter(root1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void loadGoogleTranslate() {
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/GoogleTranslate.fxml"));
            pane1.setCenter(root1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
 /*
        try {

            Parent root1 = FXMLLoader.load(getClass().getResource("mainCenter.fxml"));

            pane1.setCenter(root1);
         //   loadDatabaseData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
 */

        loadDatabaseData();
        getSeletedItems();

    }
}
