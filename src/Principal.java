import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void menu() {
        System.out.println("*************************************************");
        System.out.println("** C O N V E R T I D O R   D E   M O N E D A S **");
        System.out.println("*************************************************");
        System.out.println("*    Sea bienvenido al conversor de monedas     *");
        System.out.println("*                                               *");
        System.out.println("*    1. Dólar ==>> Peso argentino               *");
        System.out.println("*    2. Peso argentino ==>> Dólar               *");
        System.out.println("*    3. Dólar ==>> Real brasileño               *");
        System.out.println("*    4. Real brasileño ==>> Dólar               *");
        System.out.println("*    5. Dólar ==>> Peso colombiano              *");
        System.out.println("*    6. Peso colombiano ==>> Dólar              *");
        System.out.println("*    7. Salir                                   *");
        System.out.println("*************************************************");
        System.out.print("  Elija una opción válida: ");
    }

    public static void main(String[] args) {
        boolean muestraMenu = true;

        while ( muestraMenu ) {
            menu();
            Scanner lectura = new Scanner(System.in);
            try {
                var opcion = Integer.valueOf(lectura.nextLine());
                if ((opcion < 1) || (opcion > 7)) {
                    System.out.println("Debe ingresar una opción entre 1 y 7");
                    muestraMenu = true;
                } else if (opcion == 7) {
                    System.out.println("Gracias por usar el conversor. ");
                    muestraMenu = false;
                } else {
                    Conversor  conversor = new Conversor();
                    double conversion = conversor.obtieneValor(opcion);
                    System.out.println("    Valor --> " + conversion );
                    System.out.println("");
                    System.out.println("... Presione cualquier tecla para volver al menú ...");
                    Scanner teclado = new Scanner(System.in);
                    var tecla = teclado.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar una opción numérica: " + e.getMessage());
                muestraMenu = true;

            } catch (RuntimeException e) {
                System.out.println("Error desconocido: " + e.getMessage());
                System.out.println("Finalizando la aplicación");
                muestraMenu = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
