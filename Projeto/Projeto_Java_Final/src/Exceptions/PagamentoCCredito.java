package Exceptions;

public class PagamentoCCredito extends PrincipalPagamento{

    public PagamentoCCredito() {
        super("\n---O valor da transação excede o valor limite---");
    }
}
