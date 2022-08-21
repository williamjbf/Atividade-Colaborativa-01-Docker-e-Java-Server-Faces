package controller;

import model.Editora;
import repository.EditoraRepository;
import repository.implement.EditoraImplementEmMemoria;

public class EditoraController {
    private Editora editora = new Editora();
    private String busca = "";

    private EditoraRepository repository = new EditoraImplementEmMemoria();

    public String salvar() {
        repository.salvar(editora);
        return "listar";
    }

}
