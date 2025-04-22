package repository;

import util.Constantes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormularioLeitor {

    public static List<String> lerPerguntas() {
        List<String> perguntas = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constantes.FORMULARIO_TXT))) {
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
