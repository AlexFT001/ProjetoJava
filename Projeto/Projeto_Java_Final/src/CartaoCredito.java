import Exceptions.PagamentoCCredito;
import Exceptions.PagamentoMBWAY;

public class CartaoCredito extends Cartao {

    protected double valorLimite;

    /**
     * Construtor para criar o Cartão de crédito
     */
    public CartaoCredito(int numeroIdentificacao, int codigoPIN, double valorLimite, ContaBancaria contaBancaria, DataValidade dataValidacao) {
        super(numeroIdentificacao, codigoPIN,contaBancaria, dataValidacao);
        this.valorLimite = valorLimite;
    }

    /**
     * Método que verifica se é possível realizar o pagamento.
     * Caso seja retira o valor da conta e deposita no establecimento.
     */
    @Override
    public void pagamentoEfetuado(double valor, Estabelecimento establecimento) throws PagamentoCCredito, PagamentoMBWAY {
        this.verificarValorLimite(valor);
        this.contaBancaria.subtrairSaldo(valor);
        establecimento.aumentarReceita(valor);

    }

    /**
     * Método para verificar valor limite
     * Que é usada nas verifacações da função pagamentoEfetuado da Classe
     */
    public void verificarValorLimite(double valor) throws PagamentoCCredito {
        if(valor > valorLimite){
            throw new PagamentoCCredito();
        }
    }

    @Override
    public String toString() {
        return " Cartão de crédito" + " Número: " +  numeroIdentificacao;
    }
}
