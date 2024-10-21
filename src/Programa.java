

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o caminho completo do arquivo: ");
        String caminho = sc.nextLine();

        Map<String, Integer> votos = lerVotosDoArquivo(caminho);

        if (votos != null) {
            exibirVotos(votos);
        } else {
            System.out.println("Ocorreu um erro ao ler o arquivo.");
        }

        sc.close();
    }

    private static Map<String, Integer> lerVotosDoArquivo(String caminho) {
        Map<String, Integer> votos = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                String nome = campos[0];
                int quantidade = Integer.parseInt(campos[1]);

                votos.put(nome, votos.getOrDefault(nome, 0) + quantidade);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }

        return votos;
    }

    private static void exibirVotos(Map<String, Integer> votos) {
        for (Map.Entry<String, Integer> entrada : votos.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
}
