import dao.BDBuses;
import dao.BDReserva;
import dao.BDVecino;
import model.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Municipalidad {
    private static Municipalidad instancia;
    private List<Vecino> vecinos;
    private List<Reserva> reservas;
    private List<Bus> buses;

    private Municipalidad(){
        vecinos = new ArrayList<>();
        reservas = new ArrayList<>();
        buses = new ArrayList<>();

        try {
            vecinos = BDVecino.obtenerVecinos();
            buses = BDBuses.obtenerBuses();
            reservas = BDReserva.obtenerReservas();

        } catch (IOException e) {
            vecinos = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            vecinos = new ArrayList<>();
        }
    }

    public static Municipalidad getInstancia(){
        if(instancia == null){
            instancia = new Municipalidad();
        }
        return instancia;
    }

    public List<Vecino> getVecinos() {
        return vecinos;
    }

    /*********************** MANTENIMIENTO DE VECINOS ***********************/

    public void registrarVecino(Vecino vecino) throws Exception {
        if (vecino.getEdad()>=18) {
//            if (!this.vecinos.isEmpty()) {
                Vecino vecinoBuscado = buscarVecinoPorDni(vecino.getDni());

                if(vecinoBuscado !=null){
                    throw new Exception("Error !!! .. No se pudo registrar, DNI ya está registrado");
                }

                this.vecinos.add(vecino);

                BDVecino.grabarVecinos(vecinos);

//            }else{
//                throw new Exception("Error !!!! No existe lista");
//            }
        } else {
            throw new Exception("Error !!!! .... Vecino no es mayor de edad: " + vecino.toString());
        }
    }

    public Vecino buscarVecinoPorDni(String dni){
        for (Vecino v: vecinos) {
            if(v.getDni().equalsIgnoreCase(dni)){
                return v;
            }
        }
        return null;
    }

    public void listarVecinosAll(List<Vecino> lbeVecinos){
        System.out.format("%s\t%20s\t%15s\t%15s\t%25s\t%15s\t%20s\t%20s \n",
                "Nombres","DNI","Edad","Teléfono","Email","Estado Civil","Tipo","Obsequio");
        System.out.println("=======================================================================================================================================================");

        int registros = lbeVecinos.size();

        if(registros == 0){
            System.out.println("No hay registros para mostrar");
        }

        for (Vecino v:lbeVecinos) {
            String tipo = "";
            String obsequio = "";

            if(v instanceof ClubEcologia){
                tipo ="Club Ecología";
                obsequio = ((ClubEcologia)v).obsequio();
            }else{
                tipo ="Adulto Mayor";
                obsequio = ((AdultoMayor)v).obsequio();
            }

            System.out.format("%s\t%20s\t%15s\t%15s\t%25s\t%15s\t%20s\t%20s\n",
                    v.getNombre(),
                    v.getDni(),
                    v.getEdad(),
                    v.getTelefono(),
                    v.getCorreoElectronico(),
                    v.getEstadoCivil(),
                    tipo,
                    obsequio);
        }

        System.out.println("\n");

    }

    public void listarVecino(Vecino vecino){
        System.out.format("%s\t%20s\t%15s\t%15s\t%25s\t%15s\t%20s\t%20s \n",
                "Nombres","DNI","Edad","Teléfono","Email","Estado Civil","Tipo","Obsequio");
        System.out.println("=======================================================================================================================================================");


        String tipo = "";
        String obsequio = "";

        if(vecino instanceof ClubEcologia){
            tipo ="Club Ecología";
            obsequio = ((ClubEcologia)vecino).obsequio();
        }else{
            tipo ="Adulto Mayor";
            obsequio = ((AdultoMayor)vecino).obsequio();
        }

        System.out.format("%s\t%20s\t%15s\t%15s\t%25s\t%15s\t%20s\t%20s\n",
                vecino.getNombre(),
                vecino.getDni(),
                vecino.getEdad(),
                vecino.getTelefono(),
                vecino.getCorreoElectronico(),
                vecino.getEstadoCivil(),
                tipo,
                obsequio);

        System.out.println("\n");

    }

    /************************* MANTENIMIENTO DE BUSES *************************/
    public void registrarBus(Bus bus) throws IOException {
        int registros = buses.size();
        bus.setNumeroBus(""+(registros+1));

        buses.add(bus);
        BDBuses.grabarBuses(buses);
    }

    public void listarBus(){
        System.out.format("%s\t%20s\n","Código","Capacidad");
        for (Bus bus : buses) {
            System.out.format("%s\t%20s\n",
                    bus.getNumeroBus(),
                    bus.getCapacidadMax());
        }
    }

    public int obtenerAsientosDisponibles(Bus bus, String fecha) {
        int capacidadBus = bus.getCapacidadMax();

        int numerosAsientosReservados = 0;

        for (Reserva r:reservas) {
            if(r.getBusAsignado().getNumeroBus().equals(bus.getNumeroBus()) && r.getFecha().equals(fecha)){
                numerosAsientosReservados++;
            }
        }
        return   capacidadBus - numerosAsientosReservados;
    }

    public Bus obtenerBusDisponible(String fecha){
        int asientosDisponibles;

        for (Bus bus : buses) {
            asientosDisponibles = obtenerAsientosDisponibles(bus,fecha);
            if(asientosDisponibles>0) return bus;
        }

        return null;
    }

    /*********************** MANTENIMIENTO DE RESERVAS ************************/
    public void registrarReserva(Vecino vecino,Bus bus,String fechaReserva) throws IOException {
        int asientosDisponibles = obtenerAsientosDisponibles(bus,fechaReserva);
        int asientoAsignado = (bus.getCapacidadMax() - asientosDisponibles )+1;

        Reserva reserva = new Reserva(vecino,fechaReserva,asientoAsignado,bus);
        reservas.add(reserva);

        BDReserva.grabarReservas(reservas);
    }

    public void listarReservas(){
        System.out.printf("%s\t%20s\t%20s\t%10s\t%10s\t%20s\n",
                "Fecha","Nombres","Dni","Numero de bus","Asiento","Capacidad","Obsequio");

        String obsequio="";

        for (Reserva r:reservas) {

            if(r.getDniReserva() instanceof ClubEcologia){
                obsequio = ((ClubEcologia)r.getDniReserva()).obsequio();
            }else{
                obsequio = ((AdultoMayor)r.getDniReserva()).obsequio();
            }


            System.out.printf("%s\t%20s\t%20s\t%10s\t%10s\t%10s\t%20s\n",
                    r.getFecha(),
                    r.getDniReserva().getNombre(),
                    r.getDniReserva().getDni(),
                    r.getBusAsignado().getNumeroBus(),
                    r.getAsiento(),
                    r.getBusAsignado().getCapacidadMax(),
                    obsequio);
        }
    }

    public void listarAsientosDisponiblesPorReservar(String fecha){
        int asientosDisponibles;

        System.out.println("Listado de buses " + fecha);
        System.out.printf("%s\t%20s\t%20s \n", "Número de bus","Capacidad Máx","Asientos Disp.");
        for (Bus bus:buses) {
            asientosDisponibles = obtenerAsientosDisponibles(bus,fecha);
            System.out.printf("%s\t%20s\t%20s \n",
                    bus.getNumeroBus(),
                    bus.getCapacidadMax(),
                    asientosDisponibles);
        }
    }
}
