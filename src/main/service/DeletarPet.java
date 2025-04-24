package service;

import cli.BuscarPetUI;
import model.Pet;
import util.Constantes;
import util.ValidacoesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DeletarPet {
    private static List<Pet> pets = BuscarPet.buscarPets();

    public static void deletarPet() {
        BuscarPetUI.menu();

        System.out.println("Qual id?");
        System.out.print(">>> ");
        int id = ValidacoesUtils.validarNumeroPositivo(Constantes.scanner.nextInt());
        Constantes.scanner.nextLine();

        Pet petParaExcluir = null;
        for (Pet pet : pets) {
            if (pet.getId() == id) {
                petParaExcluir = pet;
                break;
            }
        }

        System.out.println("Você está prestes a excluir o pet: ");
        System.out.println(petParaExcluir.toString());
        System.out.print("Deseja realmente continuar? Digite corretamente 'SIM' ou 'NÃO': ");
        String confirmacao = Constantes.scanner.nextLine();

        if (!confirmacao.equals("SIM")) {
            System.out.println("Operação cancelada pelo usuário.");
            return;
        }

        boolean arquivoExcluido = excluirArquivoPet(petParaExcluir);

        if (arquivoExcluido) {
            System.out.println("Pet excluído com sucesso!");
        } else {
            System.out.println("Não foi possível localizar o arquivo do pet para exclusão");
        }
    }

    public static void deletarPet(Pet petParaExcluir) {
         excluirArquivoPet(petParaExcluir);
    }

    private static boolean excluirArquivoPet(Pet pet) {
        File diretorioPets = new File(Constantes.PETS_CADASTRADOS);
        File[] arquivos = diretorioPets.listFiles();
        String petNome = pet.getNome().trim().replace(" ", "").toUpperCase();

        if (arquivos == null) {
            return false;
        }

        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().toUpperCase().contains(petNome)) {
                if (verificarConteudoArquivo(arquivo, pet)) {
                    return arquivo.delete();
                }
            }
        }

        return false;
    }

    private static boolean verificarConteudoArquivo(File arquivo, Pet pet) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("1 - ") && linha.substring(4).equals(pet.getNome())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return false;
    }

}
