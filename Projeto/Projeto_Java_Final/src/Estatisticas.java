public class Estatisticas {

    /**
     * Método que mostra a quantidade de clientes e transações do establecimento e o valor da receita do mesmo
     */
    public static void estatisticasEstablecimento(Estabelecimento establecimento){

        System.out.println("Existem " + establecimento.getListaClientes().size() + " clientes registados.");
        System.out.println("\nForam efetuadas " + establecimento.getListaTransacoes().size() + " transações.");
        System.out.println("O valor de receita do establecimento é de: " +  establecimento.getValorReceita());
    }

    /**
     * Método para mostrar o cliente que realizou mais transações
     */
    public static void clienteMaisTransacoes(Estabelecimento establecimento){

        int auxilio = 0;
        Cliente clienteAux = establecimento.getListaClientes().get(0);

        for (Cliente cliente : establecimento.getListaClientes()) {

            if(auxilio < cliente.getContaBancaria().numeroTransacoes()){
                auxilio = cliente.getContaBancaria().numeroTransacoes();
                clienteAux = cliente;
            }
        }
        System.out.println("Cliente que com maior número de transações: " + clienteAux.getNome());
    }

    /**
     * Método para mostrar o cliente que despendeu maior valor em transações
     */
    public static void clienteDespendeuMaiorValor(Estabelecimento establecimento){
        double auxilio = 0.00;
        Cliente clienteAux = establecimento.getListaClientes().get(0);

        for (Cliente cliente : establecimento.getListaClientes()) {

            if(auxilio < cliente.getContaBancaria().valorTotalTransacoes()){
                auxilio = cliente.getContaBancaria().valorTotalTransacoes();
                clienteAux = cliente;
            }
        }
        System.out.println("Cliente que teve maior gastos em transações: " + clienteAux.getNome());
    }

    /**
     * Método para mostrar a transação com maior e menor valor
     */
    public static void transacaoMaioreMenorValor(Estabelecimento establecimento){

            double auxilioMaior = 0.00;
            Transacao transacaoAuxMaior = establecimento.getListaTransacoes().get(0);

            double auxilioMenor = 1000000.00;
            Transacao transacaoAuxMenor = establecimento.getListaTransacoes().get(0);

            for (Transacao transacao : establecimento.getListaTransacoes()) {
                if (auxilioMaior < transacao.getValorTransacao()) {
                    auxilioMaior = transacao.getValorTransacao();
                    transacaoAuxMaior = transacao;
                }

                if (auxilioMenor > transacao.getValorTransacao()) {
                    auxilioMenor = transacao.getValorTransacao();
                    transacaoAuxMenor = transacao;
                }
            }
            System.out.println("Transação com maior valor: " + "\n" + transacaoAuxMaior);
            System.out.println("Transação com menor valor: " + "\n" + transacaoAuxMenor);
    }

    /**
     * Método para calcular e mostra a média
     */
    public static void transacoesValorMedio(Estabelecimento establecimento){
        double auxilio = 0.00;

        for (Cliente cliente : establecimento.getListaClientes()) {
            auxilio+=cliente.getContaBancaria().valorTotalTransacoes() / establecimento.getListaTransacoes().size();
        }
        System.out.println("Média: " + auxilio);
    }

    /**
     * Método que calcuça a quantidade utilazada de cada tipo de pagamento
     */
    public static void percentagemMetodosPagamento(Estabelecimento establecimento){
        double cartaoDebitoQuantidade = 0, cartaoCreditoQuantidade = 0, mBWAYQuantidade = 0, numerarioQuantidade = 0;
        for (Transacao transacao : establecimento.getListaTransacoes()) {
            if(transacao.getMetodoPagamento() instanceof CartaoDebito){
                cartaoDebitoQuantidade++;
            } else if (transacao.getMetodoPagamento() instanceof CartaoCredito) {
                cartaoCreditoQuantidade++;
            } else if (transacao.getMetodoPagamento() instanceof MBWAY) {
                mBWAYQuantidade++;
            } else if (transacao.getMetodoPagamento() instanceof Numerario) {
                numerarioQuantidade++;
            }
        }

        percentagemPagamentosQuantidade(cartaoDebitoQuantidade,cartaoCreditoQuantidade,mBWAYQuantidade, numerarioQuantidade);
        percentagemPagamentosValor(cartaoDebitoQuantidade,cartaoCreditoQuantidade,mBWAYQuantidade, numerarioQuantidade);

    }

    /**
     * Método para mostra a percentagem em quantidade de cada tipo de pagamento
     */
    private static void percentagemPagamentosQuantidade(double cartaoDebitoQuantidade, double cartaoCreditoQuantidade, double mBWAYQuantidade, double numerarioQuantidade) {

        System.out.println("Em quantidade:");
        System.out.println("O cartão de debito tem uma percentagem de " + cartaoDebitoQuantidade);
        System.out.println("O cartão de Crédito tem uma percentagem de " + cartaoCreditoQuantidade);
        System.out.println("O MBWAY tem uma percentagem de " + mBWAYQuantidade);
        System.out.println("O numerário tem uma percentagem de " + numerarioQuantidade);
    }

    /**
     * Método para mostra a percentagem em valor de cada tipo de pagamento
     */
    private static void percentagemPagamentosValor(double cartaoDebitoQuantidade, double cartaoCreditoQuantidade, double mBWAYQuantidade, double numerarioQuantidade) {
        double valorTotal = cartaoDebitoQuantidade + cartaoCreditoQuantidade + mBWAYQuantidade + numerarioQuantidade;

        System.out.println("\n\nEm valor: ");
        System.out.println("O cartão de debito tem uma percentagem de " + (cartaoDebitoQuantidade*100) / valorTotal + "%");
        System.out.println("O cartão de Crédito tem uma percentagem de " + (cartaoCreditoQuantidade*100) / valorTotal + "%");
        System.out.println("O MBWAY tem uma percentagem de " + (mBWAYQuantidade*100) / valorTotal + "%");
        System.out.println("O numerário tem uma percentagem de " + (numerarioQuantidade*100) / valorTotal + "%");
    }
}
