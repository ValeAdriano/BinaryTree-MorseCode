import javax.swing.*;
import java.awt.*;

public class VisualizarArvore extends JPanel {

    private ArvoreCodigoMorse arvore;

    public VisualizarArvore(ArvoreCodigoMorse arvore) {
        this.arvore = arvore;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, arvore.getRaiz(), getWidth() / 2, 50, getWidth() / 4, 50);
    }

    private void drawTree(Graphics g, ArvoreCodigoMorse.No node, int x, int y, int horizontalSpacing, int verticalSpacing) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(node.caractere != ' ' ? String.valueOf(node.caractere) : "", x - 5, y + 5);

        if (node.esquerda != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - horizontalSpacing, y + verticalSpacing);
            drawTree(g, node.esquerda, x - horizontalSpacing, y + verticalSpacing, horizontalSpacing / 2, verticalSpacing);
        }

        if (node.direita != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + horizontalSpacing, y + verticalSpacing);
            drawTree(g, node.direita, x + horizontalSpacing, y + verticalSpacing, horizontalSpacing / 2, verticalSpacing);
        }
    }

    public static void exibirArvore(ArvoreCodigoMorse arvore) {
        JFrame frame = new JFrame("Árvore Binária de Código Morse");
        VisualizarArvore painelArvore = new VisualizarArvore(arvore);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(painelArvore);
        frame.setVisible(true);
        painelArvore.repaint();
    }
}
