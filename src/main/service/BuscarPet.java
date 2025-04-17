package service;

import model.EnderecoPet;
import model.Pet;
import model.enums.Sexo;
import model.enums.Tipo;
import util.ValidacoesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarPet {
    private final static String PETS_CADASTRADOS = "src/resources/petsCadastrados";
    private final static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println("Cachorro ou Gato?");
        System.out.print(">>> ");
        String tipoCriterio = PetService.validarTipoPet(scanner.nextLine());

        opcoesCriterios();
        int criterioEscolhido1 = ValidacoesUtils.validarNumeroPositivo(scanner.nextInt());

        opcoesCriterios();
        int criterioEscolhido2 = ValidacoesUtils.validarNumeroPositivo(scanner.nextInt());

        Tipo generoPet;
        if (tipoCriterio.equalsIgnoreCase("cachorro")) {
            generoPet = Tipo.CACHORRO;
        } else if (tipoCriterio.equalsIgnoreCase("gato")) {
            generoPet = Tipo.GATO;
        } else {
            System.err.println("Opção inválida! Apenas cachorro ou gato!");
            return;
        }

        List<Pet> petList = buscarPets().stream()
                .filter(pet -> pet.getTipo() == generoPet)
                .toList();

        if (criterioEscolhido1 == 1 || criterioEscolhido2 == 1) {
            System.out.println("Digite o nome ou sobrenome do pet");
            System.out.print(">>> ");
            scanner.nextLine();
            String nomeSugerido = scanner.nextLine().toLowerCase().trim();

            petList = filtarPorNome(nomeSugerido, petList);
        }

        if (criterioEscolhido1 == 2 || criterioEscolhido2 == 2) {
            System.out.println("Digite o gênero do pet");
            System.out.print(">>> ");
            scanner.nextLine();
            String generoSugerido = scanner.nextLine().toLowerCase().trim();

            petList = filtarPorGenero(generoSugerido, petList);
        }

        if (criterioEscolhido1 == 3 || criterioEscolhido2 == 3) {
            System.out.println("Digite a idade do pet");
            System.out.print(">>> ");
            int idadeSugerida = scanner.nextInt();

            petList = filtarPorIdade(idadeSugerida, petList);
        }

        if (criterioEscolhido1 == 4 || criterioEscolhido2 == 4) {
            System.out.println("Digite o peso do pet");
            System.out.print(">>> ");
            scanner.nextLine();
            double peso = scanner.nextDouble();

            petList = filtrarPorPeso(peso, petList);
        }

        if (criterioEscolhido1 == 5 || criterioEscolhido2 == 5) {
            System.out.println("Digite a raça do pet");
            System.out.print(">>> ");
            scanner.nextLine();
            String raca = scanner.nextLine().toLowerCase().trim();

            petList = filtrarPorRaca(raca, petList);
        }

        if (criterioEscolhido1 == 6 || criterioEscolhido2 == 6) {
            System.out.println("[1] Rua");
            System.out.println("[2] Numero da casa");
            System.out.println("[3] cidade");
            System.out.print(">>> ");
            scanner.nextLine();
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                System.out.println("Nome da rua");
                System.out.print(">>> ");
                scanner.nextLine();
                String rua = scanner.nextLine();
                petList = filtrarPorRua(rua, petList);
            }

            if (escolha == 2) {
                System.out.println("Número da casa");
                System.out.print(">>> ");
                scanner.nextLine();
                int numeroCasa = scanner.nextInt();
                petList = filtrarPorNumeroCasa(numeroCasa, petList);
            }

            if (escolha == 3) {
                System.out.println("Nome da cidade");
                System.out.print(">>> ");
                scanner.nextLine();
                String cidade = scanner.nextLine();
                petList = filtrarPorCidade(cidade, petList);
            }
        }

        for (Pet pet : petList) {
            System.out.println(pet);
        }
    }

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


    public static List<Pet> buscarPets() {
        File[] arquivo = new File(PETS_CADASTRADOS).listFiles(nome -> nome.getName().endsWith(".TXT"));
        List<Pet> petList = null;

        if (arquivo != null) {
            petList = new ArrayList<>();
            for (File file : arquivo) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String nome = br.readLine().split(" - ")[1];
                    String especie = br.readLine().split(" - ")[1];
                    String genero = br.readLine().split(" - ")[1];
                    String endereco = br.readLine().split(" - ")[1];
                    String idade = br.readLine().split(" - ")[1];
                    String peso = br.readLine().split(" - ")[1];
                    String raca = br.readLine().split(" - ")[1];

                    EnderecoPet enderecoPet = new EnderecoPet();
                    String[] enderecoPartes = endereco.split(", ");
                    enderecoPet.setRua(enderecoPartes[0].trim());
                    enderecoPet.setNumeroCasa(Integer.valueOf(enderecoPartes[1].trim()));
                    enderecoPet.setCidade(enderecoPartes[2].trim());

                    Pet pet = new Pet();
                    pet.setEnderecoPet(enderecoPet);
                    pet.setNome(nome);
                    pet.setTipo(Tipo.valueOf(especie.toUpperCase()));
                    pet.setSexo(Sexo.valueOf(genero.toUpperCase()));
                    pet.setIdade(Integer.valueOf(idade.split(" ")[0]));
                    pet.setPeso(Double.valueOf(peso.split("kg")[0]));
                    pet.setRaca(raca);

                    petList.add(pet);
                } catch (IOException e) {
                    System.err.println("Erro ao ler arquivo" + e);
                }
            }
        }
        return petList;
    }

    public static List<Pet> filtarPorNome(String nomeSugerido, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getNome().toLowerCase().contains(nomeSugerido))
                .toList();
    }

    public static List<Pet> filtarPorGenero(String generoSugerido, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getSexo().getNome().equalsIgnoreCase(generoSugerido))
                .toList();
    }

    public static List<Pet> filtarPorIdade(int idadeSugerida, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getIdade() == idadeSugerida)
                .toList();
    }

    public static List<Pet> filtrarPorPeso(double pesoSugerido, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getPeso() == pesoSugerido)
                .toList();
    }

    public static List<Pet> filtrarPorRaca(String racaSugerida, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getRaca().equalsIgnoreCase(racaSugerida))
                .toList();
    }

    public static List<Pet> filtrarPorRua(String ruaSurgerida, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getEnderecoPet().getRua().contains(ruaSurgerida))
                .toList();
    }

    public static List<Pet> filtrarPorNumeroCasa(int numeroSugerido, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getEnderecoPet().getNumeroCasa() == numeroSugerido)
                .toList();
    }

    public static List<Pet> filtrarPorCidade(String cidadeSugerida, List<Pet> petList) {
        return petList.stream()
                .filter(pet -> pet.getEnderecoPet().getCidade().contains(cidadeSugerida))
                .toList();
    }


}
