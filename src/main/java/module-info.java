module com.example.java_hra1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.java_hra1 to javafx.fxml;
    exports com.example.java_hra1;
}