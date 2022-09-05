package repository;

import model.Editora;

import java.util.List;

public interface EditoraRepository {

    Editora salvar(Editora editora);
    List<Editora> listarEditora();
    Editora buscarEditora(int id);
    Editora atualizarEditora(Editora editora);
    void excluirEditora(int codigo);

    List<Editora> porLocalDeOrigem(String localDeOrigem);

}
