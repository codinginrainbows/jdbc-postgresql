import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("rodando");

        String DB_url = "jdbc:postgresql://localhost:5432/hospital";
        String DB_user = "postgres";
        String DB_password = "postgres";

        try {
            Connection conexao = DriverManager.getConnection(DB_url, DB_user, DB_password);

            if(conexao != null) {
                Statement meuStatement = conexao.createStatement();

                buscarNoBanco(meuStatement, "SELECT * FROM funcionario");
                
                salvarNoBanco(meuStatement, "INSERT INTO funcionario (nome, email, senha, codigo_cargo) VALUES ('Inserido pelo JAVA','java@java.com', 'java123', 1)");
                
                conexao.close();
            } else {

            }

        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    static void buscarNoBanco(Statement meuStatement, String queryString) {
        String querySQL = queryString;
    
        try {
            ResultSet resultado = meuStatement.executeQuery(querySQL);

            while(resultado.next()) {
                System.out.println("id: " + resultado.getInt("id") + " nome: " + resultado.getString("nome"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    static void salvarNoBanco(Statement meuStatement, String queryString) {
        String querySQL = queryString;

        try {
            meuStatement.executeUpdate(querySQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
