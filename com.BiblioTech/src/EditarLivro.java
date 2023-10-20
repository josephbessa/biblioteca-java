import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EditarLivro {
    public void editarLivro(Connection connection, String nomeLivro) {
        try {
            // Verificar se o livro existe
            String sql = "SELECT * FROM livros WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeLivro);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // O livro foi encontrado
                System.out.println("Seu livro foi encontrado :). Escolha o que deseja editar:");
                System.out.println("1. Nome do livro");
                System.out.println("2. Autor do livro");
                System.out.println("3. Gênero do livro");
                System.out.print("Digite o número da opção desejada: ");

                Scanner scanner = new Scanner(System.in);
                int opcao = scanner.nextInt();

                String campoEditado = "";
                String novoValor = "";

                switch (opcao) {
                    case 1:
                        campoEditado = "nome";
                        scanner.nextLine(); // Consumir a quebra de linha pendente
                        System.out.print("Novo nome do livro: ");
                        novoValor = scanner.nextLine();
                        break;
                    case 2:
                        campoEditado = "autor";
                        scanner.nextLine(); 
                        System.out.print("Novo autor do livro: ");
                        novoValor = scanner.nextLine();
                        break;
                    case 3:
                        campoEditado = "genero";
                        scanner.nextLine(); 
                        System.out.print("Novo gênero do livro: ");
                        novoValor = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        return;
                }

                // Atualizar as informações no banco
                sql = "UPDATE livros SET " + campoEditado + " = ? WHERE nome = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, novoValor);
                preparedStatement.setString(2, nomeLivro);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Livro atualizado com sucesso!");
                } else {
                    System.out.println("Falha ao atualizar o livro.");
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

            System.out.print("Digite o nome do livro que deseja editar: ");
            String nomeLivro = scanner.nextLine();

            EditarLivro editarLivro = new EditarLivro();
            editarLivro.editarLivro(connection, nomeLivro);

            scanner.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}
