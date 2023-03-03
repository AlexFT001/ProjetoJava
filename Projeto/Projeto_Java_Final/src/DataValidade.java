import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataValidade implements Serializable {
    protected int mes;
    protected int ano;

    /**
     * Construtor para criar a data de validade dos cartões
     */
    public DataValidade() {
        GregorianCalendar atual = new GregorianCalendar();
        this.mes = atual.get(Calendar.MONTH)+1;
        this.ano = atual.get(Calendar.YEAR)+4;
    }


    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
}
