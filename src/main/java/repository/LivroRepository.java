package repository;

import model.Livro;

import java.util.List;

public interface LivroRepository {

    Livro salvar(Livro livro);
    List<Livro> listarLivro();

    Livro buscarLivro(int id);

    Livro atualizar(Livro livro);

    void excluir(int id);
}
