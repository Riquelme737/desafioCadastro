package service;

import model.Pet;
import model.enums.Sexo;
import model.enums.Tipo;
import util.Constantes;
import util.ValidacoesUtils;

import java.util.List;

public class ListarPets {
    private static List<Pet> allPets = BuscarPet.buscarPets();

    public static int menu() {
        System.out.println("[1] Nome ou sobrenome");
        System.out.println("[2] Tipo");
        System.out.println("[3] Gênero");
        System.out.println("[4] Idade");
        System.out.println("[5] Peso");
        System.out.println("[6] Raça");
        return ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
    }

    public static void listarPets() {
        allPets.forEach(System.out::println);
    }

    public static void listarPets(int criterio) {
        Constantes.scanner.nextLine();

        try {
            switch (criterio) {
                case 1:
                    System.out.println("Qual nome?");
                    System.out.print(">>> ");
                    String nome = Constantes.scanner.nextLine().trim().toLowerCase();

                    for (Pet pet : allPets) {
                        if (pet.getNome().trim().toLowerCase().contains(nome)) {
                            System.out.println(pet);
                        }
                    }
                    break;

                case 2:
                    System.out.println("Qual tipo? (Cachorro ou Gato)");
                    System.out.print(">>> ");
                    String tipoAux = PetService.validarTipoPet(Constantes.scanner.nextLine().trim().toUpperCase());

                    Tipo tipo;
                    if (tipoAux.equals("CACHORRO") || tipoAux.startsWith("C")) {
                        tipo = Tipo.CACHORRO;
                    } else if (tipoAux.equals("GATO") || tipoAux.startsWith("G")) {
                        tipo = Tipo.GATO;
                    } else {
                        tipo = null;
                    }

                    for (Pet pet : allPets) {
                        if (pet.getTipo().equals(tipo)) {
                            System.out.println(pet);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Qual gênero? (Macho ou Femea)");
                    System.out.print(">>> ");
                    String generoAux = PetService.validarSexoPet(Constantes.scanner.nextLine().trim().toUpperCase());

                    Sexo sexo;
                    if (generoAux.equals("MACHO") || generoAux.equals("M")) {
                        sexo = Sexo.MACHO;
                    } else if (generoAux.equals("FEMEA") || generoAux.equals("F")) {
                        sexo = Sexo.FEMEA;
                    } else {
                        sexo = null;
                    }

                    for (Pet pet : allPets) {
                        if (pet.getSexo().equals(sexo)) {
                            System.out.println(pet);
                        }
                    }
                    break;


                case 4:
                    System.out.println("Qual idade?");
                    System.out.print(">>> ");
                    Integer idade = PetService.validarIdade(Constantes.scanner.nextInt());

                    for (Pet pet : allPets) {
                        if (pet.getIdade().equals(idade)) {
                            System.out.println(pet);
                        }
                    }
                    break;


                case 5:
                    System.out.println("Qual peso?");
                    System.out.print(">>> ");
                    double peso = PetService.validarPeso(Constantes.scanner.nextDouble());

                    for (Pet pet : allPets) {
                        if (pet.getPeso() == peso) {
                            System.out.println(pet);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Qual raça?");
                    System.out.print(">>> ");
                    String raca = Constantes.scanner.nextLine().trim().toLowerCase();

                    for (Pet pet : allPets) {
                        if (pet.getRaca().trim().toLowerCase().contains(raca)) {
                            System.out.println(pet);
                        }
                    }
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}

