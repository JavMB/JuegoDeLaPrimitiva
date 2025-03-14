package com.primitiva.GestionSorteo;

import com.primitiva.PrimitivaConstantes;

import java.util.Arrays;
//TODO javadocs necesarios
public class Bombo {
    private final int[] pool;
    private int size;

    public Bombo(int min, int max) {
        this.pool = generarPool(min, max);
        this.size = pool.length;

    }


    public void reiniciarBolas() {
        size = pool.length;
    }

    //TODO CORREGIR URGENTE , mover logica corregida de aqui a dentro de sacar un numero y usar el metodo sacar un numero que no saca repetidos dentro de este x veces.
    public void sacarNumerosEnArray(int [] array){
        for(int i = 0; i < array.length; i++){
            int indice = PrimitivaConstantes.rnd.nextInt(0,size);

            array[i] = pool[indice];
            moveToLeft(indice);

        }
    }

    //TODO CORREGIR URGENTE - en Sorteo al usar esto puedes poner un numero repetido.
    public int sacarUnNumero(){
        return pool[PrimitivaConstantes.rnd.nextInt(0,size)];
    }



    private int[] generarPool(int min, int max){
        int[] array = new int[(max + 1) - min];
        int contador = 0;

        for(int i = min; i <= max; i++){
            array[contador] = i;
            contador++;
        }

        return array;
    }
    //TODO quitar este metodo , no es necesario desplazar, podemos intercambiar el numero extraido con el ultimo disponible y reducir el size
    private void moveToLeft(int indice) {
        int aux = pool[indice];

        for (int i = indice; i < pool.length - 1; i++) {
            pool[i] = pool[i + 1];
        }
        size--;

        pool[pool.length-1] = aux;
    }


    @Override
    public String toString() {
        return "contenido Bombo [numeros="
                + Arrays.toString(pool)
                + "]";
    }
}
