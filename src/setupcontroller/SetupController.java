package setupcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class SetupController {



    public class setupController implements Initializable {

        @FXML
        private TextField tf_username;

        @FXML
        private TextField tf_password;

        @FXML
        private TextField tf_port;

        @FXML
        private TextField tf_ip;

        @FXML
        private Button button_setup;

        @FXML
        private Button button_sign_up;

        @FXML
        private CheckBox checkBox;

        @FXML
        private PasswordField passwordHidden;

        /***
         * richiamo metodi per instaurare connessione all'inizio del gioco
         * @param location
         * @param resourceBundle
         */

        @Override
        public void initialize(URL location, ResourceBundle resourceBundle) {
            /***
             *  Passo informazioni al DB durante il primo avvio del gioco, da collegare al giusto path per il db in persistence/db/DataBase
             */
            button_setup.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DataBase.createDataBase(tf_ip.getText(), tf_port.getText(), tf_username.getText(), tf_password.getText()); //passo tutto da DBUtil per il login
                }
            });


        }

    }
}