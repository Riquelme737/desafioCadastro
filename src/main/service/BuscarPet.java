package service;

import exception.ValidationException;
import model.EnderecoPet;
import model.Pet;
import model.enums.Sexo;
import model.enums.Tipo;
import util.Constantes;
import util.ValidacoesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class BuscarPet {

    public static List<Pet> buscarPets() {
        File[] arquivo = new File(Constantes.PETS_CADASTRADOS).listFiles(nome -> nome.getName().endsWith(".TXT"));
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
                    Pet pet = new Pet();

                    String[] enderecoPartes = endereco.split(", ");
                    enderecoPet.setRua(enderecoPartes[0].trim());
                    enderecoPet.setNumeroCasa(!enderecoPartes[1].equalsIgnoreCase("Não informado") ? Integer.parseInt(enderecoPartes[1]) : -1);
                    enderecoPet.setCidade(enderecoPartes[2]);
                    pet.setEnderecoPet(enderecoPet);
                    pet.setNome(nome);
                    pet.setTipo(Tipo.valueOf(especie.toUpperCase()));
                    pet.setSexo(Sexo.valueOf(genero.toUpperCase()));

                    pet.setIdade(!idade.equalsIgnoreCase("Não informado") ? Integer.parseInt(idade.split(" ")[0]) : -1);
                    pet.setPeso(!peso.equalsIgnoreCase("Não informado") ? Double.parseDouble(peso.split("kg")[0]) : -1);
                    pet.setRaca(raca);

                    petList.add(pet);
                } catch (IOException e) {
                    System.err.println("Erro ao ler arquivo" + e);
                }
            }
        }

        atribuirIdsParaListagem(petList);
        return petList;
    }

    public static List<Pet> aplicarFiltro(List<Pet> petList, int criterio) throws ValidationException {
        Constantes.scanner.nextLine();
        switch (criterio) {
            case 1:
                System.out.println("Digite o nome ou sobrenome do pet");
                System.out.print(">>> ");
                String nomeFiltro = Constantes.scanner.nextLine().trim().toLowerCase();
                return petList.stream()
                        .filter(pet -> pet.getNome().toLowerCase().trim().contains(nomeFiltro))
                        .toList();

            case 2:
                System.out.println("Digite o sexo do pet");
                System.out.print(">>> ");
                Sexo genero = ValidacoesUtils.validarSexoPet(Constantes.scanner.nextLine());
                return petList.stream()
                        .filter(pet -> pet.getSexo().equals(genero))
                        .toList();

            case 3:
                System.out.println("Digite a idade do pet");
                System.out.print(">>> ");
                try {
                    int idadeFiltro = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
                    return petList.stream()
                            .filter(pet -> pet.getIdade() == idadeFiltro)
                            .toList();
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.err.println("Erro: " + e.getMessage());
                    Constantes.scanner.nextLine();
                    return petList;
                }

            case 4:
                System.out.println("Digite o peso do pet");
                System.out.print(">>> ");
                try {
                    double peso = ValidacoesUtils.validarPeso(Constantes.scanner.nextDouble());
                    return petList.stream()
                            .filter(pet -> pet.getPeso() == peso)
                            .toList();
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.err.println("Erro: " + e.getMessage());
                    Constantes.scanner.nextLine();
                    return petList;
                }
            case 5:
                System.out.println("Digite a raça do pet");
                System.out.print(">>> ");
                String raca = ValidacoesUtils.validarRaca(Constantes.scanner.nextLine());
                return petList.stream()
                        .filter(pet -> pet.getRaca().equalsIgnoreCase(raca))
                        .toList();

            case 6:
                System.out.println("[1] Rua");
                System.out.println("[2] Numero da casa");
                System.out.println("[3] Cidade");
                System.out.print(">>> ");
                try {
                    int escolha = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
                    Constantes.scanner.nextLine();
                    if (escolha == 1) {
                        System.out.println("Nome da rua");
                        System.out.print(">>> ");
                        String rua = ValidacoesUtils.validarRua_Cidade(Constantes.scanner.nextLine().trim());
                        return petList.stream()
                                .filter(pet -> pet.getEnderecoPet().getRua().trim().contains(rua))
                                .toList();

                    } else if (escolha == 2) {
                        System.out.println("Número da casa");
                        System.out.print(">>> ");
                        int numeroCasa = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
                        return petList.stream()
                                .filter(pet -> pet.getEnderecoPet().getNumeroCasa() == numeroCasa)
                                .toList();

                    } else if (escolha == 3) {
                        System.out.println("Nome da cidade");
                        System.out.print(">>> ");
                        String cidade = ValidacoesUtils.validarRua_Cidade(Constantes.scanner.nextLine().trim());
                        return petList.stream()
                                .filter(pet -> pet.getEnderecoPet().getCidade().contains(cidade))
                                .toList();

                    }
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.err.println("Erro: " + e.getMessage());
                    Constantes.scanner.nextLine();
                    return petList;
                }
            default:
                return petList;
        }
    }

    public static void atribuirIdsParaListagem(List<Pet> pets) {
        Pet.resetProximoId();

        for(Pet pet : pets) {
            pet.atribuirProximoId();
        }
    }
}
