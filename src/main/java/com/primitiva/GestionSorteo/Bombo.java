package com.primitiva.GestionSorteo;

import com.primitiva.PrimitivaConstantes;

import java.util.Arrays;

/**
 * Clase que representa un bombo con números aleatorios.
 */
public class Bombo {
    private final int[] pool;
    private int size;

    public Bombo(int min, int max) {
        this.pool = generarPool(min, max);
        this.size = pool.length;
    }

    /**
     * Reinicia el bombo, restaurando todos los números.
     */
    public void reiniciarBolas() {
        size = pool.length;
    }

    /**
     * Extrae una serie de números aleatorios de la pool sin repetirlos.
     *
     * @param array Array donde se almacenarán los números extraídos.
     */
    public void sacarNumerosEnArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = sacarUnNumero();
        }
    }

    /**
     * Extrae un número aleatorio de la pool sin repetirlo.
     *
     * @return Número extraído.
     */
    public int sacarUnNumero() {
        int indice = PrimitivaConstantes.rnd.nextInt(0, size);
        int numeroExtraido = pool[indice];
        extraerBola(indice);
        return numeroExtraido;
    }

    /**
     * Genera un array con números consecutivos entre un mínimo y un máximo.
     *
     * @param min Número mínimo.
     * @param max Número máximo.
     * @return Array de números consecutivos.
     */
    private int[] generarPool(int min, int max) {
        int[] array = new int[(max + 1) - min];
        int contador = 0;

        for (int i = min; i <= max; i++) {
            array[contador] = i;
            contador++;
        }

        return array;
    }

    /**
     * Ajusta el array y el tamaño para evitar la repetición de números extraídos.
     *
     * @param indice Índice del número a extraer.
     */
    private void extraerBola(int indice) {
        int aux = pool[indice];
        pool[indice] = pool[size-1];
        pool[size-1] = aux;
        size--;
    }

    @Override
    public String toString() {
        return "contenido Bombo [numeros=" + Arrays.toString(pool) + "]";
    }
}
