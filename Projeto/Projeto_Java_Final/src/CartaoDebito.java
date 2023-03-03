import Exceptions.PagamentoCDebito;

public class CartaoDebito extends Cartao {

    /**
     * Construtor para criar o Cartão de débito
     */
    public CartaoDebito(int numeroIdentificacao, int codigoPIN, ContaBancaria contaBancaria, DataValidade dataValidacao) {
        super(numeroIdentificacao, codigoPIN, contaBancaria, dataValidacao);
    }

    /**
     * Método que verifica se é possível realizar o pagamento.
     * Caso seja retira o valor da conta e deposita no establecimento.
     */
    @Override
    public void pagamentoEfetuado(double valor, Estabelecimento establecimento) throws PagamentoCDebito {

        if(!verificacaoSaldo(valor)){
            throw  new PagamentoCDebito();
        }
        this.contaBancaria.subtrairSaldo(valor);
        establecimento.aumentarReceita(valor);
    }


    @Override
    public String toString() {
        return " Cartão de Débito";
    }
}
