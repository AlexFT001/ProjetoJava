import Exceptions.PrincipalExcecoes;

public class Main {
    public static void main(String[] args) {
        int opcao;

        Estabelecimento establecimento = new Estabelecimento("Tiago dos 1000 pratos",10000);

       do {
            opcao = Menu.menu();

            switch (opcao) {
                case 1:
                        System.out.println(establecimento);
                        System.out.println("Lista de Transações: ");
                        if(establecimento.getListaTransacoes().size() > 0) {
                            for (Transacao transacao : establecimento.getListaTransacoes()) {
                                System.out.println(transacao);
                            }
                        } else{
                            System.out.println("Não existem transações.");
                        }
                    break;
                case 2:
                    System.out.println("Lista de Clientes: ");
                    if(establecimento.getListaClientes().size() > 0) {
                        for (Cliente cliente : establecimento.getListaClientes()) {
                            System.out.println(cliente);
                        }
                    } else{
                        System.out.println("Não existem clientes.");
                    }
                    break;
                case 3:
                    RegistarCliente.registarCliente(establecimento);
                    break;
                case 4:
                    try {
                        RegistarTransacao.registoTransacao(establecimento);
                    } catch (PrincipalExcecoes erro){
                        erro.erroImprimir();
                    }
                    break;
                case 5:
                   switchEstatisticas(establecimento);
                    break;
                case 6:
                    GerirFicheiros.gravarFicheiro(establecimento);
                    break;
                case 7:
                    Estabelecimento establecimentoTeste = GerirFicheiros.lerFicheiro();
                    if(establecimentoTeste != null){
                        establecimento = establecimentoTeste;
                        System.out.println("\nFicheiro lido com sucesso!");
                    }
                    break;
                case 0:
                    Leitor.fecharTeclado();
                    break;
            }
        } while (opcao != 0);
    }

    private static void switchEstatisticas(Estabelecimento establecimento) {
        int opcaoN;
        do {
             opcaoN = Menu.menuEstatisticas();
            switch (opcaoN) {
                case 1:
                    Estatisticas.estatisticasEstablecimento(establecimento);
                    break;
                case 2:
                    if(establecimento.getListaClientes().size() > 0) {
                        Estatisticas.clienteMaisTransacoes(establecimento);
                        Estatisticas.clienteDespendeuMaiorValor(establecimento);
                    }else {
                        System.out.println("\n---Não existem clientes---");
                    }
                    break;
                case 3:
                    if(establecimento.getListaTransacoes().size() > 0) {
                    Estatisticas.transacaoMaioreMenorValor(establecimento);
                    Estatisticas.transacoesValorMedio(establecimento);
                    }else {
                        System.out.println("\n---Não existem transação---");
                    }
                    break;
                case 4:
                    if(establecimento.getListaTransacoes().size() > 0) {
                        Estatisticas.percentagemMetodosPagamento(establecimento);
                    }else {
                        System.out.println("\n---Não existem transação---");
                    }
                    break;
            }
        }while(opcaoN != 0);
    }
}