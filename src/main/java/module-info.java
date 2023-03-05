module com.vervyle.lw5_oop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vervyle.lw5_oop to javafx.fxml;
    exports com.vervyle.lw5_oop;
    exports com.vervyle.lw5_oop.model;
    opens com.vervyle.lw5_oop.model to javafx.fxml;
}