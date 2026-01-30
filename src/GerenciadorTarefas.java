import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorTarefas {
    private final List<Tarefa> tarefas = new ArrayList<>();


    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }


    public List<Tarefa> listarTodas() {
        return Collections.unmodifiableList(tarefas);
    }


    public List<Tarefa> filtrarPorStatus(StatusTarefa status) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }


    public List<Tarefa> listarOrdenadasPorDataLimite() {
        return tarefas.stream()
                .sorted(Comparator.comparing(Tarefa::getDataLimite))
                .collect(Collectors.toList());
    }
}