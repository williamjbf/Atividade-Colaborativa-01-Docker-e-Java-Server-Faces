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
    public Livro salvar(Livro livro) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO "+livro.TABLE_NAME+" (titulo, dataDeLancamento, idEditora) VALUES ( ?, ?, ? ) RETURNING id;"
            );
            statement.setString(1, livro.getTitulo());
            statement.setDate(2, new java.sql.Date (livro.getDataDeLancamento().getTime()));
            statement.setInt(3, livro.getEditora().getCodigo());

            statement.executeQuery();

            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                int id = result.getInt(1);
                return buscarLivro(id);
            }
            return new Livro();
        }catch (SQLException ex){
            Logger.getLogger(LivroImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Livro();
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
                livro.setId(result.getInt("id"));
                livro.setTitulo(result.getString("titulo"));
                livro.setDataDeLancamento(result.getDate("dataDeLancamento"));
                Editora editora = editoraRepository.buscarEditora(result.getInt("ideditora"));
                livro.setEditora(editora);
                lista.add(livro);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LivroImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return Collections.EMPTY_LIST;
        }

    }

    public Livro buscarLivro(int id){
        try {
            Livro livro = new Livro();
            PreparedStatement statement =connection.prepareStatement(
                    "SELECT * FROM " + Livro.TABLE_NAME + " where id = ?;"
            );
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                livro.setId(result.getInt("id"));
                livro.setTitulo(result.getString("titulo"));
                livro.setDataDeLancamento(result.getDate("dataDeLancamento"));
                Editora editora = editoraRepository.buscarEditora(livro.getId());
                livro.setEditora(editora);
            }
            return livro;
        } catch (SQLException ex) {
            Logger.getLogger(LivroImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Livro();
        }
    }

    @Override
    public Livro atualizar(Livro livro) {
        try {
        PreparedStatement statement = connection.prepareStatement(
                "update "+Livro.TABLE_NAME+" set titulo = ?, datadelancamento = ?,ideditora = ? where id = ?;"
        );
        statement.setString(1,livro.getTitulo());
        statement.setDate(2,new java.sql.Date (livro.getDataDeLancamento().getTime()));
        statement.setInt(3,livro.getEditora().getCodigo());
        statement.setInt(4,livro.getId());
        statement.executeUpdate();

        return livro;

        } catch (SQLException ex) {
            Logger.getLogger(LivroImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return new Livro();
        }
    }

    @Override
    public void excluir(int id) {
        try {
            PreparedStatement statement =connection.prepareStatement(
                    "delete from " + Livro.TABLE_NAME + " where id = ?;"
            );
            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(LivroImplementJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
