module org.example.my_expert_inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jakarta.persistence;

    requires kotlin.stdlib;
    requires org.hibernate.orm.core;

    exports org.example.my_expert_inventory;

    opens org.example.my_expert_inventory.model;
    exports org.example.my_expert_inventory.model;
    opens org.example.my_expert_inventory;
}