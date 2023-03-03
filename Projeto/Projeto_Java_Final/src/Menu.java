public class Menu {

    /**
     * Método para mostrar menu inicial
     */
    public static int menu(){

        System.out.println("\n\t\t\t Establecimento\n");
        System.out.println("1. Informações do establecimento");
        System.out.println("2. Listagem dos Clientes");
        System.out.println("3. Registar Cliente");
        System.out.println("4. Registar transação");
        System.out.println("5. Estatísticas do estabelecimento");
        System.out.println("6. Gravar");
        System.out.println("7. Ler informação");
        System.out.println("0. Sair da aplicação");
        System.out.print("\n\t\tSelecione a opcao:");
        return Leitor.lerInteiro(0,7, "", "O número tem de estar entre 0 e 7\n Tente outra vez: ");
    }

    /**
     * Método para mostrar menu das Estatisticas(Switch(Case 5))
     */
    public static int menuEstatisticas() {

        System.out.println("\n\t\t\t Estatísticas\n");
        System.out.println("1. Estatisticas Gerais do estabelecimento");
        System.out.println("2. Estatísticas dos clientes");
        System.out.println("3. Estatísticas das transações");
        System.out.println("4. Percentagem de pagamentos por cada tipo de método de pagamento");
        System.out.println("0. Sair da aplicação");
        System.out.println("\n\t\tSelecione a opcao:");
        return Leitor.lerInteiro(0,4, "", "O número tem de estar entre 0 e 10\n Tente outra vez: ");

    }

    /**
     * Método para mostrar menu para criar métodos de pagamento no registro do Cliente
     */
    public static int menuMetodos() {
        System.out.println("1.Cartão de crédito");
        System.out.println("2.MBWAY");
        System.out.println("0.Sair");
        System.out.println("Selecione a opção");
        return Leitor.lerInteiro(0, 2, "", "O número tem de estar entre 0 e 2\n Tente outra vez: ");
    }
}
