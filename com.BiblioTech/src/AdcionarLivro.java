import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdcionarLivro {
    public boolean cadastrarLivro(Connection connection, String nome, String genero, String autor) {
        // Define a data de implementação como a data atual
        Date dataAtual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataImplementacao = dateFormat.format(dataAtual);

        // Status iniciado = "Livre"
        String status = "Livre";

        // SQL para inserir o Livro na tabela
        String sql = "INSERT INTO livros (nome, data_implementacao, genero, autor, status) VALUES (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, dataImplementacao);
            preparedStatement.setString(3, genero);
            preparedStatement.setString(4, autor);
            preparedStatement.setString(5, status);

            // Executa o Insert
            int rowsAffected = preparedStatement.executeUpdate();

            // Verifica se o insert deu certo
            if (rowsAffected > 0) {
                System.out.println("Livro registrado com sucesso!");
                return true;
            } else {
                System.out.println("Falha ao registrar livro.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            // Puxando a conexão a conexão
            Connection connection = ConexaoBanco.getConnection();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o nome do livro: ");
            String nomeLivroNovo = scanner.nextLine();

            System.out.println("Digite o gênero do livro: ");
            String generoLivro = scanner.nextLine();

            System.out.println("Digite o autor do livro: ");
            String autorLivro = scanner.nextLine();

            AdcionarLivro adcionarLivro = new AdcionarLivro();

            boolean registradoComSucesso = adcionarLivro.cadastrarLivro(connection, nomeLivroNovo, generoLivro, autorLivro);

            if (registradoComSucesso) {
                // O livro foi registrado com sucesso
                System.out.println("Livro registrado com sucesso!");
            } else {
                // Lida com a falha
                System.out.println("Falha ao registrar livro.");
            }

            scanner.close();
            connection.close(); // Fechar conexão quando ela não for mais necessária
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}
