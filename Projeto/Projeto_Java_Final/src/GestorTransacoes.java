import java.io.Serializable;
import java.util.LinkedList;

public class GestorTransacoes implements Serializable {
    private LinkedList<Transacao> listaTransacoes;
    private RepositorioDeTransacao delegador;

    public GestorTransacoes(RepositorioDeTransacao delegador){
        this.listaTransacoes = new LinkedList<>();
        this.delegador = delegador;
    }
    public LinkedList<Transacao> getListaTransacoes() {
        return this.listaTransacoes;
    }

    /**
     * Método para adicionar uma transação a lista
     */
    public void adiconarTransacao(Transacao transacao){
        if(listaTransacoes.contains(transacao) || transacao == null){
            return;
        }
        listaTransacoes.add(transacao);
    }

}
