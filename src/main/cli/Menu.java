package cli;

import java.util.Scanner;

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
            System.out.println("|[5]Listar por algum critério (idade, nome, raça)\t|");
            System.out.println("|[6] Sair\t\t\t\t\t\t\t\t\t\t\t|");
            System.out.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
            System.out.print("---> ");

            if (scanner.hasNextInt()) {
                resp = scanner.nextInt();

                if (resp >= 1 && resp <= 6) {
                    flag = true;
                } else {
                    System.err.println("Por favor, insira um número");
                    scanner.nextLine();
                }
            }
        }

        return resp;
    }
}
