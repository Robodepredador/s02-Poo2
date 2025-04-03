/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea.solid;

/**
 *
 * @author User
 */
interface CalculadorPago {
    double calcularPago(double salario);
}

class CalculadorPagoGerente implements CalculadorPago {
    public double calcularPago(double salario) {
        return salario + 1000;
    }
}

class CalculadorPagoDesarrollador implements CalculadorPago {
    public double calcularPago(double salario) {
        return salario;
    }
}

class CalculadorPagoPracticante implements CalculadorPago {
    public double calcularPago(double salario) {
        return salario * 0.5;
    }
}

class Empleado {
    private String nombre;
    private double salario;
    private CalculadorPago calculadorPago;
    
    public Empleado(String nombre, double salario, CalculadorPago calculadorPago) {
        this.nombre = nombre;
        this.salario = salario;
        this.calculadorPago = calculadorPago;
    }
    
    public double calcularPago() {
        return calculadorPago.calcularPago(salario);
    }
    
    public String getNombre() {
        return nombre;
    }
}

interface Persistencia {
    void guardarEmpleado(Empleado empleado);
}

interface Reporteador {
    void generarReporte(Empleado empleado);
}

class DatabasePersistencia implements Persistencia {
    public void guardarEmpleado(Empleado empleado) {
        System.out.println("Guardando " + empleado.getNombre() + " en BD...");
    }
}

class ReporteConsola implements Reporteador {
    public void generarReporte(Empleado empleado) {
        System.out.println("Generando reporte para " + empleado.getNombre() + "...");
    }
}

class SistemaGestionEmpleados {
    private final Persistencia persistencia;
    private final Reporteador reporteador;
    
    public SistemaGestionEmpleados(Persistencia persistencia, Reporteador reporteador) {
        this.persistencia = persistencia;
        this.reporteador = reporteador;
    }
    
    public void procesarEmpleado(Empleado empleado) {
        double pago = empleado.calcularPago();
        System.out.println("Pago calculado: " + pago);
        persistencia.guardarEmpleado(empleado);
        reporteador.generarReporte(empleado);
    }
}

public class TareaSOLID {
    public static void main(String[] args) {
        Persistencia persistencia = new DatabasePersistencia();
        Reporteador reporteador = new ReporteConsola();
        SistemaGestionEmpleados sistema = new SistemaGestionEmpleados(persistencia, reporteador);
        
        Empleado gerente = new Empleado("Juan", 5000, new CalculadorPagoGerente());
        Empleado desarrollador = new Empleado("Ana", 3000, new CalculadorPagoDesarrollador());
        Empleado practicante = new Empleado("Luis", 1000, new CalculadorPagoPracticante());
        
        sistema.procesarEmpleado(gerente);
        sistema.procesarEmpleado(desarrollador);
        sistema.procesarEmpleado(practicante);
    }
}