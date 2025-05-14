package ispw.food_care.controller.guiController;

import ispw.food_care.bean.NutritionistBean;
import ispw.food_care.dao.NutritionistDAO;
import ispw.food_care.dao.UserDAO;
import ispw.food_care.model.NutritionistModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class RegistrationNutritionistGuiController {
    @FXML
    private void handleBackToLogin(ActionEvent event) {
        ispw.food_care.utils.NavigationManager.switchScene(event, "/ispw/food_care/Login/login.fxml", "Login");
    }


    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField phoneField;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField pivaField;
    @FXML private TextField titoloStudioField;
    @FXML private TextField indirizzoStudioField;
    @FXML private TextField specializzazioneField;
    @FXML private Label errorLabel;

    public void handleSaveButton() {
        if (!validateFields()) return;

        try {
            NutritionistBean bean = new NutritionistBean();
            bean.setName(nameField.getText());
            bean.setSurname(surnameField.getText());
            bean.setPhoneNumber(phoneField.getText());
            bean.setUsername(usernameField.getText());
            bean.setEmail(emailField.getText());
            bean.setPassword(passwordField.getText());
            bean.setRole("NUTRITIONIST");
            bean.setPiva(pivaField.getText());
            bean.setTitoloStudio(titoloStudioField.getText());
            bean.setIndirizzoStudio(indirizzoStudioField.getText());
            bean.setSpecializzazione(specializzazioneField.getText());

            new UserDAO().saveUser(bean, "NUTRITIONIST");
            new NutritionistDAO().saveNutritionist(bean);

            errorLabel.setText("Registrazione avvenuta con successo!");
        } catch (SQLException e) {
            errorLabel.setText("Errore: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (nameField.getText().isEmpty() ||
                surnameField.getText().isEmpty() ||
                phoneField.getText().isEmpty() ||
                usernameField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                confirmPasswordField.getText().isEmpty() ||
                pivaField.getText().isEmpty() ||
                titoloStudioField.getText().isEmpty() ||
                indirizzoStudioField.getText().isEmpty() ||
                specializzazioneField.getText().isEmpty()) {
            errorLabel.setText("Compila tutti i campi.");
            return false;
        }

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Le password non corrispondono.");
            return false;
        }

        return true;
    }
}
