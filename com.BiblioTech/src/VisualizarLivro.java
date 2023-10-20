import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizarLivro {
    public void visualizarLivros(Connection connection) {
        try {
            // Consulta para obter todos os dados
            String sql = "SELECT nome, genero, autor FROM livros";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Livros existentes no banco de dados:");
            while (resultSet.next()) {
                String nomeLivro = resultSet.getString("nome");
                String generoLivro = resultSet.getString("genero");
                String autorLivro = resultSet.getString("autor");

                System.out.println("Nome: " + nomeLivro);
                System.out.println("Gênero: " + generoLivro);
                System.out.println("Autor: " + autorLivro);
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = ConexaoBanco.getConnection();

            VisualizarLivro visualizarLivro = new VisualizarLivro();
            visualizarLivro.visualizarLivros(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}
