import javax.swing.*;
import java.awt.*;

public class VisualizarArvore extends JPanel {
    private ArvoreCodigoMorse arvoreMorse;

    public VisualizarArvore(ArvoreCodigoMorse arvore) {
        this.arvoreMorse = arvore;
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

    public static void exibirArvore(ArvoreCodigoMorse arvore) {
        JFrame frame = new JFrame("Árvore Binária de Código Morse");
        VisualizarArvore painelArvore = new VisualizarArvore(arvore);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(painelArvore);
        frame.setVisible(true);
    }
}