import model.Bus;
import model.Vecino;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    //Uso de Singleton
    private static final Municipalidad muni = Municipalidad.getInstancia();

    public static void menuPrincipal(){
        Scanner sc = new Scanner (System.in);
        System.out.println("******************************************");
        System.out.println("              MENÚ PRINCIPAL              ");
        System.out.println("******************************************");
        System.out.println ("1- Registro de vecinos");
        System.out.println ("2- Consulta de vecinos");
        System.out.println ("3- Registro de buses");
        System.out.println ("4- Consulta de buses");
        System.out.println ("5- Registro de reservas");
        System.out.println ("6- Consulta de reservas");
        System.out.println ("7- Listar disponibilidad por bus");
        System.out.println ("8- Obtener promedio edad Vecinos");
        System.out.println ("9- Obtener Vecinos que tienen Obsequio");
        System.out.println ("10- Obtener promedio edad Vecinos Club Ecología sin Obsequio");
        System.out.println ("0- Salir del programa\n");
        System.out.println ("Ingrese una opción:");
        int opcion = sc.nextInt ();

        switch (opcion) {
            case 1 :    menuRegistrarVecino();break;

            case 2 :    listarVecinos();
                        menuPrincipal();break;

            case 3 :    registrarBus();
                        menuPrincipal();break;

            case 4 :    listarBus();
                        menuPrincipal();break;

            case 5 :    registrarReserva();
                        menuPrincipal();break;

            case 6 :    listarReserva();
                        menuPrincipal();break;

            case 7 :    listarDisponibilidad();
                        menuPrincipal();break;

            case 8 :   obtenerPromedioEdadVecinosAdultoMayor();
                menuPrincipal();break;

            case 9 :   obtenerVecinosConObsequio();
                menuPrincipal();break;

            case 10 :   obtenerPromedioEdadClubEcoSinObsequio();
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
        muni.listarVecinosAll(muni.getVecinos());
    }

    public static void limpiarPantalla(){
    }

    public static void obtenerPromedioEdadVecinosAdultoMayor(){
        double promedio = muni.obtenerPromedioEdadVecinosAdultoMayor();

        if(promedio > 0){
            System.out.println("El promedio de edades de vecinos Adulto Mayor es: "+promedio);

        } else {
            System.out.println("Aún no se registraron vecinos de tipo Adulto Mayor!");
        }
    }

    public static void obtenerVecinosConObsequio(){
        List<Vecino> arregloVecinosObsequio = muni.obtenerVecinosQueTienenObsequio();

        for(Vecino v: arregloVecinosObsequio){
            System.out.println(v.toString());
        }
    }

    public static void obtenerPromedioEdadClubEcoSinObsequio(){
        double promedio = muni.promedioEdadVecinosClubEcoSinObsequio();
        System.out.println("El promedio de edades de vecinos Adulto Mayor es: "+promedio);

        if(promedio > 0){
            System.out.println("El promedio de edades de vecinos del club de ecología sin obsequio es: "+promedio);

        } else {
            System.out.println("Aún no se registraron vecinos del club de ecología!");
        }
    }

    /******** MANTENIMIENTO DE BUSES ********/

    public static void registrarBus(){
        Scanner sc = new Scanner (System.in);

        System.out.println("Ingrese los datos del bus:\n");
        System.out.print("Capacidad :");
        int capacidad = Integer.parseInt(sc.next());

        Bus bus = new Bus();
        bus.setCapacidadMax(capacidad);

        try {
            muni.registrarBus(bus);
            System.out.println("Bus registrado correctamente");
        } catch (IOException e) {
            System.out.println("Error al registrar el bus");
        }
    }

    public static void listarBus(){
        muni.listarBus();
    }

    /******* MANTENIMIENTO DE RESERVA *******/
    public static void registrarReserva(){
        Scanner sc = new Scanner (System.in);
        System.out.println("* Ingrese su dni:\n");
        String dni=sc.next();

        Vecino veci = muni.buscarVecinoPorDni(dni);
        if(veci != null){
            System.out.println("* Fecha de reserva (yyyyddmm):\n");
            String fecha=sc.next();

            Bus bus = muni.obtenerBusDisponible(fecha);
            if(bus!=null){
                try {
                    muni.registrarReserva(veci,bus,fecha);

                    System.out.println("Asiento reservado correctamente");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("No hay buses disponibles para esta fecha");
            }
        }else{
            System.out.println("Vecino no está registrado, por favor registrese primero !!!!");
        }
    }

    public static void listarReserva(){
        muni.listarReservas();
    }

    public static void listarDisponibilidad(){
        Scanner sc = new Scanner (System.in);
        System.out.println("* Fecha de reserva (yyyyddmm):\n");
        String fecha=sc.next();

        muni.listarAsientosDisponiblesPorReservar(fecha);
    }
}
