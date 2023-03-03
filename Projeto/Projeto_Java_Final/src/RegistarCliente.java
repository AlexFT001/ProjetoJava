import java.util.LinkedList;

public class RegistarCliente {

    /**
     * Método para registar cliente
     * Valdia se o Nif já existe
     * E regista uma conta bancária
     */
    public static void registarCliente(Estabelecimento establecimento){
        boolean validacao;
        int  numeroNIF;

        String nome = Leitor.lerNome("Insira o nome do cliente: ");
        do {
            numeroNIF = Leitor.lerInteiro(100000000, 999999999, "Insira aqui o seu NIF: ", " O NIF somente pode possuir 9 digitos\n Tente outra vez: ");
            validacao = validacaoNumeroNIF(numeroNIF, establecimento);
        }while(!validacao);
        Cliente cliente = new Cliente(nome, numeroNIF);
        registarContaBancaria(cliente, establecimento);
        establecimento.adicionarCliente(cliente);
        System.out.println("\nCliente registrado com sucesso!");
    }

    /**
     * Método para registar conta bancária
     * Adiciona a conta bancária criada ao cliente criado anteriormente
     * E regista métodos de pagamento
     */
    private static void registarContaBancaria(Cliente cliente, Estabelecimento establecimento) {
        System.out.println("\n****Abrir conta bancária****");
        double valor = Leitor.lerDouble(0.00,1000000.00,"Insira o saldo da conta a abrir: ");
        ContaBancaria contaBancaria = new ContaBancaria(valor, cliente);
        cliente.setContaBancaria(contaBancaria);
        registarMetodosPagamento(contaBancaria, establecimento);
    }

    /**
     * Método para registar métodos de pagamento
     * Adiciona obrigatoriamente um cartão de debito á conta bancária e a lista de todos os cartões no estabelecimento
     * Apresenta um menu para criar os outros métodos caso desejado
     */
    private static void registarMetodosPagamento(ContaBancaria contaBancaria, Estabelecimento establecimento) {
        boolean validacao;
        int numeroIdentificacao;
        int contadorCc = 0;

        do{
            numeroIdentificacao = Leitor.lerInteiro(10000, 99999, "Insira o número de identificação do Cartão de Débito: ", " O numero somente pode possuir 5 digitos\n Tente outra vez: ");
            validacao = validacaoNumeroIdentificacaoCartao(numeroIdentificacao, establecimento.getListaTotalCartoes());
        }while(!validacao);
        int codigoPIN = Leitor.lerInteiro(1000, 9999, "Insira o numero PIN: ", " O PIN somente pode conter 4 digitos\n Tente outra vez: ");
        DataValidade data = new DataValidade();

        CartaoDebito cartaoDebito = new CartaoDebito(numeroIdentificacao, codigoPIN, contaBancaria, data);
        contaBancaria.adicionarMetodosCartao(cartaoDebito);
        establecimento.adicionarMetodosCartao(cartaoDebito);
        int opcao;

        do {
            opcao = Menu.menuMetodos();
            switch (opcao) {
                case 1:
                        registarCartaoCredito(contaBancaria, establecimento, contadorCc);
                        contadorCc++;
                    break;
                case 2:
                        registarMBWAY(contaBancaria, establecimento);
                    break;
                case 0:
                    break;
            }
        }while (opcao != 0);
    }

    /**
     * Método para registar Cartão de Crédito
     * Adiciona o cartão de crédito á conta bancária e a lista de todos os cartões no estabelecimento
     * E impede caso o cliente já possue 2 cartões de crédito
     */
    private static void registarCartaoCredito(ContaBancaria contaBancaria, Estabelecimento establecimento, int contadorCc){
        boolean validacao;
        int numeroIdentificacao;

        for (Cartao metodosPagamento : contaBancaria.getListaMetodosCartao()) {
            if (metodosPagamento instanceof CartaoCredito && contadorCc == 2) {
                System.out.println("Este cliente já possui 2 Cartão de crédito");
                return;
            }
        }
        do{
            numeroIdentificacao = Leitor.lerInteiro(10000, 99999, "Insira o número de identificação do Cartão de Crédito: ", " O numero somente pode possuir 5 digitos\n Tente outra vez: ");
            validacao = validacaoNumeroIdentificacaoCartao(numeroIdentificacao, establecimento.getListaTotalCartoes());
        }while(!validacao);
        int codigoPIN = Leitor.lerInteiro(1000, 9999, "Insira o numero PIN: ", " O PIN somente pode conter 4 digitos\n Tente outra vez: ");
        DataValidade data = new DataValidade();
        double valorLimite = Leitor.lerDouble(0.00, 250000.00, "Insira o valor limite: ");

        CartaoCredito cartaoCredito = new CartaoCredito(numeroIdentificacao, codigoPIN, valorLimite, contaBancaria, data);
        System.out.println("\n Cartão de crédito adicionado com sucesso!");
        contaBancaria.adicionarMetodosCartao(cartaoCredito);
        establecimento.adicionarMetodosCartao(cartaoCredito);

    }

    /**
     * Método para registar MBWAY
     * Adiciona o MBWAY á conta bancária e a lista de todos os cartões no estabelecimento
     * E impede caso o cliente já MBWAY
     */
    private static void registarMBWAY(ContaBancaria contaBancaria, Estabelecimento establecimento) {
        boolean validacao;
        int numeroIdentificacao;

        for (Cartao metodosPagamento : contaBancaria.getListaMetodosCartao()) {
            if (metodosPagamento instanceof MBWAY) {
                System.out.println("A conta bancária já possui MBWY");
                return;
            }
        }
        do{
            numeroIdentificacao = Leitor.lerInteiro(10000, 99999, "Insira o número de identificação do MBWY: ", " O numero somente pode possuir 5 digitos\n Tente outra vez: ");
            validacao = validacaoNumeroIdentificacaoCartao(numeroIdentificacao, establecimento.getListaTotalCartoes());
        }while(!validacao);
        int codigoPIN = Leitor.lerInteiro(1000, 9999, "Insira o numero PIN: ", " O PIN somente pode conter 4 digitos\n Tente outra vez: ");
        DataValidade data = new DataValidade();
        double valorLimite = Leitor.lerDouble(0.00, 250000.00, "Insira o valor limite: ");

        MBWAY mbway = new MBWAY(numeroIdentificacao, codigoPIN, valorLimite, contaBancaria, data);
        System.out.println("\nCartão MBWAY adicionado com sucesso!");

        contaBancaria.adicionarMetodosCartao(mbway);
        establecimento.adicionarMetodosCartao(mbway);
    }

    /**
     * Método para verificar se NIF já existe
     */
    private static boolean validacaoNumeroNIF(int numeroNIF, Estabelecimento establecimento){
        for (Cliente cliente : establecimento.getListaClientes()) {
            if(numeroNIF == cliente.getNumeroNif()){
                System.out.println("O numero NIF já existe!");
                return false;
            }
        }
        return true;
    }

    /**
     * Método para verificar se o número de idententificação do cartão já existe
     */
    private static boolean validacaoNumeroIdentificacaoCartao(int numeroIdentificacao, LinkedList<Cartao> totalListaMetodosCartao){
        for (Cartao metodoPagamento : totalListaMetodosCartao) {
                if(numeroIdentificacao == metodoPagamento.getNumeroIdentificacao()){
                    System.out.println("O numero de identificação já existe!");
                    return false;
                }
            }
        return true;
    }
}
