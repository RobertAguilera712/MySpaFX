package model;

/**
 * Un {@code Item} representa una opción que aparecerá en un {@code ComboBox}.
 * Un {@code Item} contiene un {@link texto} y un {@link valor}
 */
public class Item {

	/**
	 * Texto que se mostrará en el {@code ComboBox}
	 */
	private String texto;

	/**
	 * Texto que se tomará como valor una vez el usuario haya elejido un 
	 * {@code Item} de un {@code ComboBox}
	 */
	private String valor;

	/**
	 * Crea un {@code Item} con asignando los valores pasados como parametros
	 * a los valores del Item
	 * @param texto Valor que se asignará a la propieda {@link texto} de este
	 * {@code Item}.
	 * @param valor Valor que se asignará a la propiedad {@link valor} de este
	 * {@code Item}.
	 */
	public Item(String texto, String valor) {
		this.texto = texto;
		this.valor = valor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String text) {
		this.texto = text;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String value) {
		this.valor = value;
	}

	@Override
	public String toString() {
		return texto;
	}

	
}
