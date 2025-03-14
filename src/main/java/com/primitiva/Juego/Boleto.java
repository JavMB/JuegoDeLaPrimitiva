package com.primitiva.Juego;

import com.primitiva.PrimitivaConstantes;

import java.util.Arrays;

public class Boleto {
    private final int[] numerosPrincipales;
    private final int reintegro;

    public Boleto(int[] numerosPrincipales) {
        if (!esValido(numerosPrincipales)) {
            this.numerosPrincipales = generarBoletoAleatorio(PrimitivaConstantes.NUMERO_MAX, PrimitivaConstantes.TOTAL_NUMEROS);
        } else {
            this.numerosPrincipales = numerosPrincipales;
        }
        this.reintegro = PrimitivaConstantes.rnd.nextInt(PrimitivaConstantes.REINTEGRO_MAX + 1);
    }


    /**
     * Constructor que permite crear un Boleto aleatorio.
     */
    public Boleto() {
        this.numerosPrincipales = generarBoletoAleatorio(PrimitivaConstantes.NUMERO_MAX, PrimitivaConstantes.TOTAL_NUMEROS);
        this.reintegro = PrimitivaConstantes.rnd.nextInt(PrimitivaConstantes.REINTEGRO_MAX + 1);
    }

    /**
     * Genera un boleto aleatorio con b números únicos en el rango de 1 a n.
     *
     * @param n cantidad de números disponibles (rango: 1 a n)
     * @param b cantidad de números en el boleto
     * @return un array de enteros con el boleto generado
     * @throws IllegalArgumentException si b > n o si n o b no son positivos
     */
    private int[] generarBoletoAleatorio(int n, int b) {
        if (b > n || n <= 0 || b <= 0) {
            throw new IllegalArgumentException("Parámetros inválidos: b debe ser <= n y ambos positivos");
        }

        int[] nums = new int[n];
        int[] bol = new int[b];
        int size = nums.length;

        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }

        for (int i = 0; i < b; i++) {
            int index = PrimitivaConstantes.rnd.nextInt(size);
            bol[i] = nums[index];
            nums[index] = nums[--size];
        }

        return bol;
    }

    /**
     * @param numerosPrincipales boleto a comprobar
     * @return devuelve si el boleto es valido, 6 nums, no repetidos,etc
     */
    private boolean esValido(int[] numerosPrincipales) {
        if (numerosPrincipales == null || numerosPrincipales.length != PrimitivaConstantes.TOTAL_NUMEROS) return false;

        for (int i = 0; i < numerosPrincipales.length; i++) {
            for (int j = i + 1; j < numerosPrincipales.length; j++) {
                if (numerosPrincipales[i] == numerosPrincipales[j] || numerosPrincipales[i] > 49 || numerosPrincipales[i] < 1)
                    return false;
            }
        }
        return true;
    }

    /**
     * @return Devuelve una copia superficial del array que guarda los 6 numeros del boleto
     */
    public int[] getNumerosPrincipales() {
        return Arrays.copyOf(numerosPrincipales, numerosPrincipales.length);
    }

    /**
     * @return Devuelve el valor del reintegro generado aleatoriamente perteneciente al Boleto
     */
    public int getReintegro() {
        return reintegro;
    }

    @Override
    public String toString() {
        return "Boleto [numeros="
                + Arrays.toString(numerosPrincipales)
                + ", reintegro="
                + reintegro
                + "]";
    }
}
