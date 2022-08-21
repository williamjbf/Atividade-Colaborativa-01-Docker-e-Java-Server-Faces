package repository.implement;

import model.Editora;
import model.Livro;
import repository.EditoraRepository;
import repository.LivroRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroImplementJDBC implements LivroRepository {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    private EditoraRepository editoraRepository = new EditoraImplementJDBC();
    private Connection connection;

    public LivroImplementJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/jsf-db",
                    "postgres","postgres"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EditoraImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
    @Override
    public void salvar(Livro livro) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO "+"teste"+" (titulo, dataDeLancamento, idEditora) VALUES ( ?, ?, ? );"
            );
            statement.setString(1, livro.getTitulo());
            statement.setDate(2, Date.valueOf(livro.getDataDeLancamento()));
            statement.setInt(3, livro.getEditora().getCodigo());

            statement.executeUpdate();

        }catch (SQLException ex){
            Logger.getLogger(LivroImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    @Override
    public List<Livro> listarLivro() {
        try {
            List<Livro> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM " + Livro.TABLE_NAME
            ).executeQuery();
            while (result.next()) {
                Livro livro = new Livro();
                livro.setTitulo(result.getString("titulo"));
                livro.setDataDeLancamento(result.getDate("dataDeLancamento").toLocalDate());
                Editora editora = editoraRepository.buscarEditora(livro.getId());
                livro.setEditora(editora);
                lista.add(livro);
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }

    }
}
