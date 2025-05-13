package service;

import exception.ValidationException;
import model.EnderecoPet;
import model.Pet;
import repository.PetFileRepository;
import util.Constantes;
import util.ValidacoesUtils;

import java.util.List;
import java.util.function.Consumer;

import static service.ListarPets.listarPets;
import static service.ListarPets.menu;


public class AtualizarPet {
    public static void atualizarPet(){
        System.out.println("[1] - Listar todos os Pets");
        System.out.println("[2] - Escolher algum critério");
        int opcao = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());

        if (opcao == 1) {
            ListarPets.listarPets();
        } else if (opcao == 2) {
            int criterion = menu();
            listarPets(criterion);
        } else {
            System.err.println("Erro: Opção válida é 1 ou 2");
            return;
        }

        List<Pet> allPets = BuscarPet.buscarPets();

        System.out.println("Digite o ID do pet");
        System.out.print(">>> ");
        int id = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
        Constantes.scanner.nextLine();

        List<Pet> filteredPets = allPets.stream().filter(pet -> pet.getId() == id).toList();

        Pet petSelecionado = filteredPets.getFirst();
        System.out.println(petSelecionado);

        EnderecoPet existente = petSelecionado.getEnderecoPet();
        final EnderecoPet enderecoParaAtualizar;
        if (existente == null) {
            enderecoParaAtualizar = new EnderecoPet();
            petSelecionado.setEnderecoPet(enderecoParaAtualizar);
        } else {
            enderecoParaAtualizar = existente;
        }

        try {
            DeletarPet.deletarPet(petSelecionado);

            atualizarAtributo("Novo nome", petSelecionado::setNome);
            atualizarAtributo("Nova cidade", enderecoParaAtualizar::setCidade);
            atualizarAtributo("Nova rua", enderecoParaAtualizar::setRua);
            atualizarAtributo("Novo número de casa", numeroCasa -> enderecoParaAtualizar.setNumeroCasa(Integer.valueOf(numeroCasa)));
            atualizarAtributo("Nova idade", idade -> petSelecionado.setIdade(Integer.valueOf(idade)));
            atualizarAtributo("Novo peso", peso -> petSelecionado.setPeso(Double.valueOf(peso)));
            atualizarAtributo("Nova raça", petSelecionado::setRaca);
            filteredPets.forEach(System.out::println);

            PetFileRepository.salvarPet(petSelecionado);
            ListarPets.setAllPets(BuscarPet.buscarPets());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }

    }

    private static <T> void atualizarAtributo(String mensagem, Consumer<String> setter){
        System.out.print(mensagem + "? (s/n): ");

        String resp = Constantes.scanner.nextLine();

        if (resp.startsWith("s")) {
            System.out.println("Digite " + mensagem.toLowerCase() + ":");
            System.out.print(">>> ");

            String input = Constantes.scanner.nextLine();

            try {
                setter.accept(input);
                System.out.println(mensagem + " atualizado com sucesso!");
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println("Algo deu errado." + e.getMessage());
            }
        }
    }


}