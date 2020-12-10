package modelo;

import java.time.LocalDate;

public class Producto {
	private String codProd;
	private String seccion;
	private String nombreProd;
	private Double precio;
	private LocalDate fecha;
	private Boolean importado;
	private String pais;
	
		
	
	public Producto(String codProd, String seccion, String nombreProd, Double precio, LocalDate fecha,
			Boolean importado, String pais) {
		super();
		this.codProd = codProd;
		this.seccion = seccion;
		this.nombreProd = nombreProd;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.pais = pais;
	}
	public String getCodProd() {
		return codProd;
	}
	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getNombreProd() {
		return nombreProd;
	}
	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Boolean getImportado() {
		return importado;
	}
	public void setImportado(Boolean importado) {
		this.importado = importado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	 
	@Override
	public String toString() {
		return "Producto [codProd=" + codProd + ", seccion=" + seccion + ", nombreProd=" + nombreProd + ", precio="
				+ precio + ", fecha=" + fecha + ", importado=" + importado + ", pais=" + pais + "]";
	}
}
