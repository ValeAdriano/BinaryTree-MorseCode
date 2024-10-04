class Pilha {
    private No topo;

    public void empilhar(int x, int y) {
        No novoNo = new No(x, y);
        novoNo.prox = topo;
        topo = novoNo;
    }

    public No desempilhar() {
        if (topo == null) return null;
        No desempilhado = topo;
        topo = topo.prox;
        return desempilhado;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}
