package com.tienda.usuarios.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "producto") // Se conecta a la tabla producto de tu SQL
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precioCompra;

    @Column(nullable = false)
    private Double precioVenta;

    @Column(nullable = false)
    private Double ivaCompra;

    // Como la tabla exige un idProveedor, le pondremos un valor por defecto
    @Column(nullable = false)
    private Integer idProveedor = 1; 

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(Double precioCompra) { this.precioCompra = precioCompra; }
    public Double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(Double precioVenta) { this.precioVenta = precioVenta; }
    public Double getIvaCompra() { return ivaCompra; }
    public void setIvaCompra(Double ivaCompra) { this.ivaCompra = ivaCompra; }
    public Integer getIdProveedor() { return idProveedor; }
    public void setIdProveedor(Integer idProveedor) { this.idProveedor = idProveedor; }
}