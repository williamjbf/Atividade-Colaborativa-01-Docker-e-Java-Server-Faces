package repository.implement;

import model.Editora;
import repository.EditoraRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditoraImplementJDBC implements EditoraRepository {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    private Connection connection;

    public EditoraImplementJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/jsf-db",
                    "postgres", "postgres"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EditoraImplementJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Editora salvar(Editora editora) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + Editora.TABLE_NAME + " (localDeOrigem, nomeFantasia) VALUES ( ?, ? );"
            );
            statement.setString(1, editora.getLocalDeOrigem());
            statement.setString(2, editora.getNomeFantasia());

            statement.executeQuery();

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                int codigo = result.getInt(1);
                return buscarEditora(codigo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditoraImplementJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Editora();
    }

    @Override
    public List<Editora> listarEditora() {
        try {
            List<Editora> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM " + Editora.TABLE_NAME
            ).executeQuery();
            while (result.next()) {
                Editora editora = new Editora();
                editora.setCodigo(result.getInt("codigo"));
                editora.setLocalDeOrigem(result.getString("localDeOrigem"));
                editora.setNomeFantasia(result.getString("nomeFantasia"));
                lista.add(editora);
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    public Editora buscarEditora(int id) {
        try {
            Editora editora = new Editora();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + Editora.TABLE_NAME + " WHERE codigo = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                editora.setCodigo(result.getInt("codigo"));
                editora.setLocalDeOrigem(result.getString("localDeOrigem"));
                editora.setNomeFantasia(result.getString("nomeFantasia"));
            }
            return editora;
        }
        catch(SQLException ex){
            return new Editora();
        }
    }

    @Override
    public Editora atualizarEditora(Editora editora) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update "+ Editora.TABLE_NAME+" set localdeorigem = ?, nomefantasia = ? where codigo = ?;"
            );
            statement.setString(2,editora.getLocalDeOrigem());
            statement.setString(3,editora.getNomeFantasia());

            statement.executeUpdate();

            return editora;

        } catch (SQLException ex) {
            Logger.getLogger(EditoraImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Editora();
        }
    }

    @Override
    public void excluirEditora(int codigo) {
        try {
            PreparedStatement statement =connection.prepareStatement(
                    "delete from " + Editora.TABLE_NAME + " where codigo = ?;"
            );
            statement.setInt(1, codigo);
            statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(EditoraImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
