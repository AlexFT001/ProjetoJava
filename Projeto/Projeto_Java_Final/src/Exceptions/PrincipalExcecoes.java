package Exceptions;

public class PrincipalExcecoes extends  Exception{

    public PrincipalExcecoes(String menssagem){
        super(menssagem);
    }

    public void erroImprimir(){
        System.out.println(this.getMessage());
    }
}
