class Fila {
    private No inicio, fim;

    public void enfileirar(int x, int y) {
        No novoNo = new No(x, y);
        if (fim != null) fim.prox = novoNo;
        fim = novoNo;
        if (inicio == null) inicio = fim;
    }

    public No desenfileirar() {
        if (inicio == null) return null;
        No desenfileirado = inicio;
        inicio = inicio.prox;
        if (inicio == null) fim = null;
        return desenfileirado;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}
