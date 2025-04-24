package service;

import cli.BuscarPetUI;
import model.EnderecoPet;
import model.Pet;
import repository.PetFileRepository;
import util.Constantes;
import util.ValidacoesUtils;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class AtualizarPet {


    public static void atualizarPet() {
        List<Pet> x = BuscarPetUI.menu();

        System.out.println("Digite o ID do pet");
        System.out.print(">>> ");
        int id = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
        Constantes.scanner.nextLine();

        List<Pet> pets = x.stream().filter(pet -> pet.getId() == id).toList();

        Pet petSelecionado = pets.getFirst();
        EnderecoPet enderecoPet = petSelecionado.getEnderecoPet();

        if (enderecoPet == null) {
            enderecoPet = new EnderecoPet();
            petSelecionado.setEnderecoPet(enderecoPet);
        }

        try {
            atualizarAtributo("Novo nome",
                    ValidacoesUtils::validarNomeCompleto,
                    petSelecionado::setNome);

            atualizarAtributo("Nova rua",
                    PetService::validarRua_Cidade,
                    enderecoPet::setRua);

            atualizarAtributo("Novo número de casa",
                    numCasa -> ValidacoesUtils.validarNumeroPositivo(Integer.parseInt(numCasa)),
                    enderecoPet::setNumeroCasa);
            petSelecionado.setEnderecoPet(enderecoPet);

            atualizarAtributo("Nova cidade",
                    PetService::validarRua_Cidade,
                    enderecoPet::setCidade);

            atualizarAtributo("Nova idade",
                    idade -> PetService.validarIdade(Integer.parseInt(idade)),
                    petSelecionado::setIdade);

            atualizarAtributo("Novo peso",
                    peso -> PetService.validarPeso(Double.parseDouble(peso)),
                    petSelecionado::setPeso);

            atualizarAtributo("Nova raça",
                    PetService::validarRaca,
                    petSelecionado::setRaca);

            pets.forEach(System.out::println);

            DeletarPet.deletarPet(petSelecionado);
            PetFileRepository.salvarPet(petSelecionado);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }

    }

    private static <T> void atualizarAtributo(String mensagem, Function<String, T> validator, Consumer<T> setter) {
        System.out.print(mensagem + "? (s/n): ");

        String resp = Constantes.scanner.nextLine();

        if (resp.startsWith("s")) {
            System.out.println("Digite " + mensagem.toLowerCase() + ":");
            System.out.print(">>> ");

            String input = Constantes.scanner.nextLine();

            try {
                T valorValidado = validator.apply(input);
                setter.accept(valorValidado);
                System.out.println(mensagem + " atualizado com sucesso!");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println("Algo deu errado." + e.getMessage());
            }
        }
    }


}