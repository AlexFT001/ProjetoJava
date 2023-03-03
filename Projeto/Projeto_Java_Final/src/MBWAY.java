import Exceptions.PagamentoCCredito;
import Exceptions.PagamentoMBWAY;

public class MBWAY extends  CartaoCredito{

    /**
     * Construtor para criar um MBWAY
     */
    public MBWAY(int numeroIdentificacao, int codigoPIN, double valorLimite, ContaBancaria contaBancaria, DataValidade dataValidacao) {
        super(numeroIdentificacao, codigoPIN, valorLimite, contaBancaria, dataValidacao);
    }

    /**
     * Método que verifica se é possível realizar o pagamento.
     * Caso seja retira o valor da conta e deposita no establecimento.
     */
    @Override
    public void pagamentoEfetuado(double valor, Estabelecimento establecimento) throws PagamentoCCredito, PagamentoMBWAY {

        verificarValorLimite(valor);

        if(!verificacaoSaldo(valor)){
            throw  new PagamentoMBWAY();
        }
        this.contaBancaria.subtrairSaldo(valor);
        establecimento.aumentarReceita(valor);
    }

    @Override
    public String toString() {
        return " MBWAY";
    }
}
