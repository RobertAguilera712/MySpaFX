package model;

public enum Estatus {
	INACTIVO(0), ACTIVO(1);

	private final int valor;

	private Estatus(int valor){
		this.valor = valor;
	}

	public int getValor(){
		return this.valor;
	}
	
}
