import java.util.LinkedList;

public class Estabelecimento extends Identificador implements RepositorioDeTransacao{

    private double valorReceita;
    private LinkedList<Cliente> listaClientes;
    private GestorTransacoes gerente;
    private LinkedList<Cartao> listaTotalCartoes;

    /**
     * Construtor para criar um establecimento
     */
    public Estabelecimento(String nome, double valorReceitas){

        super(nome);
        this.valorReceita = valorReceitas;
        this.listaClientes = new LinkedList<>();
        this.gerente = new GestorTransacoes(this);
        listaTotalCartoes = new LinkedList<>();

    }

    public double getValorReceita() {
        return valorReceita;
    }

    public LinkedList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public LinkedList<Cartao> getListaTotalCartoes() {
        return listaTotalCartoes;
    }

    @Override
    public LinkedList<Transacao> getListaTransacoes() {
        return gerente.getListaTransacoes();
    }

    /**
     * Método que atualiza o valor da receita
     */
    public void aumentarReceita(double valor){
        this.valorReceita += valor;
    }

    /**
     * Método para adicionar cliente e validar se já existe ou se é recebido como null
     */
    public void adicionarCliente(Cliente cliente){
        if(cliente == null || this.listaClientes.contains(cliente)){
            return;
        }
        this.listaClientes.add(cliente);
    }

    /**
     * Método para adicionar cartões e validar se já existe ou se é recebido como null
     * Está lista guarda todos os cartões existentes
     */
    public void adicionarMetodosCartao(Cartao metodoCartao){
        if(listaTotalCartoes.contains(metodoCartao) || metodoCartao == null){
            return;
        }
        listaTotalCartoes.add(metodoCartao);
    }

    /**
     * Método para adicionar transação e validar se já existe ou se é recebido como null
     */
    @Override
    public void adiconarTransacao (Transacao transacao){
        gerente.adiconarTransacao(transacao);
    }

    @Override
    public String toString() {
        return "Nome:" + nome + "\n" +
                "Valor da Receita:" + valorReceita;
    }
}
