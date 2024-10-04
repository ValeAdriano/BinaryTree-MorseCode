import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class FloodFill {
    private BufferedImage imagem;
    private int largura, altura;
    private int corOriginal, novaCor;
    private JFrame janela;
    private JLabel rotuloImagem;

    public FloodFill(String caminhoImagem, int novaCor) throws IOException {
        this.imagem = ImageIO.read(new File(caminhoImagem));
        this.largura = imagem.getWidth();
        this.altura = imagem.getHeight();
        this.novaCor = novaCor;

        janela = new JFrame("Preenchimento de Imagem");
        rotuloImagem = new JLabel(new ImageIcon(imagem));
        janela.add(rotuloImagem);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    private void atualizarImagem() {
        rotuloImagem.setIcon(new ImageIcon(imagem));
        janela.repaint();
    }

    public void preencherComPilha(int x, int y) {
        corOriginal = imagem.getRGB(x, y);
        if (corOriginal == novaCor) return;

        Pilha pilha = new Pilha();
        pilha.empilhar(x, y);

        int iteracoes = 0;

        while (!pilha.estaVazia()) {
            No atual = pilha.desempilhar();
            int px = atual.x;
            int py = atual.y;

            if (px >= 0 && px < largura && py >= 0 && py < altura && imagem.getRGB(px, py) == corOriginal) {
                imagem.setRGB(px, py, novaCor);
                pilha.empilhar(px + 1, py);
                pilha.empilhar(px - 1, py);
                pilha.empilhar(px, py + 1);
                pilha.empilhar(px, py - 1);
            }

            iteracoes++;
            if (iteracoes % 500 == 0) {
                atualizarImagem();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        atualizarImagem();
    }

    public void preencherComFila(int x, int y) {
        corOriginal = imagem.getRGB(x, y);
        if (corOriginal == novaCor) return;

        Fila fila = new Fila();
        fila.enfileirar(x, y);

        int iteracoes = 0;

        while (!fila.estaVazia()) {
            No atual = fila.desenfileirar();
            int px = atual.x;
            int py = atual.y;

            if (px >= 0 && px < largura && py >= 0 && py < altura && imagem.getRGB(px, py) == corOriginal) {
                imagem.setRGB(px, py, novaCor);
                fila.enfileirar(px + 1, py);
                fila.enfileirar(px - 1, py);
                fila.enfileirar(px, py + 1);
                fila.enfileirar(px, py - 1);
            }

            iteracoes++;
            if (iteracoes % 1000 == 0) {
                atualizarImagem();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        atualizarImagem();
    }

    public void salvarImagem(String caminhoSaida) throws IOException {
        ImageIO.write(imagem, "png", new File(caminhoSaida));
    }
}
