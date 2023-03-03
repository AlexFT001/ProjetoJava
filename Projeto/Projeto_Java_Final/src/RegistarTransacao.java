import Exceptions.*;

public class RegistarTransacao {

    /**
     * Método que regista uma nova transação
     * Verifica o cliente associado a partir do número NIF
     * Apresenta os métodos que o cliente possui
     * Continua a transação a partir do método escolhido
     */
    public static void registoTransacao(Estabelecimento establecimento) throws PrincipalExcecoes {
        boolean validacao;
        int numeroNIF;
        int codigoPIN;
        int cancelar;
        int tentativas = 0;
        boolean confirmaco;
        Cartao metodoPagamentoEscolhido;

        System.out.println("\n****Registar Transacao****");

        do{
            cancelar = Leitor.lerInteiro(0, 1, "Se deseja cancelar prima 0, se deseja continuar prime 1: ","Opção não encontrada!\n Tente outra vez: ");
            if(cancelar == 0){
                return;
            }

            numeroNIF = Leitor.lerInteiro(100000000,999999999, "Insira o NIF do cliente: ", "O NIF somente pode conter 9 digitos!\n Tente outra vez: ");
            validacao = verificarNIF(numeroNIF,establecimento);

        }while(!validacao);

        Cliente clienteAtual = escolherPagamento(numeroNIF, establecimento);

        int opcaoCliente = Leitor.lerInteiro(1,4,"","Selecionar entre entre 1 e 4!\n Tente outra vez: ");

        switch (opcaoCliente){
            case 1:
                continuarRegistoNumerario(clienteAtual, establecimento);
                break;
            default:
                metodoPagamentoEscolhido = clienteAtual.getContaBancaria().getListaMetodosCartao().get(opcaoCliente-2);
                do {
                    codigoPIN = Leitor.lerInteiro(1000, 9999, "Insira o seu codigo PIN: ", "O numero PIN tem de possuir 4 digitos\n Tente outra vez: ");
                        confirmaco = metodoPagamentoEscolhido.verificacaoCodigoPIN(codigoPIN);
                        if (!confirmaco) {
                            tentativas++;
                        }
                    if (tentativas == 3) {
                        throw new TentativasPIN();
                    }
                }while (!confirmaco);
                continuarRegistoCartao(clienteAtual, metodoPagamentoEscolhido, establecimento);
                break;
        }
        System.out.println("\nTransação registada com sucesso!");
    }
    /**
     * Método que continua a transação com um tipo de cartão já escolhido
     * Adiciona a transação á conta báncaria do cliente e ao estabelecimento
     * Verifica se o cartão está dentro de validade e efetua o pagamento conforme o cartão escolhido
     */
    private static void continuarRegistoCartao(Cliente cliente, Cartao metodoPagamento, Estabelecimento establecimento) throws PrincipalPagamento, ValidadeCartao {

        double valor = Leitor.lerDouble(0.00, 100000.00, "Insira aqui o valor da transação: ");
        efetuarPagamento(metodoPagamento, valor, establecimento);
        Data transacaoData = new Data();
        compararDatas(metodoPagamento, transacaoData);
        Transacao transacao = new Transacao(valor, cliente, transacaoData, metodoPagamento);
        establecimento.adiconarTransacao(transacao);
        cliente.getContaBancaria().adiconarTransacao(transacao);
    }

    /**
     * Método que continua a transação com o Numerário
     * Adiciona a transação á conta báncaria do cliente e ao estabelecimento
     * E aumenta o valor de receita do establecimento
     */
    private static void continuarRegistoNumerario(Cliente cliente, Estabelecimento establecimento) {

        double valor = Leitor.lerDouble(0.00, 10000.00, "Insira aqui o valor da transação: ");
        Data transacaoData = new Data();
        Numerario numerario = new Numerario(valor);
        Transacao transacao = new Transacao(valor, cliente, transacaoData, numerario);
        establecimento.adiconarTransacao(transacao);
        establecimento.aumentarReceita(valor);
        cliente.getContaBancaria().adiconarTransacao(transacao);
    }

    /**
     * Método que efetua o pagamento da transação
     */
    private static void efetuarPagamento(Cartao metodoPagamento, double valor, Estabelecimento establecimento) throws PrincipalPagamento {
        metodoPagamento.pagamentoEfetuado(valor, establecimento);
    }

    /**
     * Método para verificar se existe o numero NIF.
     */
    private static boolean verificarNIF(int numeroNIF, Estabelecimento establecimento) {
        for (Cliente cliente : establecimento.getListaClientes()) {
            if (numeroNIF == cliente.getNumeroNif()) {
                return true;
            }
        }
        System.out.println("Numero NIF não encontrado!");
        return false;
    }

    /**
     * Método para escolher metodos de pagamento do cliente
     */
    private static Cliente escolherPagamento(int numeroNIF, Estabelecimento establecimento){
        Cliente clienteR = establecimento.getListaClientes().get(0);
        for(Cliente cliente : establecimento.getListaClientes()){
            if(numeroNIF == cliente.getNumeroNif()){
                System.out.println("Metodos de pagamento para o cliente " + cliente.getNome() + ": ");
                System.out.println("1. Numerário");
                cliente.getContaBancaria().mostrarFormasPagamento();
                System.out.print("\n Selecione a opção: ");
                clienteR = cliente;
            }
        }
        return  clienteR;
    }

    /**
     * Método que compara a data de validade do cartão com a data da transação
     */
    private static void compararDatas(Cartao metodoPagamento, Data datatransacao) throws ValidadeCartao {
        if(datatransacao.getAno() > metodoPagamento.dataValidacao.ano || datatransacao.getAno() == metodoPagamento.dataValidacao.ano && datatransacao.getMes() > metodoPagamento.dataValidacao.mes){
            throw  new ValidadeCartao();
        }
    }

}
