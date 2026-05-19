package mx.edu.tecdesoftware.market_backend.persistence.entity;

import jakarta.persistence.*;


@Entity
@Table (name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name="codigo_barras")
    private String codigoBarras;

    @Column(name="precio_venta")
    private Integer precioVenta;

    @Column(name="cantidad_stock")
    private Integer cantidadStock;
    
    private String estado;
}
