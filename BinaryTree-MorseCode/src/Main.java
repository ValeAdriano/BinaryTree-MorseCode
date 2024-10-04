
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel {
    private ArvoreCodigoMorse arvoreMorse;

    public Main() {
        arvoreMorse = new ArvoreCodigoMorse();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = getWidth() / 2;
        int y = 50;
        int nivel = 100;
        int deslocamento = 200;
        desenharNo(g, x, y, deslocamento, nivel, arvoreMorse.raiz);
    }

    private void desenharNo(Graphics g, int x, int y, int deslocamento, int nivel, ArvoreCodigoMorse.No no) {
        if (no != null) {
            g.setColor(Color.BLACK);
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(String.valueOf(no.caractere), x - 5, y + 5);

            if (no.esquerda != null) {
                int xEsquerda = x - deslocamento;
                int yFilho = y + nivel;
                g.drawLine(x, y, xEsquerda, yFilho);
                desenharNo(g, xEsquerda, yFilho, deslocamento / 2, nivel, no.esquerda);
            }

            if (no.direita != null) {
                int xDireita = x + deslocamento;
                int yFilho = y + nivel;
                g.drawLine(x, y, xDireita, yFilho);
                desenharNo(g, xDireita, yFilho, deslocamento / 2, nivel, no.direita);
            }
        }
    }

    public static void exibirArvore() {
        JFrame frame = new JFrame("Árvore Binária de Código Morse");
        Main painelArvore = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(painelArvore);
        frame.setVisible(true);
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
                    exibirArvore();
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
