import reserva.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MAX_RESERVAS = 6;
    private static List<Reserva> reservas = new ArrayList<>();
    private static List<Reserva> listaEspera = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = 0;

        String menu = "Restaurante URUGUAIO\n"
                + "1. Reservar Mesa\n"
                + "2. Pesquisar reserva\n"
                + "3. Imprimir reservas\n"
                + "4. Imprimir lista de espera\n"
                + "5. Cancelar reserva\n"
                + "6. Sair";

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {
                case 1:
                    Reserva novaReserva = criarReserva();
                    reservarMesa(novaReserva);
                    break;
                case 2:
                    pesquisarReserva();
                    break;
                case 3:
                    imprimirReservas();
                    break;
                case 4:
                    imprimirListaEspera();
                    break;
                case 5:
                    cancelarReserva();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Encerrando o programa.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (opcao != 6);
    }

    private static Reserva criarReserva() {
        String nome = JOptionPane.showInputDialog("Qual o nome do cliente?");
        String tpCliente = JOptionPane.showInputDialog("Qual o tipo de cliente? (1 - Física, 2 - Jurídica)");
        Cliente cliente;

        if (tpCliente.equalsIgnoreCase("1")) {
            String cpf = JOptionPane.showInputDialog("Qual o CPF do cliente?");
            cliente = new PessoaFisica(cpf, nome);
        } else {
            String cnpj = JOptionPane.showInputDialog("Qual o CNPJ do cliente?");
            cliente = new PessoaJuridica(cnpj, nome);
        }

        String formaPagamento = JOptionPane.showInputDialog("Forma de pagamento (1 - À vista, 2 - Parcelado): ");
        boolean pagamentoAVista = formaPagamento.equals("1");

        return new Reserva(cliente, pagamentoAVista);
    }

    private static void reservarMesa(Reserva reserva) {
        if (reservas.size() < MAX_RESERVAS) {
            reservas.add(reserva);
            System.out.println("Reserva realizada com sucesso!");
        } else {
            listaEspera.add(reserva);
            System.out.println("Reserva excedida. Cliente adicionado à lista de espera.");
        }
    }

    private static void pesquisarReserva() {
        String cpfCnpj = JOptionPane.showInputDialog("Informe o CPF ou CNPJ do cliente:");
        boolean encontrouReserva = false;
        boolean naListaEspera = false;
        Reserva reservaEncontrada = null;

        for (Reserva reserva : reservas) {
            Cliente cliente = reserva.getCliente();

            if (cliente instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente;
                if (pf.getCpf().equals(cpfCnpj)) {
                    encontrouReserva = true;
                    reservaEncontrada = reserva;
                    break;
                }
            } else if (cliente instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente;
                if (pj.getCnpj().equals(cpfCnpj)) {
                    encontrouReserva = true;
                    reservaEncontrada = reserva;
                    break;
                }
            }
        }

        if (!encontrouReserva) {
            for (Reserva reserva : listaEspera) {
                Cliente cliente = reserva.getCliente();

                if (cliente instanceof PessoaFisica) {
                    PessoaFisica pf = (PessoaFisica) cliente;
                    if (pf.getCpf().equals(cpfCnpj)) {
                        encontrouReserva = true;
                        naListaEspera = true;
                        reservaEncontrada = reserva;
                        break;
                    }
                } else if (cliente instanceof PessoaJuridica) {
                    PessoaJuridica pj = (PessoaJuridica) cliente;
                    if (pj.getCnpj().equals(cpfCnpj)) {
                        encontrouReserva = true;
                        naListaEspera = true;
                        reservaEncontrada = reserva;
                        break;
                    }
                }
            }
        }

        if (encontrouReserva && reservaEncontrada != null) {
            String message = reservaEncontrada.toString();
            if (naListaEspera) {
                message += "\nCliente está na lista de espera.";
            } else {
                message += "\nCliente tem reserva confirmada.";
            }
            JOptionPane.showMessageDialog(null, message);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não tem reserva para o jantar.");
        }
    }

    private static void imprimirReservas() {
        StringBuilder sb = new StringBuilder("Reservas:\n");
        int count = 0;

        for (Reserva reserva : reservas) {
            sb.append(reserva.toString()).append("\n");
            count++;
            if (count == MAX_RESERVAS) break;
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void imprimirListaEspera() {
        StringBuilder sb = new StringBuilder("Lista de Espera:\n");
        int posicao = 1;

        for (Reserva reserva : listaEspera) {
            sb.append("Posição ").append(posicao).append(": ").append(reserva.toString()).append("\n");
            posicao++;
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void cancelarReserva() {
        String cpfCnpj = JOptionPane.showInputDialog("Informe o CPF ou CNPJ do cliente:");
        boolean encontrouReserva = false;
        Reserva reservaRemovida = null;

        for (Reserva reserva : reservas) {
            Cliente cliente = reserva.getCliente();

            if (cliente instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) cliente;
                if (pf.getCpf().equals(cpfCnpj)) {
                    encontrouReserva = true;
                    reservaRemovida = reserva;
                    break;
                }
            } else if (cliente instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) cliente;
                if (pj.getCnpj().equals(cpfCnpj)) {
                    encontrouReserva = true;
                    reservaRemovida = reserva;
                    break;
                }
            }
        }

        if (encontrouReserva && reservaRemovida != null) {
            reservas.remove(reservaRemovida);
            JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso!");

            if (!listaEspera.isEmpty()) {
                Reserva reservaListaEspera = listaEspera.remove(0);
                reservas.add(reservaListaEspera);
                JOptionPane.showMessageDialog(null, "Cliente da lista de espera movido para reservas.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado na lista de reservas.");
        }
    }
}