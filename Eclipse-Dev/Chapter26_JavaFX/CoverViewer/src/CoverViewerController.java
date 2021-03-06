/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author superlamer
 */
public class CoverViewerController{
    
    @FXML private ListView<Book> booksListView;
    @FXML private ImageView coverImageView;
    
    // store the list of Book objects
    private final ObservableList<Book> books = 
            FXCollections.observableArrayList();
    

    public void initialize() {
        // populate the ObservableList<Book>
        //System.out.println(CoverViewerController.class.getResource("/images/small/androidhtp.jpg"));                
        books.add(new Book("Android How to Program", "/images/small/androidhtp.jpg",
        "/images/large/androidhtp.jpg"));
        books.add(new Book("C How to Program", "/images/small/chtp.jpg",
        "/images/large/chtp.jpg"));
        books.add(new Book("C++ How to Program", "/images/small/cpphtp.jpg",
        "/images/large/cpphtp.jpg"));
        books.add(new Book("Internet and World Wide Web How to Program", "/images/small/iw3htp.jpg",
        "/images/large/iw3htp.jpg"));
        books.add(new Book("Java How to Program", "/images/small/jhtp.jpg",
        "/images/large/jhtp.jpg"));
        books.add(new Book("Visual Basic How to Program", "/images/small/vbhtp.jpg",
        "/images/large/vbhtp.jpg"));
        books.add(new Book("Visual C# How to Program", "/images/small/vcshtp.jpg",
        "/images/large/vcshtp.jpg"));
        booksListView.setItems(books);
                   
        // when listView selection chanages, show large cover in imageview
        booksListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    coverImageView.setImage(new Image(newValue.getLargeImage()));
                });
    }    
    
}
