import java.util.Scanner;

public class Main {
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
