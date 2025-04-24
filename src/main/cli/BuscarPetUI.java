package cli;

import model.Pet;
import model.enums.Tipo;
import service.PetService;
import util.ValidacoesUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static service.BuscarPet.*;

public class BuscarPetUI {
    private static final Scanner scanner = new Scanner(System.in);

    private static void opcoesCriterios() {
        System.out.println("[1] Nome ou sobrenome");
        System.out.println("[2] Sexo");
        System.out.println("[3] Idade");
        System.out.println("[4] Peso");
        System.out.println("[5] Raça");
        System.out.println("[6] Endereço");
        System.out.println("[0] Nenhuma das opções");
        System.out.print(">>> ");
    }

    public static List<Pet> menu() {
        System.out.println("Cachorro ou Gato?");
        System.out.print(">>> ");
        String tipoCriterio = "";
        try {
            tipoCriterio = PetService.validarTipoPet(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());

        }

        Tipo tipo;
        if (tipoCriterio.equalsIgnoreCase("cachorro")) {
            tipo = Tipo.CACHORRO;
        } else if (tipoCriterio.equalsIgnoreCase("gato")) {
            tipo = Tipo.GATO;
        } else {
            tipo = null;
        }

        List<Pet> listaPetFiltrada = buscarPets().stream()
                .filter(pet -> pet.getTipo() == tipo)
                .toList();

        for (int i = 0; i < 2; i++) {
            try {
                opcoesCriterios();
                int criterio = ValidacoesUtils.validarNumeroPositivo(scanner.nextInt());
                scanner.nextLine();
                listaPetFiltrada = aplicarFiltro(listaPetFiltrada, criterio);
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.err.println("Digite apenas números, por favor.");
                scanner.nextLine();
            }
        }

        atribuirIdsParaListagem(listaPetFiltrada);
        listaPetFiltrada.forEach(System.out::println);
        return listaPetFiltrada;
    }
}
