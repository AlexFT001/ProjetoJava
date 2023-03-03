import java.io.Serializable;

public class Utilizador implements Serializable {
    protected Cliente cliente;

    public Utilizador(Cliente cliente) {
        this.cliente = cliente;
    }
}
