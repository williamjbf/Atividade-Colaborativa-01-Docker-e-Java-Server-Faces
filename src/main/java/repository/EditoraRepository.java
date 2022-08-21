package repository;

import model.Editora;

import java.util.List;

public interface EditoraRepository {

    void salvar(Editora editora);
    List<Editora> listarEditora();
    Editora buscarEditora(Long id);
}
