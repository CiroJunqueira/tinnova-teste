package geral;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite um número: ");
		int n = sc.nextInt();
		int somaMult3 = 0;
		int somaMult5 = 0;
		int total = 0;

		for (int i = 0; i <= n; i++) {
			if (i % 3 == 0) {
				somaMult3 = somaMult3 + i;
			}

			if (i % 5 == 0) {
				somaMult5 = somaMult5 + i;
			}
		}
		
		total = somaMult3 + somaMult5;

		System.out.println("Soma dos múltiplos de 3 e 5 do número informado: " + total);
		sc.close();
	}
}