package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseProductRepository3 extends JDBCTemplate{

    private final String w_name;
    public PurchaseProductRepository3(String w_name){
        this.w_name = w_name;
    }


    @Override
    protected void handleResultSet(ResultSet rs) throws SQLException {

    }

    @Override
    protected void setParameter(PreparedStatement pstmt) throws SQLException {

    }
}
