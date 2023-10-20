import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) throws SQLException { 
        Connection connection = ConexaoBanco.getConnection();
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Menu Principal:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Deletar Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Visualizar Livros");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    AdcionarLivro adcionarLivro = new AdcionarLivro();
                    System.out.print("Digite o nome do livro: ");
                    String nomeLivroNovo = scanner.nextLine(); 
                    System.out.print("Digite o gênero do livro: ");
                    String generoLivro = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autorLivro = scanner.nextLine();
                    adcionarLivro.cadastrarLivro(connection, nomeLivroNovo, generoLivro, autorLivro);
                    break;
                case 2:
                    DeletarLivro deletarLivro = new DeletarLivro();
                    System.out.print("Digite o nome do livro que deseja excluir: ");
                    String nomeLivroExcluir = scanner.nextLine(); 
                    deletarLivro.deletarLivro(connection, nomeLivroExcluir);
                    break;
                case 3:
                    EditarLivro editarLivro = new EditarLivro();
                    System.out.print("Digite o nome do livro que deseja editar: ");
                    String nomeLivroEditar = scanner.nextLine();
                    editarLivro.editarLivro(connection, nomeLivroEditar);
                    break;
                case 4:
                    VisualizarLivro visualizarLivro = new VisualizarLivro();
                    visualizarLivro.visualizarLivros(connection);
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        connection.close();

        scanner.close();
    }
}
