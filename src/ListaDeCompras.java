public class ListaDeCompras {
    public static void main(String[] args) {
        int[][] distancias = { // Matriz de distâncias
                { 0, 1, 2, 3, 4, 5 }, // Corredor 1
                { 1, 0, 2, 3, 4, 5 }, // Corredor 2
                { 2, 2, 0, 3, 4, 5 }, // Corredor 3
                { 3, 3, 3, 0, 1, 2 }, // Corredor 4
                { 4, 4, 4, 1, 0, 2 }, // Corredor 5
                { 5, 5, 5, 2, 2, 0 } // Corredor 6
        };

        String[] produtos = { // Lista de produtos
                "Sabão em pó",
                "Amaciante",
                "Papel higiênico",
                "Farinha de trigo",
                "Feijão",
                "Macarrão",
                "Leite",
                "Detergente",
                "Sabonete",
                "Melancia",
                "Condicionador",
                "Margarina",
                "Óleo",
                "Vinagre",
                "Maçã",
                "Banana",
                "Pasta de dente",
                "Refrigerante",
                "Pão de forma"
        };

        int[] caminho = menorCaminho(distancias); // Encontra o menor caminho

        System.out.println("Menor caminho:");
        for (int i = 0; i < caminho.length; i++) {
            int posicao = caminho[i];
            System.out.println(
                    produtos[posicao] + " - Corredor " + (posicao / 3 + 1) + " - Prateleira " + (posicao % 3 + 1));
        }
    }

    public static int[] menorCaminho(int[][] distancias) {
        int n = distancias.length;
        int[] caminhoAtual = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            caminhoAtual[i] = i + 1;
        }
        int[] melhorCaminho = caminhoAtual.clone();
        int distanciaAtual = distanciaCaminho(caminhoAtual, distancias);
        int melhorDistancia = distanciaAtual;

        // Gerar permutações do caminho
        while (proximaPermutacao(caminhoAtual)) {
            distanciaAtual = distanciaCaminho(caminhoAtual, distancias);
            if (distanciaAtual < melhorDistancia) {
                melhorDistancia = distanciaAtual;
                melhorCaminho = caminhoAtual.clone();
            }
        }

        return melhorCaminho;
    }

    public static int distanciaCaminho(int[] caminho, int[][] distancias) {
        int distancia = distancias[0][caminho[0]];
        for (int i = 0; i < caminho.length - 1; i++) {
            distancia += distancias[caminho[i]][caminho[i + 1]];
        }
        distancia += distancias[caminho[caminho.length - 1]][0];
        return distancia;
    }

    public static boolean proximaPermutacao(int[] a) {
        // Encontrar o maior índice i tal que a[i] < a[i+1]
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        // Se i < 0, a é a última permutação
        if (i < 0) {
            return false;
        }

        // Encontrar o maior índice j tal que a[j] > a[i]
        int j = a.length - 1;
        while (a[j] <= a[i]) {
            j--;
        }

        // Trocar a[i] com a[j]
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        // Reverter a[i+1..n-1]
        int k = i + 1;
        int l = a.length - 1;
        while (k < l) {
            temp = a[k];
            a[k] = a[l];
            a[l] = temp;
            k++;
            l--;
        }
        return true;
    }

}
