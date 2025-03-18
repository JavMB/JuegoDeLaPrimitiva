package com.primitiva.Juego;

import com.primitiva.GestionSorteo.Bombo;
import com.primitiva.GestionSorteo.ResultadoSorteo;
import com.primitiva.GestionSorteo.Sorteo;
import com.primitiva.PrimitivaConstantes;

public class JuegoPrimitiva {

    private final Sorteo sorteo;

    public JuegoPrimitiva() {
        Bombo bomboPrincipal = new Bombo(PrimitivaConstantes.NUMERO_MIN, PrimitivaConstantes.NUMERO_MAX);
        Bombo bomboReintegro = new Bombo(PrimitivaConstantes.REINTEGRO_MIN, PrimitivaConstantes.REINTEGRO_MAX);
        this.sorteo = new Sorteo(bomboPrincipal, bomboReintegro);
    }


    /**
     * Primera opción del menú, se juega un sorteo de Primitiva
     *
     * @param boleto el boleto con el que juega el usuario y que hay que comparar con el resultado del sorteo
     * @return pasa unos parámetros al metodo que determina que premio se gana
     */
    public Premios juegoUnico(Boleto boleto) {
        sorteo.generar();
        int aciertos = calcularAciertos(boleto);

        boolean complementario = false;
        if (aciertos == 5) {
            complementario = calcularComplementario(boleto);
        }

        boolean reintegro = false;
        if (aciertos == 6) {
            reintegro = boleto.getReintegro() == sorteo.getReintegro();
        }

        return determinarPremio(aciertos, reintegro, complementario);
    }


    /**
     * Calcula los aciertos del boleto comparando con el resultado sorteo
     *
     * @param boleto El boleto con el que juega el usuario
     * @return La cantidad de números coincidentes entre boleto y resultado sorteo
     */
    private int calcularAciertos(Boleto boleto) {
        int aciertos = 0;
        for (int numero : boleto.getNumerosPrincipales()) {
            for (int resultado : sorteo.getResultado()) {
                if (numero == resultado) {
                    aciertos++;
                    break;
                }
            }
        }
        return aciertos;
    }

    /**
     * Se busca el número del boleto que no coincide con ningudo del sorteo
     *
     * @param boleto El boleto en el que se busca el complementario
     * @return Si el complementario encontrado es igual al del sorteo o no
     */
    private boolean calcularComplementario(Boleto boleto) {
        for (int num : boleto.getNumerosPrincipales()) {
            if (sorteo.getComplementario() == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determinar el premio que se ha ganado
     *
     * @param aciertos       El número de aciertos para devolver el premio adecuado
     * @param reintegro      El reintegro para saber si se consigue el premio especial
     * @param complementario El número complementario para saber si se consigue el segundo premio
     * @return Devuelve el premio ganado
     */
    private Premios determinarPremio(int aciertos, boolean reintegro, boolean complementario) {
        return switch (aciertos) {
            case 3 -> Premios.QUINTO;
            case 4 -> Premios.CUARTO;
            case 5 -> complementario ? Premios.SEGUNDO : Premios.TERCERO;
            case 6 -> reintegro ? Premios.ESPECIAL : Premios.PRIMERO;
            default -> Premios.NINGUNO;
        };
    }


    /**
     * Hace sorteos hasta conseguir un premio, incluyendo reintegro
     *
     * @param boleto El boleto que vamos a comparar en los sorteos
     * @return El número de sorteos que se han hecho hasta conseguir un premio o reintegro
     */
    public long juegoHastaPremio(Boleto boleto) {
        int iteraciones = 0;
        int aciertos;
        boolean reintegro;
        do {
            iteraciones++;
            sorteo.generar();
            aciertos = calcularAciertos(boleto);
            reintegro = boleto.getReintegro() == sorteo.getReintegro();
        } while (aciertos < 3 && !reintegro);
        return iteraciones;
    }

    /**
     * Hace sorteos hasta conseguir un premio, sin incluir reintegro
     *
     * @param boleto El boleto que vamos a comparar en los sorteos
     * @return El número de sorteos que se han hecho hasta conseguir un premio
     */
    public long juegoHastaPremioSinReintegro(Boleto boleto) {
        int iteraciones = 0;
        int aciertos;
        do {
            iteraciones++;
            sorteo.generar();
            aciertos = calcularAciertos(boleto);
        } while (aciertos < 3);
        return iteraciones;
    }

    /**
     * Juega una cantidad de veces determinadas en PrimitivaConstantes
     *
     * @param boleto El boleto con el que se juega
     * @return Un array en el que se ordenan los premios de mayor a menor de izq a decha
     */
    public int[] juegoDeMuchosSorteos(Boleto boleto) {
        final int MUCHOS_SORTEOS = PrimitivaConstantes.CANT_SORTEOS;
        final int[] premios = new int[7];

        for (int i = 0; i < MUCHOS_SORTEOS; i++) {
            switch (juegoUnico(boleto)) {
                case ESPECIAL -> premios[0]++;
                case PRIMERO -> premios[1]++;
                case SEGUNDO -> premios[2]++;
                case TERCERO -> premios[3]++;
                case CUARTO -> premios[4]++;
                case QUINTO -> premios[5]++;
                default -> premios[6]++;
            }
        }
        return premios;
    }

    /**
     * Juega sorteos hasta que haya 6 aciertos y el reintegro = Premio Especial
     *
     * @param boleto El boleto que el usuario juega
     * @return El número de sorteos que han hecho falta
     */
    public String juegoHastaEspecialResultado(Boleto boleto) {
        int iteraciones = 0;
        // Capturar tiempo de inicio
        long tiempoInicio = System.nanoTime();

        do {
            iteraciones++;
        } while (juegoUnico(boleto) != Premios.ESPECIAL);

        // Capturar tiempo de fin y calcular la diferencia
        long tiempoFin = System.nanoTime();
        double tiempoTotal = (tiempoFin - tiempoInicio) / 1_000_000_000.0; // Convertir a segundos

        return iteraciones + " Sorteos (Tiempo: " + String.format("%.2f", tiempoTotal) + " segundos)";
    }

    public ResultadoSorteo obtenerUltimoSorteo() {
        return sorteo.getResultadoGanador();
    }


}
