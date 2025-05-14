package ispw.food_care.dao;

import ispw.food_care.bean.NutritionistBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NutritionistDAO {
    public void saveNutritionist(NutritionistBean bean) throws SQLException {
        String insertNutritionistSql = "INSERT INTO nutritionist (username, piva, titolo_studio, indirizzo_studio, specializzazione) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertNutritionistSql)) {

            stmt.setString(1, bean.getUsername());
            stmt.setString(2, bean.getPiva());
            stmt.setString(3, bean.getTitoloStudio());
            stmt.setString(4, bean.getIndirizzoStudio());
            stmt.setString(5, bean.getSpecializzazione());

            stmt.executeUpdate();
        }
    }
}

