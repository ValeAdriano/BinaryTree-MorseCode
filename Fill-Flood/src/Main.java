import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o caminho da imagem:");
        String caminhoImagem = scanner.nextLine();

        int novaCor = 0xFFFF0000;

        FloodFill floodFill = new FloodFill(caminhoImagem, novaCor);

        System.out.println("Escolha o método de preenchimento (1 = Pilha, 2 = Fila):");
        int escolha = scanner.nextInt();

        System.out.println("Digite as coordenadas iniciais (x y):");
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        if (escolha == 1) {
            floodFill.preencherComPilha(x, y);
        } else if (escolha == 2) {
            floodFill.preencherComFila(x, y);
        }

        floodFill.salvarImagem("imagem-depois.png");
        System.out.println("Imagem preenchida e salva como 'imagem-depois.png'.");
    }
}
