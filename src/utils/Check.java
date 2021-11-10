package utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import model.Item;

public class Check {

	private static final String mensaje = "El campo %s no ha sido llando\n";

	private static String checkTextInput(TextInputControl input){
		if (input.getText().length() == 0){
			return String.format(mensaje, input.getPromptText());
		}
		return "";
	}

	private static String checarCombo(ComboBox<Item> combo){
		if (combo.getValue().getValue().length() == 0){
			return String.format(mensaje, combo.getPromptText());
		}
		return "";
	}

	public static String checkTextInputs(TextInputControl... inputs){
		StringBuilder sb = new StringBuilder();

		for (TextInputControl input : inputs){
			sb.append(checkTextInput(input));
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
