package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JDBCTemplate {
    private static HikariDataSource dataSource = HikariCP.createDataSource();

    public void execute(String sql){
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(sql);

            setParameter(pstmt);


            if (pstmt.execute()) {
                ResultSet rs = pstmt.getResultSet();
                handleResultSet(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    protected abstract void handleResultSet(ResultSet rs) throws SQLException;
    protected abstract void setParameter(PreparedStatement pstmt);
}
