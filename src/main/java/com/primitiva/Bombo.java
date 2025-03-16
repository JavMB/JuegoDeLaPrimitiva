package com.primitiva;

import java.util.Arrays;

public class Bombo {
    private int[] pool;
    private int size;

    public Bombo(int min, int max) {
        this.pool = generarPool(min, max);
        this.size = pool.length;

    }


    public void reiniciarBolas() {
        size = pool.length;
    }


    /**
     * Metodo para Rellenar un array con numeros aleatorios
     * @param array el array en cuaestion
     */
    public void sacarNumerosEnArray(int [] array){
        for(int i = 0; i < array.length; i++){
            int indice = PrimitivaConstantes.rnd.nextInt(0,size);

            array[i] = pool[indice];
            extraerBola(indice);

        }
    }


    /**
     * metodo para sacar un numero aleatorio de la pool
     * @return numero aleatorio
     */
    public int sacarUnNumero(){
        int indice = PrimitivaConstantes.rnd.nextInt(0,size);
        extraerBola(indice);
        return pool[indice];

    }


    /**
     * metodo para generar la pool de bolas
     * @param min numero minimo
     * @param max numero maximo
     * @return pool de bolas
     */
    private int[] generarPool(int min, int max){
        int[] array = new int[(max + 1) - min];
        int contador = 0;

        for(int i = min; i <= max; i++){
            array[contador] = i;
            contador++;
        }

        return array;
    }


    /**
     * Ajustar el array y el size para que no salgan los numeros previamente sacados
     * @param indice numero seleccionado
     */
    private void extraerBola(int indice) {
        if(indice != (size-1)){
            int aux = pool[indice];
            pool[indice] = pool[size-1];
            pool[size-1] = aux;

        }

        size--;
    }


    @Override
    public String toString() {
        return "contenido Bombo [numeros="
                + Arrays.toString(pool)
                + "]";
    }
}
