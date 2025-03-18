package com.primitiva;

import com.primitiva.Juego.Boleto;
import com.primitiva.Juego.JuegoPrimitiva;

import java.util.Arrays;

public class Main {
    // Array de los numeros
    public static int[] num = new int[PrimitivaConstantes.TOTAL_NUMEROS];
    // int para que el usuario introduzca los datos
    private static int numIntroducido;
    // char para que el usuario introduzca los datos
    private static char respuesta;
    // Clase boleto
    public static Boleto boleto;

    public static void main(String[] args) {
        // Crear boletos
        crearBoleto();
        // El menu
        menu();
    }

    /**
     * crear el objeto Boleto
     */
    private static void crearBoleto() {
        respuesta = preguntarFormaDeCrearBoleto();
        if (respuesta == 's' || respuesta == 'S') {
            // en caso de manual ir pidiendo varias veces hasta la longitud del total de numeros
            System.out.println(decoradorDeTexto(PrimitivaConstantes.MSG_APUESTA_MANUAL, false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            System.out.println(decoradorDeTexto(PrimitivaConstantes.LINEA_SEPARADORA, false, dondeVaElColor(PrimitivaConstantes.COLORES.BLANCO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            boleto = crearBoletoManual();
        } else {
            // por si a la persona le da flojera hacerlo a mano
            System.out.println(decoradorDeTexto(PrimitivaConstantes.MSG_APUESTA_AUTOMATICA, false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            boleto = new Boleto();
        }

        // mostar el boleto
        System.out.println(decoradorDeTexto(PrimitivaConstantes.LINEA_SEPARADORA, false, dondeVaElColor(PrimitivaConstantes.COLORES.BLANCO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto(ponerTextoBoletoString(PrimitivaConstantes.TUBOLETOTOSTRING), false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto(boleto.toString(), false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        await();
    }

    /**
     * Como crear el boleto auto o manual
     *
     * @return la respuesta de auto o manual
     */
    private static char preguntarFormaDeCrearBoleto() {
        System.out.println(decoradorDeTexto(ponerTextoBoletoString(PrimitivaConstantes.CREARBOLETOSTRING), false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        // Pedir al usuario si lo hace a mano o aleatorio
        System.out.println(decoradorDeTexto("*** Quieres introducirlos a mano? (S/N) ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.BLANCO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        return validarRespuestaChar();
    }

    /**
     * Para validar si lo que se introduce es un char
     *
     * @return un numero
     */
    public static char validarRespuestaChar() {
        // String para que el usuario introduzca los datos
        String input;
        do {
            try {
                input = PrimitivaConstantes.sc.nextLine();
            } catch (StringIndexOutOfBoundsException sofbe) {
                System.err.println(PrimitivaConstantes.MSG_ERROR);
                input = "";
            }

        } while (input.isEmpty());
        return respuesta = input.charAt(0);
    }

    /**
     * Para validar si lo que se introduce es un numero
     *
     * @return un numero
     */
    public static int validarNum() {
        do {
            try {
                numIntroducido = (int) Double.parseDouble(PrimitivaConstantes.sc.nextLine());
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.err.println(PrimitivaConstantes.MSG_ERROR);
                numIntroducido = Integer.MIN_VALUE;
            }
        } while (numIntroducido == Integer.MIN_VALUE);
        return numIntroducido;
    }

    /**
     * Para crear el boleto manualmente
     *
     * @return un array de ints para crear el boleto
     */
    private static Boleto crearBoletoManual() {
        for (int i = 0; i < num.length; i++) {
            System.out.println(decoradorDeTexto(PrimitivaConstantes.MSG_INTRODUZCA_NUMERO, false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            // verificar si el numero introducido es valido
            numIntroducido = validarNum();
            if (numIntroducido > PrimitivaConstantes.NUMERO_MAX || numIntroducido < PrimitivaConstantes.NUMERO_MIN) {
                System.err.println("No se permiten numeros mayores a " + PrimitivaConstantes.NUMERO_MAX + " y menores que " + PrimitivaConstantes.NUMERO_MIN);
                i--;
            } else {
                for (int j = 0; j < i; j++) {
                    if (num[j] == numIntroducido) {
                        System.err.println("No se permiten colocar numeros repetidos");
                        System.err.println("Quiere poner otro numero?");
                        System.err.println("(S para poner otro numero, otro valor creara un boleto automatico)");
                        respuesta = validarRespuestaChar();
                        if (respuesta == 'S' || respuesta == 's') {
                            numIntroducido = Integer.MIN_VALUE;
                        } else {
                            System.err.println("Creando boleto automaticamente...");
                            return new Boleto();
                        }
                        break;
                    }
                }
                // ver si el numero resultante es valido o no
                if (numIntroducido == Integer.MIN_VALUE) {
                    i--;
                } else {
                    // meter los numeros en el array en la cual es el boleto
                    num[i] = numIntroducido;
                }
            }
        }
        System.out.println(decoradorDeTexto(PrimitivaConstantes.LINEA_SEPARADORA, false, dondeVaElColor(PrimitivaConstantes.COLORES.BLANCO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        return new Boleto(num);
    }

    /**
     * El menu del programa
     */
    private static void menu() {
        do {
            menuText();
            numIntroducido = validarNum();
            if (numIntroducido != 0) {
                menuOptions(numIntroducido);
            }
        } while (numIntroducido != 0);
        System.out.println(PrimitivaConstantes.DESPEDIDA);
    }

    /**
     * Crear los juegos
     */
    private static final JuegoPrimitiva juegos = new JuegoPrimitiva();

    /**
     * Su funcion es recibir el texto y decorarlo con colores
     *
     * @param texto      el texto a recibir
     * @param negrita    si el texto se quiere en negrita
     * @param colorText  el color del texto
     * @param background el fondo del texto
     * @return el texto decorado
     */
    private static String decoradorDeTexto(String texto, boolean negrita, int colorText, int background) {
        int n = negrita ? 1 : 0;
        return "\u001B[" + n + ";" + colorText + ";" + background + "m" + texto + "\u001B[0m";
    }

    /**
     * Esto es para hacerlo mas simple en la cual pide un color y si es para el fondo o no
     *
     * @param color el color que se quiere
     * @param fondo si para el fondo o no
     * @return devuelve el numero del color dependiendo si es para el fondo o no
     */
    private static int dondeVaElColor(PrimitivaConstantes.COLORES color, boolean fondo) {
        if (fondo) {
            return PrimitivaConstantes.colores(color, PrimitivaConstantes.TIPOCOLOR.FONDO);
        } else {
            return PrimitivaConstantes.colores(color, PrimitivaConstantes.TIPOCOLOR.TEXTO);
        }
    }

    /**
     * Es para hacer que el programa espere y asi el usuario tenga que pulsar un boton para continuar
     */
    private static void await() {
        System.out.println(PrimitivaConstantes.LINEA_SEPARADORA);
        System.out.println(decoradorDeTexto("*** Pulse un boton para continuar ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.BLANCO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        PrimitivaConstantes.sc.nextLine();
    }

    /**
     * Es para meterle al String BOLETO una frase cualquiera ej: TU BOLETO
     *
     * @param text el texto a introducir
     * @return devolver el texto con el String BOLETO
     */
    private static String ponerTextoBoletoString(String text) {
        return text + "\n" + PrimitivaConstantes.BOLETO;
    }

    /**
     * Para realizar un contador
     */
    private static String countdown() {
        for (int i = PrimitivaConstantes.COUNTDOWN; i > 0; i--) {
            try {
                System.out.println(decoradorDeTexto("Mostrando resultados en... " + i, false, dondeVaElColor(PrimitivaConstantes.COLORES.values()[i], false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
        return "";
    }

    /**
     * El texto del menu
     */
    private static void menuText() {
        System.out.println(decoradorDeTexto(PrimitivaConstantes.NOMBREJUEGO, false, dondeVaElColor(PrimitivaConstantes.COLORES.VERDE, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** Menu ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.AZUL, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** Elige ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.AZUL, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** 1. Juego único ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.NEGRO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** 2. Jugar hasta obtener premio ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.AMARILLO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** 3. Jugar hasta obtener premio (sin reintegro) ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.MORADO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** 4. Ciclo de " + PrimitivaConstantes.CANT_SORTEOS + " sorteos ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.AZULCLARO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** 5. Jugar hasta obtener premio categoría especial ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.BLANCO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
        System.out.println(decoradorDeTexto("*** 0. Salir ***", false, dondeVaElColor(PrimitivaConstantes.COLORES.ROJO, false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
    }

    /**
     * Las opciones del menu y lo que realiza
     *
     * @param numIntroducido el numero introducido para llamar a los metodos
     */
    private static void menuOptions(int numIntroducido) {
        switch (numIntroducido) {
            // que muestre premio
            case 1 ->
                    System.out.println(countdown() + decoradorDeTexto(PrimitivaConstantes.RESULTADOS + juegos.juegoUnico(boleto) + ", " + "la combinacion ganadora era: " + juegos.obtenerUltimoSorteo() + " y tu boleto es: " + boleto, false, dondeVaElColor(PrimitivaConstantes.COLORES.values()[numIntroducido], false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            case 2 ->
                    System.out.println(countdown() + decoradorDeTexto(PrimitivaConstantes.RESULTADOS + "Se tiro un total de: " + juegos.juegoHastaPremio(boleto) + " veces hasta que te ha tocado un premio CON REINTEGRO y tu boleto es: " + boleto, false, dondeVaElColor(PrimitivaConstantes.COLORES.values()[numIntroducido], false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            case 3 ->
                    System.out.println(countdown() + decoradorDeTexto(PrimitivaConstantes.RESULTADOS + "Se tiro un total de: " + juegos.juegoHastaPremioSinReintegro(boleto) + " veces hasta que te ha tocado un premio SIN REINTEGRO y tu boleto es: " + boleto, false, dondeVaElColor(PrimitivaConstantes.COLORES.values()[numIntroducido], false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            case 4 ->
                    System.out.println(countdown() + decoradorDeTexto(PrimitivaConstantes.RESULTADOS + mostrarArray(text) + " y tu boleto es: " + boleto, false, dondeVaElColor(PrimitivaConstantes.COLORES.values()[numIntroducido], false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            case 5 ->
                    System.out.println(countdown() + decoradorDeTexto(PrimitivaConstantes.RESULTADOS + "Se tiro un total de: " + juegos.juegoHastaEspecialResultado(boleto) + juegos.obtenerUltimoSorteo() + " veces y tu boleto es: " + boleto, false, dondeVaElColor(PrimitivaConstantes.COLORES.values()[numIntroducido], false), dondeVaElColor(PrimitivaConstantes.COLORES.NULL, true)));
            default -> System.err.println(PrimitivaConstantes.MSG_ERROR);
        }
        await();
    }

    /**
     * Para decorar el array y hacerlo bonito
     * @return el array decorado
     */
    private static final String[] text = new String[]{"E","1ª","2ª","3ª","4ª","5ª","R"};
    private static String mostrarArray(String[] text) {
        StringBuilder sb = new StringBuilder();
        int margin = 1;
        sb.append("\n".repeat(margin));
        for (int i = 0; i < text.length; i++) {
            sb.append(text[i]).append(" total: ").append(juegos.juegoDeMuchosSorteos(boleto)[i]).append("\n");
        }
        return sb + "";
    }
}