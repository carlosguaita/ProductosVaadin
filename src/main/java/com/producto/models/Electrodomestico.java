package com.producto.models;

import java.util.Scanner;

public class Electrodomestico extends Producto {

    private String fabricante;
    private double voltaje;

    private int aniosGarantia;

    public Electrodomestico(String tipo, String nombre, double precio, String codigo, int cantidad, String marca, String fabricante, double voltaje, int aniosGarantia) {
        super(tipo, nombre, precio, codigo, cantidad, marca);
        this.fabricante = fabricante;
        this.voltaje = voltaje;
        this.aniosGarantia = aniosGarantia;
    }

    public Electrodomestico() {
    }

    @Override
    public void imprimirProducto(){
        System.out.println("EL producto tecnológico tiene las siguientes características");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Precio: "+this.precio);
        System.out.println("Código: "+this.codigo);
        System.out.println("Cantidad: "+this.cantidad);
        System.out.println("Marca: "+this.marca);
        System.out.println("Fabricante: "+this.fabricante);
        System.out.println("Voltaje: "+this.voltaje);
        System.out.println("Años Garantìa: "+this.aniosGarantia);

    }
    @Override
    public void ingresarProducto(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del producto");
        this.nombre=sc.next();
        System.out.println("Ingrese el precio del producto");
        this.precio=sc.nextDouble();
        System.out.println("Ingrese el código del producto");
        this.codigo=sc.next();
        System.out.println("Ingrese el cantidad del producto");
        this.cantidad=sc.nextInt();
        System.out.println("Ingrese marca del producto");
        this.marca=sc.next();
        System.out.println("Ingrese el fabricante del producto");
        this.fabricante=sc.next();
        System.out.println("Ingrese los años de garantia del producto");
        this.aniosGarantia=sc.nextInt();
        System.out.println("Ingrese el voltaje del producto");
        this.voltaje=sc.nextDouble();
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(double voltaje) {
        this.voltaje = voltaje;
    }

    public int getAniosGarantia() {
        return aniosGarantia;
    }

    public void setAniosGarantia(int aniosGarantia) {
        this.aniosGarantia = aniosGarantia;
    }
}
