public class Numerario extends MetodoPagamento {

    double valorPago;

    /**
     * Construtor para criar Númerário
     */
    public Numerario(double valorPago){
        super();
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return " Numerario";
    }
}
