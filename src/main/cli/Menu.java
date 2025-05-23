package cli;

import service.AtualizarPet;
import service.CadastrarPet;
import service.DeletarPet;

import java.util.Scanner;

import static service.ListarPets.listarPets;
import static service.ListarPets.menu;

public class Menu {
    private final static Scanner scanner = new Scanner(System.in);

    public static int rodarMenu() {
        int resp = 0;
        boolean flag = false;

        while (!flag) {

            System.out.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
            System.out.println("|[1] Cadastrar um novo pet\t\t\t\t\t\t\t|");
            System.out.println("|[2] Alterar dados do pet cadastrado\t\t\t\t|");
            System.out.println("|[3] Deletar um pet cadastrado\t\t\t\t\t\t|");
            System.out.println("|[4] Listar todos os pets cadastrado\t\t\t\t|");
            System.out.println("|[5] Listar por algum critério (idade, nome, raça)\t|");
            System.out.println("|[6] Sair\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
            System.out.print("---> ");

            if (scanner.hasNextInt()) {
                resp = scanner.nextInt();
                if (resp >= 1 && resp <= 6) {
                    flag = true;
                }
            } else {
                System.err.println("Por favor, insira um número");
                scanner.nextLine();
            }
        }
        return resp;
    }

    public static void menuPrincipal(){
        boolean flag = false;
        while (!flag) {
            switch (rodarMenu()) {
                case 1:
                    CadastrarPet.cadastrarPet();
                    break;
                case 2:
                    AtualizarPet.atualizarPet();
                    break;
                case 3:
                    DeletarPet.deletarPet();
                    break;
                case 4:
                    listarPets();
                    break;
                case 5:
                    int criterion = menu();
                    listarPets(criterion);
                    break;
                case 6:
                    System.out.println("Obrigado!");
                    flag = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + rodarMenu());
            }
        }
    }
}
