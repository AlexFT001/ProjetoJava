import java.util.LinkedList;

public interface RepositorioDeTransacao {
    LinkedList<Transacao> getListaTransacoes();
     void adiconarTransacao(Transacao transacao);

}
