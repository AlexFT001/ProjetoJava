import java.util.LinkedList;

public class ContaBancaria extends Utilizador implements  RepositorioDeTransacao {

    private double valorSaldo;
    private GestorTransacoes gerente;
    private LinkedList<Cartao> listaMetodosCartao;

    private static int numeroContaBancariaInicial = 1000;
    private final int numeroContaBancariaPessoal;

    /**
     * Construtor que cria uma Conta bancária
     */
    public ContaBancaria(double valorSaldo, Cliente cliente) {
        super(cliente);
        this.valorSaldo = valorSaldo;
        this.gerente = new GestorTransacoes(this);
        this.listaMetodosCartao = new LinkedList<>();
        this.numeroContaBancariaPessoal = numeroContaBancariaInicial;
        numeroContaBancariaInicial++;

    }

    public double getValorSaldo() {
        return valorSaldo;
    }

    @Override
    public LinkedList<Transacao> getListaTransacoes() {
        return gerente.getListaTransacoes();
    }

    public LinkedList<Cartao> getListaMetodosCartao() {
        return listaMetodosCartao;
    }
    public int getNumeroContaBancariaPessoal() {
        return numeroContaBancariaPessoal;
    }

    /**
     * Método para adicionar uma transação e validar se ja existe ou se é recebida como null
     */
    @Override
    public void adiconarTransacao(Transacao transacao){
        gerente.adiconarTransacao(transacao);
    }

    /**
     * Método para adicionar um métode de pagamento do tipo Cartao e validar se já existe ou se é recebido como null
     */
    public void adicionarMetodosCartao(Cartao metodosCartao){
        if(listaMetodosCartao.contains(metodosCartao) || metodosCartao == null){
            return;
        }
        listaMetodosCartao.add(metodosCartao);

    }

    /**
     * Método para atualizar Saldo
     */
    public void subtrairSaldo(double valorSaldo) {
        this.valorSaldo -= valorSaldo;
    }

    /**
     * Método que devolve o número de transações realizadas de cada cliente
     */
    public int numeroTransacoes(){
        return gerente.getListaTransacoes().size();
    }

    /**
     * Método que devolve o valor total das transações realizadas por cada cliente
     */
    public double valorTotalTransacoes(){
        double valorTotal = 0.00;
        for (Transacao transacao : gerente.getListaTransacoes()) {
            valorTotal+=transacao.getValorTransacao();
        }
        return valorTotal;
    }

    /**
     * Método para mostrar a lista de métodos de pagamento do cliente
     */
    public void mostrarFormasPagamento(){
        int i = 1;
        for(MetodoPagamento pagamento : getListaMetodosCartao()){
            i++;
            System.out.println(i + "." + pagamento);
        }
    }

    @Override
    public String toString() {
        return "" + numeroContaBancariaPessoal;
    }
}
