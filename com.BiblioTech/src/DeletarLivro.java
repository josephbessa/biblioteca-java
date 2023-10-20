import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletarLivro {
    public void deletarLivro(Connection connection, String nomeLivro) {
        try {
            // Verifica se o livro existe
            String sql = "SELECT * FROM livros WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeLivro);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // O livro foi encontrado
                System.out.println("Livro encontrado. Deseja realmente excluí-lo? (S/N)");
                Scanner scanner = new Scanner(System.in);
                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {
                    // Excluir o livro
                    sql = "DELETE FROM livros WHERE nome = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, nomeLivro);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Livro excluído com sucesso!");
                    } else {
                        System.out.println("Falha ao excluir o livro.");
                    }
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = ConexaoBanco.getConnection();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o nome do livro que deseja excluir: ");
            String nomeLivro = scanner.nextLine();

            DeletarLivro deletarLivro = new DeletarLivro();
            deletarLivro.deletarLivro(connection, nomeLivro);

            scanner.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}
