package service;

import model.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalvarPet {
    public void salvarPet(Pet pet) {
        String petsCadastrados = "src/resources/petsCadastrados";
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeArquivo = data + "-" + pet.getNome().trim().replace(" ", "").toUpperCase() + ".TXT";
        File arquivo = new File(petsCadastrados, nomeArquivo);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo))) {
            bufferedWriter.write("1 - " + pet.getNome());
            bufferedWriter.newLine();
            bufferedWriter.write("2 - " + pet.getTipo().getNome());
            bufferedWriter.newLine();
            bufferedWriter.write("3 - " + pet.getSexo().getNome());
            bufferedWriter.newLine();
            bufferedWriter.write("4 - " + pet.getEnderecoPet().getRua() +
                    ", " + pet.getEnderecoPet().getNumeroCasa() +
                    ", " + pet.getEnderecoPet().getCidade());
            bufferedWriter.newLine();
            bufferedWriter.write("5 - " + pet.getIdade() + " anos");
            bufferedWriter.newLine();
            bufferedWriter.write("6 - " + pet.getPeso() + "kg");
            bufferedWriter.newLine();
            bufferedWriter.write("7 - " + pet.getRaca());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
