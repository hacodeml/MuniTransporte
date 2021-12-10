import model.Vecino;

import java.util.Scanner;

public class Menu {
    static Municipalidad muni = new Municipalidad();

    public static void menuPrincipal(){
        Scanner sc = new Scanner (System.in);
        System.out.println("******************************************");
        System.out.println("              MENÚ PRINCIPAL              ");
        System.out.println("******************************************");
        System.out.println ("1- Registro de vecinos");
        System.out.println ("2- Consulta de vecinos");
        System.out.println ("0- Salir del programa\n");
        System.out.println ("Ingrese una opción:");
        int opcion = sc.nextInt ();

        switch (opcion) {
            case 1 :    menuRegistrarVecino();break;

            case 2 :    listarVecinos();
                        menuPrincipal();break;

            case 0 :    System.exit (0);
            default :
                System.out.println ("Opcion incorrecta");
                menuPrincipal();
        }
    }

    /******* MANTENIMIENTO DE VECINOS *******/
    public static void menuRegistrarVecino(){
        Scanner sc = new Scanner (System.in);
        System.out.println("******************************************");
        System.out.println("     MENÚ DE MANTENIMIENTO DE VECINOS     ");
        System.out.println("******************************************");
        System.out.println("1- Registrar Miembros del club de ecologia");
        System.out.println("2- Registrar Adulto Mayor");
        System.out.println("0- Retornar\n");
        System.out.println("Ingrese una opción:");
        int opcion=sc.nextInt();

        switch (opcion) {
            case 1 :    registrarVecino(1);
                        menuRegistrarVecino();break;

            case 2 :    registrarVecino(2);
                        menuRegistrarVecino();break;

            case 0 : menuPrincipal();
            default :
                System.out.println ("Opcion incorrecta");
                menuRegistrarVecino();
        }
    }

    public static void registrarVecino(int tipo){
        Scanner sc = new Scanner (System.in);

        System.out.println("Ingrese los siguientes datos:\n");
        System.out.print("Nombres :");
        String nombre=sc.nextLine();
        System.out.print("Apellidos :");
        String apellido=sc.nextLine();
        System.out.print("DNI :");
        String dni=sc.next();
        System.out.print("Teléfono :");
        String telefono=sc.next();
        System.out.print("Estado civil :");
        String estadoCivil=sc.next();
        System.out.print("Edad :");
        int edad=Integer.parseInt(sc.next());
        System.out.print("Email\t:");
        String correoElectronico=sc.next();

        Vecino v = FactoryVecino.obtenerVecino(tipo,nombre,dni,telefono,estadoCivil,""+edad,correoElectronico);

        try {
            muni.registrarVecino(v);

            System.out.println("Vecino registrado correctamente!!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void listarVecinos(){
        muni.listarVecinosAll();
    }

    public static void limpiarPantalla(){
    }
}
