package Exceptions;

public class PagamentoCDebito extends PrincipalPagamento{
    public PagamentoCDebito() {
        super("\n---O saldo da conta é insufciente---");
    }
}
