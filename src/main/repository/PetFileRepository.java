package repository;

import model.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetFileRepository {

    public void salvarPet(Pet pet) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeArquivo = data + "-" + pet.getNome().trim().replace(" ", "").toUpperCase() + ".TXT";
        String petsCadastrados = "src/resources/petsCadastrados";
        File arquivo = new File(petsCadastrados, nomeArquivo);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo))) {
            bufferedWriter.write(pet.toFileFormat());
            System.out.println("\n✅ Pet cadastrado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar pet no arquivo: " + nomeArquivo, e);
        }
    }
}
