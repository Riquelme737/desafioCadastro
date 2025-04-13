package service;

import model.EnderecoPet;
import model.Pet;
import model.enums.Sexo;
import model.enums.Tipo;
import repository.PetFileRepository;

import java.util.List;
import java.util.Scanner;

import static repository.FormularioLeitor.lerPerguntas;

public class CadastrarPet {

    private static final PetFileRepository PET_FILE_REPOSITORY = new PetFileRepository();

    public static void cadastrarPet() {
        Scanner scanner = new Scanner(System.in);
        Pet pet = new Pet();
        List<String> perguntas = lerPerguntas();
        EnderecoPet enderecoPet = new EnderecoPet();


        // PERGUNTA 1 - NOME E SOBRENOME
        boolean nomeValido = false;
        while (!nomeValido) {
            try {
                System.out.println(perguntas.getFirst());
                System.out.print(">>> ");
                String nomeAux = scanner.nextLine();

                if (nomeAux.isBlank()) {
                    pet.setNome(pet.getNAO_INFORMADO());
                    nomeValido = true;
                } else {
                    pet.setNome(nomeAux);
                }
                nomeValido = true;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // PERGUNTA 2 - CACHORRO OU GATO
        boolean tipoValido = false;
        while (!tipoValido) {
            try {
                System.out.println(perguntas.get(1));
                System.out.print(">>> ");

                String tipoAux = scanner.nextLine().toLowerCase();
                PetService.validarTipoPet(tipoAux);

                if (tipoAux.startsWith("c")) {
                    pet.setTipo(Tipo.CACHORRO);
                } else {
                    pet.setTipo(Tipo.GATO);
                }

                tipoValido = true;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // PERGUNTA 3 - SEXO DO ANIMAL
        boolean sexoValido = false;
        while (!sexoValido) {
            try {
                System.out.println(perguntas.get(2));
                System.out.print(">>> ");

                String sexoAux = scanner.nextLine().toLowerCase();
                PetService.validarSexoPet(sexoAux);

                if (sexoAux.startsWith("m")) {
                    pet.setSexo(Sexo.MACHO);
                } else {
                    pet.setSexo(Sexo.FEMEA);
                }
                sexoValido = true;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // PERGUNTA 4 - ENDEREÇO
        boolean enderecoValido = false;
        while (!enderecoValido) {
            try {
                System.out.println(perguntas.get(3));

                System.out.print("> ");
                System.out.print("Numero da Casa: ");
                String numeroCasaString = scanner.nextLine();

                if (numeroCasaString.isBlank()) {
                    enderecoPet.setNumeroCasa(null);
                    enderecoValido = true;
                } else {
                    enderecoPet.setNumeroCasa(Integer.valueOf(numeroCasaString));
                }

                System.out.print("> ");
                System.out.print("Cidade: ");
                enderecoPet.setCidade(scanner.nextLine());

                System.out.print("> ");
                System.out.print("Rua: ");
                enderecoPet.setRua(scanner.nextLine());

                pet.setEnderecoPet(enderecoPet);
                enderecoValido = true;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // PERGUNTA 5 - IDADE
        boolean idadeValida = false;
        while (!idadeValida) {
            try {
                System.out.println(perguntas.get(4));
                System.out.print(">>> ");
                String idadeString = scanner.nextLine();

                if (idadeString.isBlank()){
                    pet.setIdade(null);
                    idadeValida = true;
                } else {
                    pet.setIdade(Integer.valueOf(idadeString));
                }
                idadeValida = true;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // PERGUNTA - PESO
        boolean pesovalido = false;
        while (!pesovalido) {
            try {
                System.out.println(perguntas.get(5));
                System.out.print(">>> ");
                String pesoString = scanner.nextLine();

                if (pesoString.isBlank()) {
                    pet.setPeso(null);
                    pesovalido = true;
                } else {
                    pet.setPeso(Double.parseDouble(pesoString));
                }
                pesovalido = true;

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }


        // PERGUNTA - RAÇA
        boolean racaValida = false;
        while (!racaValida) {
            try {
                System.out.println(perguntas.get(6));
                System.out.print(">>> ");
                String racaAux = scanner.nextLine();
                if (racaAux.isBlank()){
                    pet.setRaca(pet.getNAO_INFORMADO());
                    racaValida = true;
                } else {
                    pet.setRaca(racaAux);
                }
                racaValida = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        PET_FILE_REPOSITORY.salvarPet(pet);
        System.out.println("O seu pet foi cadastrado com sucesso.");
        System.out.println("------------------------------------------------------------------------------------");

    }
}
