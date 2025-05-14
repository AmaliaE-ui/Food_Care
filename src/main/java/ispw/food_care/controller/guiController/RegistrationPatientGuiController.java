package ispw.food_care.controller.guiController;

import ispw.food_care.bean.PatientBean;
import ispw.food_care.dao.PatientDAO;
import ispw.food_care.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegistrationPatientGuiController {

    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField confPasswordTextField;
    @FXML private DatePicker birthDatePicker;
    @FXML private ChoiceBox<String> genderChoiceBox;
    @FXML private Label errorLabel;


    @FXML
    public void handleSaveButton() {
        if (!validateFields()) return;

        try {
            PatientBean bean = new PatientBean();
            bean.setName(nameTextField.getText());
            bean.setSurname(surnameTextField.getText());
            bean.setPhoneNumber(phoneTextField.getText());
            bean.setUsername(usernameTextField.getText());
            bean.setEmail(emailTextField.getText());
            bean.setPassword(passwordTextField.getText());
            bean.setRole("PATIENT");
            bean.setBirthDate(birthDatePicker.getValue().toString());
            bean.setGender(genderChoiceBox.getValue());

            new UserDAO().saveUser(bean, "PATIENT");
            new PatientDAO().savePatient(bean);

            errorLabel.setText("Registrazione avvenuta con successo!");
        } catch (SQLException e) {
            errorLabel.setText("Errore: " + e.getMessage());
        }catch (DateTimeParseException e) {
            errorLabel.setText("Formato data non valido. Usa GG/MM/AAAA.");
        }
    }

    private boolean validateFields() {
        if (nameTextField.getText().isEmpty() ||
                surnameTextField.getText().isEmpty() ||
                phoneTextField.getText().isEmpty() ||
                usernameTextField.getText().isEmpty() ||
                emailTextField.getText().isEmpty() ||
                passwordTextField.getText().isEmpty() ||
                confPasswordTextField.getText().isEmpty() ||
                birthDatePicker.getValue() == null ||
                genderChoiceBox.getValue() == null) {
            errorLabel.setText("Compila tutti i campi.");
            return false;
        }
        if (!passwordTextField.getText().equals(confPasswordTextField.getText())) {
            errorLabel.setText("Le password non corrispondono.");
            return false;
        }

        return true;
    }

    private String parseDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input, inputFormatter);
        return date.toString(); // restituisce nel formato yyyy-MM-dd
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        ispw.food_care.utils.NavigationManager.switchScene(event, "/ispw/food_care/Login/login.fxml", "FoodCare - Login");
    }


}
