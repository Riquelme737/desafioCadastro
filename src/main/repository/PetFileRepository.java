package repository;

import model.Pet;
import util.Constantes;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetFileRepository {

    public static void salvarPet(Pet pet) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeArquivo = data + "-" + pet.getNome().trim().replace(" ", "").toUpperCase() + ".TXT";
        File arquivo = new File(Constantes.PETS_CADASTRADOS, nomeArquivo);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo))) {
            bufferedWriter.write(pet.toFileFormat());
            System.out.println("\n✅ Pet cadastrado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar pet no arquivo: " + nomeArquivo, e);
        }
    }
}
