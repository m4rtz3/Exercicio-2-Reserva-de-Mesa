package reserva;
import java.util.UUID;

public class Cliente {

    private String id;
    private String nome;

    protected Cliente(String nome) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getnome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id
            + ", nome=" + nome
        + "]";
    }
    
}
