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
	private static final String MENSAJE = "El campo %s no ha sido llando\n";

	/**
	 * Metodo que comprueba si un TextInputControl ha sido llenado por el usuario
	 * @param input 
	 * @return 
	 */
	private static String checarInputTexto(TextInputControl input){
		if (input.getText().length() == 0){
			return String.format(MENSAJE, input.getPromptText());
		}
		return "";
	}

	private static String checarCombo(ComboBox<Item> combo){
		if (combo.getValue().getValue().length() == 0){
			return String.format(MENSAJE, combo.getPromptText());
		}
		return "";
	}

	public static String checarInputsTexto(TextInputControl... inputs){
		StringBuilder sb = new StringBuilder();

		for (TextInputControl input : inputs){
			sb.append(checarInputTexto(input));
		}

		return sb.toString();
	}

	public static String checarCombos(ComboBox<Item>... combos){
		StringBuilder sb = new StringBuilder();

		for (ComboBox combo : combos){
			sb.append(checarCombo(combo));
		}

		return sb.toString();
	}
	
}
