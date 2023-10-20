import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/nomeBanco?user=usuarioDoBanco";
    private static final String JDBC_USERNAME = "usuarioDoBanco";
    private static final String JDBC_PASSWORD = "senhaDoSeuBanco";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }
}

