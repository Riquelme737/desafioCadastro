package service;

import model.EnderecoPet;
import model.Pet;
import model.enums.Sexo;
import model.enums.Tipo;
import util.ValidacoesUtils;

import java.util.List;
import java.util.Scanner;

import static repository.FormularioLeitor.lerPerguntas;

public class CadastrarPet {

    private static final SalvarPet salvarPet = new SalvarPet();

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
                pet.setNome(scanner.nextLine());
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

                String tipoAux = scanner.nextLine().trim().toLowerCase();
                ValidacoesUtils.validarTipoPet(tipoAux);

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

                String sexoAux = String.valueOf(scanner.next());
                ValidacoesUtils.validarSexoPet(sexoAux);

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
                enderecoPet.setNumeroCasa(scanner.nextInt());

                scanner.nextLine();

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


        // PERGUNGA 5 - IDADE
        boolean idadeValida = false;
        while (!idadeValida) {
            try {
                System.out.println(perguntas.get(4));
                System.out.print(">>> ");
                pet.setIdade(scanner.nextInt());
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
                pet.setPeso(scanner.nextDouble());
                pesovalido = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.nextLine();

        // PERGUNTA - RAÇA
        boolean racaValida = false;
        while (!racaValida) {
            try {
                System.out.println(perguntas.get(6));
                System.out.print(">>> ");
                pet.setRaca(scanner.nextLine());
                racaValida = true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        salvarPet.salvarPet(pet);
        System.out.println("O seu pet foi cadastrado com sucesso.");
        System.out.println("------------------------------------------------------------------------------------");

    }
}
