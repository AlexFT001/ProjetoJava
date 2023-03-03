public class Transacao extends Utilizador {
    private final DataValidade data;
    private final double valorTransacao;
    private final MetodoPagamento metodoPagamento;

    /**
     * Construtor para criar uma transação
     */
    public Transacao(double valor, Cliente cliente, DataValidade data, MetodoPagamento metodoPagamento) {
        super(cliente);
        this.data = data;
        this.valorTransacao = valor;
        this.metodoPagamento = metodoPagamento;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    @Override
    public String toString() {
        return " Data:" + data +
                ", Valor da Transacao:" + valorTransacao +
                ", Cliente:" + cliente.getNome() + "-" + cliente.getNumeroNif() +
                ", Metodo  de Pagamento:" + metodoPagamento;
    }
}