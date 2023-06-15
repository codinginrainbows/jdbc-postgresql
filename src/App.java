import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("rodando");

        String DB_url = "jdbc:postgresql://localhost:5432/postgres";
        String DB_user = "postgres";
        String DB_password = "postgres";

        try {
            Connection conexao = DriverManager.getConnection(DB_url, DB_user, DB_password);

            if(conexao != null) {
                Statement meuStatement = conexao.createStatement();

                buscarNoBanco(meuStatement, "SELECT * FROM funcionario");
                
                executarNoBanco(meuStatement, "INSERT INTO funcionario (nome, email, senha, codigo_cargo) VALUES ('Inserido pelo JAVA','java@java.com', 'java123', 1)");
                
                executarNoBanco(meuStatement, "UPDATE funcionario SET nome = 'SetadoNoJava' WHERE id = 1");
                
                executarNoBanco(meuStatement, "DELETE FROM funcionario WHERE id = 2");
                
                buscarNoBanco(meuStatement, "SELECT * FROM paciente");
                
                executarNoBanco(meuStatement, "INSERT INTO paciente (nome) VALUES ('PacienteInseridoPeloJAVA')");
                
                executarNoBanco(meuStatement, "UPDATE paciente SET nome = 'SetadoNoJava' WHERE id = 1");
                
                executarNoBanco(meuStatement, "DELETE FROM paciente WHERE id = 1");
                
                buscarNoBanco(meuStatement, "SELECT * FROM atendimento");
                
                executarNoBanco(meuStatement, "INSERT INTO atendimento (data_hora, diagnostico, codigo_paciente, codigo_funcionario) VALUES ('2018-04-04 14:30:00', 'AtendimentoJAVA', 1, 1)");
                
                executarNoBanco(meuStatement, "UPDATE atendimento SET diagnostico = 'UpdateNoJava' WHERE id = 1");
                
                executarNoBanco(meuStatement, "DELETE FROM atendimento WHERE id = 1");
                
                conexao.close();
            } else {

            }

        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    static void buscarNoBanco(Statement meuStatement, String querySQL) {
        try {
            ResultSet resultado = meuStatement.executeQuery(querySQL);

            while(resultado.next()) {
                System.out.println(resultado);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    static void executarNoBanco(Statement meuStatement, String querySQL) {
        try {
            meuStatement.execute(querySQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
