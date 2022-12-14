import java.sql.SQLOutput;
import java.util.Scanner;

public class JogoDaVelha {

    public static String[][] inicializaGrade() {

        String[][] grade = new String[3][3];

        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade.length; j++) {
                grade[i][j] = "   ";
            }
        }
        return grade;
    }

    public static void desenhaGrade(String[][] grade) {

        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade.length; j++) {
                System.out.print(grade[i][j]);
                if (j < (grade.length - 1)) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < (grade.length - 1)) {
                System.out.println("----+-----+----");
            }
        }
    }

    public static String[][] desenhaSimboloNaGrade(String[][] grade, int linha, int coluna, String simbolo) {

        try {

            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
                System.out.println("Posição inválida. Escolha a posição novamente: ");
                throw new Exception();
            }

            if (grade[linha][coluna].contains("X") || grade[linha][coluna].contains("O")) {
                System.out.println("Posição já ocupada. Escolha a posição novamente: ");
                throw new Exception();
            }

            if (grade[linha][coluna].equalsIgnoreCase("   ")) {
                grade[linha][coluna] = simbolo;
            }

        } catch (Exception e) {
            desenhaGrade(grade);
            Scanner input = new Scanner(System.in);
            System.out.println("Linha: ");
            int novaLinha = input.nextInt();
            System.out.println("Coluna: ");
            int novaColuna = input.nextInt();
            System.out.println("Linha: " + novaLinha);
            System.out.println("Coluna: " + novaColuna);
            desenhaSimboloNaGrade(grade, novaLinha, novaColuna, simbolo);
        }

        return grade;
    }

    public static boolean verificaGanhador(String[][] grade, String simbolo) { //alterar para hasGanhador que é uma convenção do java

        if (grade[0][0].contains(simbolo) && grade[0][1].contains(simbolo) && grade[0][2].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // linha
            return true;
        } else if (grade[1][0].contains(simbolo) && grade[1][1].contains(simbolo) && grade[1][2].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // linha
            return true;
        } else if (grade[2][0].contains(simbolo) && grade[2][1].contains(simbolo) && grade[2][2].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // linha
            return true;
        } else if (grade[0][0].contains(simbolo) && grade[1][0].contains(simbolo) && grade[2][0].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // coluna
            return true;
        } else if (grade[0][1].contains(simbolo) && grade[1][1].contains(simbolo) && grade[2][1].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // coluna
            return true;
        } else if (grade[0][2].contains(simbolo) && grade[1][2].contains(simbolo) && grade[2][2].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // coluna
            return true;
        } else if (grade[0][0].contains(simbolo) && grade[1][1].contains(simbolo) && grade[2][2].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // diagonal 1
            return true;
        } else if (grade[0][2].contains(simbolo) && grade[1][1].contains(simbolo) && grade[2][0].contains(simbolo)) {
            System.out.println("Jogador " + simbolo + " ganhou a jogada"); // diagonal 2
            return true;
        }
        return false;
    }

    public static int numeroRodadas() {
        Scanner input = new Scanner(System.in);
        int numeroRodadas = 0;

        System.out.println("Informe o número de rodadas que você quer jogar: ");

        try {
            numeroRodadas = input.nextInt();
            if (numeroRodadas >= 1) {
                return numeroRodadas;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Número de rodadas inválido.");
            numeroRodadas = numeroRodadas();
        }
        return numeroRodadas;
    }

    public static void main(String[] args) { // faz a interação com o jogador

        Scanner input = new Scanner(System.in);
        System.out.println("Jogo da Velha");

        int numeroRodadas = numeroRodadas();
        int vitoriasX = 0;
        int vitoriasO = 0;

        while (numeroRodadas >= 1) {
            int numeroJogadas = 0;
            String simbolo = " X ";
            boolean existeGanhador = false;

            String[][] grade = inicializaGrade();
            desenhaGrade(grade);

            while (numeroJogadas < 9 && !existeGanhador) {
                System.out.println("Jogada " + (numeroJogadas + 1));

                System.out.println("É a vez do jogador" + simbolo);

                System.out.println("Indique a posição escolhida: ");
                System.out.println("Linha: ");
                int linha = input.nextInt();
                System.out.println("Coluna: ");
                int coluna = input.nextInt();
                System.out.println("Linha: " + linha);
                System.out.println("Coluna: " + coluna);

                grade = desenhaSimboloNaGrade(grade, linha, coluna, simbolo);

                desenhaGrade(grade);

                existeGanhador = verificaGanhador(grade, simbolo);

                if (existeGanhador) {
                    if (simbolo.equals(" X ")) {
                        vitoriasX++;
                    } else {
                        vitoriasO++;
                    }
                }

                if (simbolo.equalsIgnoreCase(" x ")) {
                    simbolo = " O ";
                } else {
                    simbolo = " X ";
                }

                numeroJogadas++;

            }
            if (!existeGanhador) {
                System.out.println("Empatou a jogada!");
            }

            numeroRodadas--;

            if (numeroRodadas == 0) {
                if (vitoriasX > vitoriasO) {
                    System.out.println("Jogador X ganhou");
                } else if (vitoriasX < vitoriasO) {
                    System.out.println("Jogador O ganhou");
                } else {
                    System.out.println("O jogo empatou.");
                    System.out.println("Iniciando rodada final...");
                    numeroRodadas++;
                }
            }
        }
    }
}