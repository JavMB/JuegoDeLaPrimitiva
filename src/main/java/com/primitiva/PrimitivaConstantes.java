package com.primitiva;

import javax.print.DocFlavor;
import java.util.Random;
import java.util.Scanner;

public class PrimitivaConstantes {
  // Entrada y aleatoriedad
  public static final Scanner sc = new Scanner(System.in);
  public static final Random rnd = new Random();

  // Constantes específicas de la Primitiva española
  public static final int NUMERO_MIN = 1;
  public static final int NUMERO_MAX = 49;
  public static final int TOTAL_NUMEROS = 6;

  public static final int REINTEGRO_MIN = 0;
  public static final int REINTEGRO_MAX = 9;

  public static final int COUNTDOWN = 3;

  public static final int CANT_SORTEOS = 10000;

  // Mensajes comunes
  public static final String MSG_APUESTA_MANUAL = "*** Selección manual de números ***";
  public static final String MSG_APUESTA_AUTOMATICA = "*** Selección automática de números ***";
  public static final String MSG_INTRODUZCA_NUMERO =
      "*** Introduzca un número entre " + NUMERO_MIN + " y " + NUMERO_MAX + ": ***";
  public static final String ERROR = """
           _____ ____  ____   ___  ____ \s
          | ____|  _ \\|  _ \\ / _ \\|  _ \\\s
          |  _| | |_) | |_) | | | | |_) |
          | |___|  _ <|  _ <| |_| |  _ <\s
          |_____|_| \\_\\_| \\_\\\\___/|_| \\_\\""";
  public static final String MSG_ERROR = ERROR + "\n" + "*** Valor introducido no valido, vuelve a intentarlo ***";

  // Caracteres para presentación
  public static final String LINEA_SEPARADORA = "----------------------------------------";

  public enum COLORES {
    NEGRO, ROJO, VERDE, AMARILLO, AZUL, MORADO, AZULCLARO,BLANCO,NULL
  }

  public enum TIPOCOLOR {
    FONDO,TEXTO
  }

  public static int colores(COLORES color, TIPOCOLOR donde) {
    switch (donde) {
      case TEXTO -> {
        switch (color) {
          case NEGRO -> {
            return 30;
          }
          case ROJO -> {
            return 31;
          }
          case VERDE -> {
            return 32;
          }
          case AMARILLO -> {
            return 33;
          }
          case AZUL -> {
            return 34;
          }
          case MORADO -> {
            return 35;
          }
          case AZULCLARO -> {
            return 36;
          }
          case BLANCO -> {
            return 37;
          }
          case NULL -> {
            return 0;
          }
        }
      }
      case FONDO -> {
        switch (color) {
          case NEGRO -> {
            return 40;
          }
          case ROJO -> {
            return 41;
          }
          case VERDE -> {
            return 42;
          }
          case AMARILLO -> {
            return 43;
          }
          case AZUL -> {
            return 44;
          }
          case MORADO -> {
            return 45;
          }
          case AZULCLARO -> {
            return 46;
          }
          case BLANCO -> {
            return 47;
          }
          case NULL -> {
            return 48;
          }
        }
      }
    }
    return 0;
  }

  public static final String NOMBREJUEGO = """
               _                              _        _      \s
              | |_   _  ___  __ _  ___     __| | ___  | | __ _\s
           _  | | | | |/ _ \\/ _` |/ _ \\   / _` |/ _ \\ | |/ _` |
          | |_| | |_| |  __/ (_| | (_) | | (_| |  __/ | | (_| |
           \\___/ \\__,_|\\___|\\__, |\\___/ _ \\__,_|\\___| |_|\\__,_|
           _ __  _ __(_)_ __|___/(_) |_(_)_   ____ _          \s
          | '_ \\| '__| | '_ ` _ \\| | __| \\ \\ / / _` |         \s
          | |_) | |  | | | | | | | | |_| |\\ V / (_| |         \s
          | .__/|_|  |_|_| |_| |_|_|\\__|_| \\_/ \\__,_|         \s
          |_|                                                 \s""";
  public static final String BOLETO = """
           ____   ___  _     _____ _____ ___ \s
          | __ ) / _ \\| |   | ____|_   _/ _ \\\s
          |  _ \\| | | | |   |  _|   | || | | |
          | |_) | |_| | |___| |___  | || |_| |
          |____/ \\___/|_____|_____| |_| \\___/\s""";
  public static final String CREARBOLETOSTRING = """
            ____ ____  _____    _    ____ \s
           / ___|  _ \\| ____|  / \\  |  _ \\\s
          | |   | |_) |  _|   / _ \\ | |_) |
          | |___|  _ <| |___ / ___ \\|  _ <\s
           \\____|_| \\_\\_____/_/   \\_\\_| \\_\\""";
  public static final String TUBOLETOTOSTRING = """
           _____ _   _\s
          |_   _| | | |
            | | | | | |
            | | | |_| |
            |_|  \\___/\s""";
  public static final String RESULTADOS = """
           ____  _____ ____  _   _ _   _____  _    ____   ___  ____ \s
          |  _ \\| ____/ ___|| | | | | |_   _|/ \\  |  _ \\ / _ \\/ ___|\s
          | |_) |  _| \\___ \\| | | | |   | | / _ \\ | | | | | | \\___ \\\s
          |  _ <| |___ ___) | |_| | |___| |/ ___ \\| |_| | |_| |___) |
          |_| \\_\\_____|____/ \\___/|_____|_/_/   \\_\\____/ \\___/|____/\s""" + "\n";

  public static final String DESPEDIDA = """
              _    ____ ___ ___  ____      \s
             / \\  |  _ \\_ _/ _ \\/ ___|     \s
            / _ \\ | | | | | | | \\___ \\     \s
           / ___ \\| |_| | | |_| |___) | _ _\s
          /_/   \\_\\____/___\\___/|____(_|_|_)""";
}
