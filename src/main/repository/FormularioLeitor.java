package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormularioLeitor {
    private final static String formularioTxt = "src/resources/formulario.txt";
//    List<String> perguntas = new ArrayList<>();

    public static List<String> lerPerguntas() {
        List<String> perguntas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(formularioTxt))) {
            String linhas;
            while ((linhas = bufferedReader.readLine()) != null) {
                perguntas.add(linhas);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return perguntas;
    }
}
