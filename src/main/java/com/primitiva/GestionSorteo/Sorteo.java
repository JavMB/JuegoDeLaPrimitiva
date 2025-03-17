package com.primitiva.GestionSorteo;


import com.primitiva.PrimitivaConstantes;

public class Sorteo {
    private final int[] resultado;
    private int complementario;
    private int reintegro;

    private final Bombo bomboPrincipal;
    private final Bombo bomboReintegro;

    public Sorteo(Bombo bomboPrincipal, Bombo bomboReintegro) {
        this.bomboPrincipal = bomboPrincipal;
        this.bomboReintegro = bomboReintegro;
        this.resultado = new int[PrimitivaConstantes.TOTAL_NUMEROS];
    }

    /**
     * Genera un resultado de la primitiva
     * reasignando los valores del array principal y los enteros
     * permitiendonos mejorar la eficiencia de sorteos masivos.
     */
    public void generar() {
        bomboPrincipal.reiniciarBolas(); // Reinicia el bombo para cada sorteo
        bomboPrincipal.sacarNumerosEnArray(resultado); // Genera los 6 números principales
        complementario = bomboPrincipal.sacarUnNumero(); // Genera el complementario
        reintegro = bomboReintegro.sacarUnNumero(); // Genera el reintegro
    }

    /**
     * @return Devuelve la referencia de la array con los resultados actuales de los 6 nums
     */
    public int[] getResultado() {
        return resultado;
    }

    /**
     * @return Devuelve el valor del complementario obtenido en el sorteo
     */
    public int getComplementario() {
        return complementario;
    }

    /**
     * @return Devuelve el valor del reintegro obtenido en el sorteo
     */
    public int getReintegro() {
        return reintegro;
    }

    /**
     * @return devuelve un Resultado inmutable en caso de ser necesario, como para devolver el resultado ganador .
     */
    public ResultadoSorteo getResultadoGanador() {
        return new ResultadoSorteo(resultado, complementario, reintegro);
    }
}