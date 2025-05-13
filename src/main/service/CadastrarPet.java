package service;

import model.EnderecoPet;
import model.Pet;
import model.enums.Sexo;
import model.enums.Tipo;
import repository.PetFileRepository;
import service.validators.*;

import java.util.List;

import static repository.FormularioLeitor.lerPerguntas;

public class CadastrarPet {
    private static final InputHandler input = new InputHandler();

    public static void cadastrarPet() {
        List<String> perguntas = lerPerguntas();

        String nome = input.getInput(perguntas.getFirst(), new NomeValidator());
        Tipo tipo = input.getInput(perguntas.get(1), new TipoValidator());
        Sexo sexo = input.getInput(perguntas.get(2), new GeneroValidator());

        System.out.println(perguntas.get(3));
        Integer numeroCasa = input.getInput("Número da casa: ", new numberHouse());
        String cidade = input.getInput("Cidade: ", new RuaCidadeValidator());
        String rua = input.getInput("Rua: ", new RuaCidadeValidator());
        EnderecoPet enderecoPet = new EnderecoPet(numeroCasa, cidade, rua);

        Integer idade = input.getInput(perguntas.get(4), new IdadeValidator());
        Double peso = input.getInput(perguntas.get(5), new PesoValidator());
        String raca = input.getInput(perguntas.getLast(), new RacaValidator());

        Pet pet = new Pet.Builder()
                .setNome(nome)
                .setTipo(tipo)
                .setSexo(sexo)
                .setEnderecoPet(enderecoPet)
                .setIdade(idade)
                .setPeso(peso)
                .setRaca(raca)
                .build();

        PetFileRepository.salvarPet(pet);
        ListarPets.addAllPets(pet);
    }
}
