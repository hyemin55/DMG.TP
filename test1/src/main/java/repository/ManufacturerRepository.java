package repository;

import domain.Manufacturer;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerRepository extends JDBCTemplate {

    public void selectManufacturer() {
        execute("select * from manufacturer");
    }

    @Override
    protected void handleResultSet(ResultSet rs) throws SQLException {
        List<Manufacturer> list = new ArrayList<>();
        while (rs.next()) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setM_id(rs.getInt("m_id"));
            manufacturer.setM_businessID(rs.getString("m_businessID"));
            manufacturer.setM_phone(rs.getString("m_phone"));
            manufacturer.setM_department(rs.getString("m_department"));
            manufacturer.setM_jobTitle(rs.getString("m_jobTitle"));
            manufacturer.setM_adress(rs.getString("m_adress"));
            manufacturer.setM_name(rs.getString("m_name"));
            manufacturer.setM_person(rs.getString("m_person"));
            list.add(manufacturer);
        }
        list.forEach(System.out::println);
    }

    @Override
    protected void setParameter(PreparedStatement pstmt) throws SQLException {

    }
}
