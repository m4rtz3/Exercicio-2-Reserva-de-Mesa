package reserva;
import java.util.Date;

public class PessoaFisica extends Cliente {

    private String cpf;
    private Date birthdate;

    public PessoaFisica(String cpf, String nome) {
        super(nome);
        this.cpf = cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Pessoa Física [cpf=" + cpf
            + ", birthdate=" + birthdate
        + "]";
    }

}