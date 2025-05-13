package util;

public class Style {
    private final static String TRAVESSAO = "—";

    public static void travessao(int vezes) {
        for (int i = 0; i < vezes ; i++) {
            System.out.print(TRAVESSAO);
        }
        System.out.println();
    }


}
