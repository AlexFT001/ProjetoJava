import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data extends DataValidade {
    private final int dia;
    private final int hora;
    private final int minuto;

    /**
     * Construtor para criar a data da transação
     */
    public Data() {
        super();
        GregorianCalendar atual = new GregorianCalendar();
        this.ano = atual.get(Calendar.YEAR);
        this.dia = atual.get(Calendar.DAY_OF_MONTH);
        this.hora = atual.get(Calendar.HOUR_OF_DAY);
        this.minuto = atual.get(Calendar.MINUTE);

    }

    @Override
    public String toString() {
        return dia+ "/" + mes + "/" + ano + " " + hora + ":" + minuto;
    }



}


