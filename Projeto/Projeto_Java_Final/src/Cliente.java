public class Cliente extends Identificador{

    private final int numeroNif;
    private  ContaBancaria contaBancaria;

    /**
     * Construtor para criar o Cliente
     */
    public Cliente(String nome, int numeroNif) {
        super(nome);
        this.numeroNif = numeroNif;
        this.contaBancaria = null;
    }

    public int getNumeroNif() {
        return numeroNif;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    /**
     * Método que atribui uma Conta bancária ao Cliente
     */
    public void setContaBancaria(ContaBancaria contaBancaria) {
        if(contaBancaria == null||this.contaBancaria == contaBancaria){
            return;
        }
        this.contaBancaria = contaBancaria;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                ", NIF: " + numeroNif +
                ", Conta Bancaria: " + contaBancaria.getNumeroContaBancariaPessoal();
    }
}
