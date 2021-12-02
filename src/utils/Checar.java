package utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import model.Item;

/**
 * Clase que contiene métodos estaticos para comprobar si los inputs de un formulario
 * fueron llenados.
 * @author kasparov
 */
public class Checar {

	 /**
	  * Mensaje que se devolverá en caso de que un campo no haya sido llenado
	  * Este mensaje se usará con el metódo String.format donde el placeholder
	  * para el string "%s" será remplazado con el promtText del input en cuestión.
	  */
	private static final String MENSAJE = "El campo %s no ha sido llenado\n";

	/**
	 * Metodo que comprueba si un TextInputControl ha sido llenado por el usuario
	 * @param input El input a checar. Puede ser un TextField, PasswordField o 
	 * cualquier {@code Nodo} que herede de {@code TextInputControl}
	 * @return Un String con el Mensaje indicando que el campo no ha sido llenado.
	 * Regresa un String vacío si el campo fue llenado.
	 * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextInputControl.html">
	 * TextInputControl</a>
	 */
	private static String checarInputTexto(TextInputControl input){
		if (input.getText().length() == 0){
			return String.format(MENSAJE, input.getPromptText());
		}
		return "";
	}

	/**
	 * Metodo que compruba si un {@code ComboBox<Item>} ha sido llenado por el 
	 * usuario.
	 * @param combo un {@code ComboBox} de tipo {@code Item} para evaluar si
	 * el usuario ha escogido una opción
	 * @return Un String con el mensaje indicando que el combo no ha sido llenado.
	 * Regresa un String vacío si el combo fue llenado
	 */
	private static String checarCombo(ComboBox<Item> combo){
		if (combo.getValue().getValor().length() == 0){
			return String.format(MENSAJE, combo.getPromptText());
		}
		return "";
	}

	/**
	 * Metodo que comprueba si los TextInputControl recibidos como paramertros
	 * han sido llenado por el usuario
	 * @param inputs Los inputs a checar. Pueden ser TextField, PasswordField o 
	 * cualquier {@code Nodo} que herede de {@code TextInputControl}
	 * @return Un String con el Mensaje indicando los campos que no han sido
	 * llenados. Regresa un String vacío si todos los campos fueron llenados.
	 * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextInputControl.html">
	 * TextInputControl</a>
	 */
	public static String checarInputsTexto(TextInputControl... inputs){
		StringBuilder sb = new StringBuilder();

		for (TextInputControl input : inputs){
			sb.append(checarInputTexto(input));
		}

		return sb.toString();
	}

	/**
	 * Metodo que compruba si los {@code ComboBox<Item>} pasados como parametros
	 * han sido llenados por el usuario.
	 * @param combos Lista de {@code ComboBox} de tipo {@code Item} para evaluar
	 * si el usuario ha escogido una opción en ellos
	 * @return Un String con el mensaje indicando los combos no ha sido llenados.
	 * Regresa un String vacío si todos los combos fueron llenados
	 * @see model.Item
	 */
	public static String checarCombos(ComboBox<Item>... combos){
		StringBuilder sb = new StringBuilder();

		for (ComboBox combo : combos){
			sb.append(checarCombo(combo));
		}

		return sb.toString();
	}
	
}
