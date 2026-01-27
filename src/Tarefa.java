public class Tarefa {
    private String titulo;
    private String descricao;
    private String dataLimite;
    private String status;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tarefa(String titulo, String descricao, String dataLimite, String status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;


    }
}
