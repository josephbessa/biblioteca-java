# biblioteca-java
Este sistema vai te permitir adcionar, editar, deletar e visualizar os livros.

Para usar o sistema vocÊ deve ter instalado e configurado o mysqlserver e substituir os dados da URL, user e password por suas credenciais.
Deve ter o arquivo mysql connnector java na pasta lib no lugar o mysql connector que está nela caso você seja usuário de windows.

Para executar o programa de forma completa e fluída use o seguinte comando no seu terminal:
  java -cp 'Coloque aqui sem as "'" o caminho do seu arquivo do mysql-connnector na pasta lib':.  MenuPrincipal
  exemplo: java -cp /home/usuario/pastateste/Projeto_Biblioteca/com.BiblioTech/lib/mysql-connector-j_8.1.0-1ubuntu22.04_all/usr/share/java/mysql-connector-java-8.1.0.jar:. MenuPrincipal

  Query SQL para criar o banco de dados
  CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_implementacao DATE NOT NULL,
    genero VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    status ENUM('Livre', 'Ocupado') NOT NULL
);

  Com tudo isso feito seu programa irá rodar perfeitamente no terminal :)
