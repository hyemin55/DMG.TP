package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Manufacturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addmanufacturer {
    private static HikariDataSource dataSource = HikariCP.createDataSource();
    String sql = "insert into manufacturer(m_businessID,m_phone,m_department,m_jobTitle,m_adress,m_name,m_person)\n" +
            "value (?,?,?,?,?,?,?)";

    public addmanufacturer(String mBusinessID, String mName, String mAdress, String mPerson,
                           String mPhone, String mDepartment, String mJobTitle) {
        Manufacturer manufacturer = new Manufacturer();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1,mBusinessID);
            pstmt.setString(2,mPhone);
            pstmt.setString(3,mDepartment);
            pstmt.setString(4,mJobTitle);
            pstmt.setString(5,mAdress);
            pstmt.setString(6,mName);
            pstmt.setString(7,mPerson);
            pstmt.executeUpdate();
            System.out.println("업체등록이 완료되었습니다.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
