import java.io.*;

public class GerirFicheiros {

    /**
     * Método para gravar dados do establecimento em ficheiro
     */
    public static void gravarFicheiro(Estabelecimento establecimento){
        try{
            ObjectOutputStream gravar = new ObjectOutputStream(new FileOutputStream("Establecimento.dat"));
            gravar.writeObject(establecimento);
            gravar.close();
            System.out.println("\nFicheiro gravado com sucesso!");
        }catch (IOException erro){
            System.out.println("Erro ao gravar ficheiro");
        }
    }

    /**
     * Método para ler dados do establecimento de um ficheiro
     */
    public static Estabelecimento lerFicheiro(){
        try{
            ObjectInputStream ler = new ObjectInputStream(new FileInputStream("Establecimento.dat"));
            Estabelecimento establecimentoLido = (Estabelecimento) ler.readObject();
            ler.close();
            return establecimentoLido;
        } catch (IOException erro) {
            System.out.println("Erro ao ler o ficheiro");
        } catch (ClassNotFoundException erro) {
            System.out.println("Erro ao tentar ler o Establecimento");
        }
        return null;
    }
}
