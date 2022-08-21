package model;

import java.util.Objects;

public class Editora {

    private int codigo;
    private String localDeOrigem;
    private String nomeFantasia;

    public Editora(int codigo, String localDeOrigem, String nomeFantasia) {
        this.codigo = codigo;
        this.localDeOrigem = localDeOrigem;
        this.nomeFantasia = nomeFantasia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editora editora = (Editora) o;
        return codigo == editora.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}
