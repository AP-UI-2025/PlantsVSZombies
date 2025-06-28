module org.example.plantvszombies {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.plantvszombies to javafx.fxml;
    exports org.example.plantvszombies;
}