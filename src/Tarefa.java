import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Tarefa implements Validavel {
    private final String titulo;
    private final String descricao;
    private final LocalDate dataLimite;
    private StatusTarefa status;

    public Tarefa(String titulo, String descricao, LocalDate dataLimite, StatusTarefa status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.status = status;
        validar();
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataLimite() { return dataLimite; }
    public StatusTarefa getStatus() { return status; }
    public void setStatus(StatusTarefa status) {
        this.status = status;
        validar();
    }

    public boolean tarefaAtrasada() {
        return this.status != StatusTarefa.CONCLUIDA && dataLimite.isBefore(LocalDate.now());
    }

    @Override
    public void validar() {
        if (titulo == null || titulo.length() < 5)
            throw new IllegalArgumentException("O título deve ter pelo menos 5 caracteres.");
        if (dataLimite == null || dataLimite.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("A data limite não pode ser no passado.");
        if (status == null)
            throw new IllegalArgumentException("O status não pode ser nulo.");
        if (status != StatusTarefa.PENDENTE && status != StatusTarefa.EM_ANDAMENTO && status != StatusTarefa.CONCLUIDA)
            throw new IllegalArgumentException("Status inválido.");
    }

    @Override
    public String toString() {
        String situacao;
        if (status == StatusTarefa.CONCLUIDA) {
            situacao = " CONCLUÍDA";
        } else if (status == StatusTarefa.PENDENTE) {
            situacao = "PENDENTE";
        } else if (tarefaAtrasada()) {
            situacao = " ATRASADA";
        } else {
            situacao = "EM ANDAMENTO";
        }
        return String.format("Título: %s | Descrição: %s | Data Limite: %s | Status: %s | Situação: %s",
                titulo, descricao, dataLimite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), status, situacao);
    }
}

