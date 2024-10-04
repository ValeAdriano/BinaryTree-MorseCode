import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ArvoreCodigoMorse {
    No raiz;
    private Map<Character, String> mapaCodificacao;

    public class No {
        char caractere;
        No esquerda, direita;

        No(char caractere) {
            this.caractere = caractere;
            esquerda = direita = null;
        }
    }

    public ArvoreCodigoMorse() {
        raiz = new No(' ');
        mapaCodificacao = new HashMap<>();

        inserirCodigo('A', ".-");
        inserirCodigo('B', "-...");
        inserirCodigo('C', "-.-.");
        inserirCodigo('D', "-..");
        inserirCodigo('E', ".");
        inserirCodigo('F', "..-.");
        inserirCodigo('G', "--.");
        inserirCodigo('H', "....");
        inserirCodigo('I', "..");
        inserirCodigo('J', ".---");
        inserirCodigo('K', "-.-");
        inserirCodigo('L', ".-..");
        inserirCodigo('M', "--");
        inserirCodigo('N', "-.");
        inserirCodigo('O', "---");
        inserirCodigo('P', ".--.");
        inserirCodigo('Q', "--.-");
        inserirCodigo('R', ".-.");
        inserirCodigo('S', "...");
        inserirCodigo('T', "-");
        inserirCodigo('U', "..-");
        inserirCodigo('V', "...-");
        inserirCodigo('W', ".--");
        inserirCodigo('X', "-..-");
        inserirCodigo('Y', "-.--");
        inserirCodigo('Z', "--..");
    }

    private void inserirCodigo(char caractere, String codigoMorse) {
        No atual = raiz;
        for (char sinal : codigoMorse.toCharArray()) {
            if (sinal == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new No(' ');
                }
                atual = atual.esquerda;
            } else if (sinal == '-') {
                if (atual.direita == null) {
                    atual.direita = new No(' ');
                }
                atual = atual.direita;
            }
        }
        atual.caractere = caractere;
        mapaCodificacao.put(caractere, codigoMorse);
    }

    public String codificar(String mensagem) {
        StringBuilder morse = new StringBuilder();
        for (char caractere : mensagem.toUpperCase().toCharArray()) {
            if (mapaCodificacao.containsKey(caractere)) {
                morse.append(mapaCodificacao.get(caractere)).append(" ");
            } else {
                morse.append(" ");
            }
        }
        return morse.toString();
    }

    public String decodificar(String codigoMorse) {
        StringBuilder mensagem = new StringBuilder();
        String[] palavras = codigoMorse.split("   ");
        for (String palavra : palavras) {
            String[] letras = palavra.split(" ");
            for (String letra : letras) {
                mensagem.append(decodificarLetra(letra));
            }
            mensagem.append(" ");
        }
        return mensagem.toString().trim();
    }

    private char decodificarLetra(String codigoMorse) {
        No atual = raiz;
        for (char sinal : codigoMorse.toCharArray()) {
            if (sinal == '.') {
                atual = atual.esquerda;
            } else if (sinal == '-') {
                atual = atual.direita;
            }
        }
        return atual.caractere;
    }

    public static void main(String[] args) {
        ArvoreCodigoMorse arvore = new ArvoreCodigoMorse();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Codificar mensagem");
            System.out.println("2. Decodificar mensagem");
            System.out.println("3. Exibir árvore gráfica");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a mensagem para codificar (A-Z): ");
                    String mensagem = scanner.nextLine();
                    String codigoMorse = arvore.codificar(mensagem);
                    System.out.println("Código Morse: " + codigoMorse);
                    break;
                case 2:
                    System.out.print("Digite o código Morse para decodificar: ");
                    String codigo = scanner.nextLine();
                    String texto = arvore.decodificar(codigo);
                    System.out.println("Mensagem decodificada: " + texto);
                    break;
                case 3:
                    VisualizarArvore.exibirArvore(arvore);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}