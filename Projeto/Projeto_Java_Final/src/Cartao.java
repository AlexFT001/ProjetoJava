import Exceptions.PrincipalPagamento;

public abstract class Cartao extends MetodoPagamento{
    protected int numeroIdentificacao;
    protected ContaBancaria contaBancaria;
    protected DataValidade dataValidacao;
    protected int codigoPIN;

    /**
     * Construtor para criar um cartao debito/credito/MBWAY
     */
    public Cartao(int numeroIdentificacao, int codigoPIN, ContaBancaria contaBancaria, DataValidade dataValidacao){
        super();
        this.numeroIdentificacao = numeroIdentificacao;
        this.dataValidacao = dataValidacao;
        this.codigoPIN = codigoPIN;
        this.contaBancaria = contaBancaria;

    }
    public int getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    /**
     * Método para verificar se há saldo suficiente na conta tendo em conta o atributo enviado por parâmetro
     */
    public Boolean verificacaoSaldo(double valorPago){
        if(valorPago <= this.contaBancaria.getValorSaldo()){
            return true;
        }
            return false;
    }

    /**
     * Método para verificar se o codigo pin coincide com o atributo enviado por parâmetro
     */
    public Boolean verificacaoCodigoPIN(int codigoPIN){
        if(codigoPIN == this.codigoPIN){
            return true;
        }
            return  false;
    }

    protected abstract void pagamentoEfetuado(double valor, Estabelecimento establecimento) throws PrincipalPagamento;








}
