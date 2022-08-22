package repository.implement;

import model.Editora;
import model.Livro;
import repository.LivroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LivroImplementEmMemoria implements LivroRepository {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private List<Livro> livroSalvo = new ArrayList<>() ;

    public Livro salvar(Livro livro){
        Livro salvarLivro = new Livro(livro.getTitulo(), livro.getDataDeLancamento(), livro.getEditora());
        salvarLivro.setId(ID_GENERATOR.getAndIncrement());
        livroSalvo.add(salvarLivro);
        return salvarLivro;
    }
    public List<Livro> listarLivro(){
        return livroSalvo;
    }

    @Override
    public Livro buscarLivro(int id) {
        return null;
    }

    @Override
    public Livro atualizar(Livro livro) {
        return null;
    }

    @Override
    public void excluir(int id) {

    }
}

