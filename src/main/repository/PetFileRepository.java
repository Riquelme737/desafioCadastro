package repository;

import model.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetFileRepository {
    public void salvarPet(Pet pet) {
        String petsCadastrados = "src/resources/petsCadastrados";
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeArquivo = data + "-" + pet.getNome().trim().replace(" ", "").toUpperCase() + ".TXT";
        File arquivo = new File(petsCadastrados, nomeArquivo);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo))) {
            bufferedWriter.write(pet.toFileFormat());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar pet no arquivo: " + nomeArquivo, e);
        }
    }
}
