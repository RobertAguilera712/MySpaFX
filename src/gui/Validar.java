package gui;

import javafx.scene.control.TextInputControl;


public class Validar {

	private static String validarCampoTexto(TextInputControl input){
		if (input.getText().length() == 0){
			return String.format("El campo %s no ha sido llenado\n", input.getPromptText());
		}
		return "";
	}

	public static String validarCamposTexto(TextInputControl... inputs){
		StringBuilder sb = new StringBuilder();

		for (TextInputControl input : inputs){
			sb.append(validarCampoTexto(input));
		}

		return sb.toString();
	}
	
}
