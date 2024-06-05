package reserva;

public class Reserva implements Pagamento {
    private Cliente cliente;
    private boolean pagamentoAVista;

    // construtor
    public Reserva(Cliente cliente, boolean pagamentoAVista) {
        this.cliente = cliente;
        this.pagamentoAVista = pagamentoAVista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPagamentoAVista() {
        return pagamentoAVista;
    }

    public void setPagamentoAVista(boolean pagamentoAVista) {
        this.pagamentoAVista = pagamentoAVista;
    }

    @Override
    public String toString() {

        String tipoCliente = "";

        if (cliente instanceof PessoaFisica) {
            tipoCliente = "Pessoa Física";
        } else if (cliente instanceof PessoaJuridica) {
            tipoCliente = "Pessoa Jurídica";
        }

        String formaPagamento = pagamentoAVista ? "à vista" : "parcelado";

        return "Reserva [tipo do cliente=" + tipoCliente
                + ", nome=" + cliente.getnome()
                + ", forma de pagamento=" + formaPagamento
                + "]";
    }

    @Override
    public double calcularPagamento() {

        double valorBase = 3200.00;

        if (pagamentoAVista) {
            return valorBase * 0.9;
        } else {
            return valorBase;
        }
    }

}
