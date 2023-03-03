import java.util.InputMismatchException;
import java.util.Scanner;

public class Leitor {

    public static Scanner TECLADO = new Scanner(System.in);

    /**
     * Método que devolve um inteiro inserido pelo utilizador e que restringe o mínimo e o máximo
     */
    public static int lerInteiro(int min, int max, String mensagem,String mensagemErro){

        int inteiro;

        System.out.print(" " + mensagem);
        do {

            inteiro = validacaoInteiro();

            if (inteiro < min || inteiro > max) {

                System.out.print(mensagemErro);

            }

        } while (inteiro < min || inteiro > max);
        return inteiro;
    }

    /**
     * Método que valida que o número seja obrigatoriamente um inteiro
     */
    private static  int validacaoInteiro(){
        int inteiro = -1;
        do {
            try {
                inteiro = TECLADO.nextInt();
            } catch (InputMismatchException erroDeLeitura) {
                System.out.println(" O valor tem de ser um número inteiro \n Insira novamente: ");
            }
            TECLADO.nextLine();
        }while (inteiro == -1);
        return  inteiro;
    }

    /**
     * Método que devolve um double inserido pelo utilizador e que restringe o mínimo e o máximo
     */
    public static double lerDouble(double min, double max, String mensagem){

        double numero;

        System.out.print(" " + mensagem);
        do {

            numero = validacaoDouble();

            if (numero < min || numero > max) {

                System.out.print("Valor Inválido!" +"\n" + "Tem de estar entre " + min + " e " + max + "\n" + "Insira novamente: ");

            }

        } while (numero < min || numero > max);

        return numero;
    }

    /**
     * Método que valida que o número seja obrigatoriamente um double
     */
    private static double validacaoDouble(){
        double numero = 0;
        do {

            try{
                numero = TECLADO.nextDouble();

            }catch (InputMismatchException erroDeLeitura){
                System.out.println("O valor tem de ser um número Double" + "\n" + "Insira novamente: ");
            }
            TECLADO.nextLine();
        }while (numero == 0);

        return numero;
    }

    /**
     * Método que devolve uma String inserida pelo utilizador
     */
    public static String lerNome(String mensagem){

        String nome;
        boolean validacao;

        do {
            System.out.print(" " + mensagem);
            nome = TECLADO.nextLine();

            validacao = validacaoNome(nome);
            if (!validacao) {
                System.out.println("Nome Inválido. Por favor introduza um nome válido!");
            }
        }while (!validacao);

        return nome;
    }

    /**
     * Método que valida que a String inserida pelo utilizador seja um nome
     */
    private static boolean validacaoNome(String nome)
    {
        return nome.matches( "[A-Za-z\séáêã]*" );
    }

    public static void fecharTeclado(){
        TECLADO.close();
    }

}
