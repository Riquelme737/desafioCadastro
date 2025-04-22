package service;

import cli.BuscarPetUI;
import model.EnderecoPet;
import model.Pet;
import repository.PetFileRepository;
import util.Constantes;
import util.ValidacoesUtils;


import java.sql.SQLOutput;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;


public class AtualizarPet {
    public static void atualizarPet() {
        BuscarPetUI.menu();

        List<Pet> pets = BuscarPet.buscarPets();
        System.out.println("Digite o ID do pet");
        System.out.print(">>> ");
        int id = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
        Constantes.scanner.nextLine();

        if (id >= 1 && id <= pets.size()) {
            Pet petSelecionado = pets.get(id - 1);
            EnderecoPet enderecoPet = new EnderecoPet();

            try {
                atualizarAtributo("Novo nome",
                        ValidacoesUtils::validarNomeCompleto,
                        petSelecionado::setNome);

                atualizarAtributo("Nova cidade",
                        PetService::validarRua_Cidade,
                        enderecoPet::setCidade);

                atualizarAtributo("Nova rua",
                        PetService::validarRua_Cidade,
                        enderecoPet::setRua);

                atualizarAtributo("Novo número de casa",
                        input -> ValidacoesUtils.validarNumeroPositivo(Integer.parseInt(input)),
                        enderecoPet::setNumeroCasa);
                petSelecionado.setEnderecoPet(enderecoPet);

                atualizarAtributo("Novo peso",
                        input -> PetService.validarPeso(Double.parseDouble(input)),
                        petSelecionado::setPeso);

                atualizarAtributo("Nova raça",
                        PetService::validarRaca,
                        petSelecionado::setRaca);

                PetFileRepository.salvarPet(petSelecionado);
            } catch (IllegalArgumentException e) {
                System.err.println("Erro: " + e.getMessage());
                return;
            }
        }
    }

    private static <T> void atualizarAtributo(String mensagem, Function<String, T> validador, Consumer<T> setter) {
        System.out.print(mensagem + "? (s/n): ");

        String resp = Constantes.scanner.nextLine();


        if (resp.startsWith("s")) {
            System.out.println("Digite " + mensagem.toLowerCase() + ":");
            System.out.print(">>> ");

            String input = Constantes.scanner.nextLine();

            try {
                T valorValidado = validador.apply(input);
                setter.accept(valorValidado);
                System.out.println(mensagem + " atualizado com sucesso!");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println("Algo deu errado." + e.getMessage());
            }
        }
    }
}