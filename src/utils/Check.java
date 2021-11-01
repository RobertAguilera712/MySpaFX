package utils;

import javafx.scene.control.TextInputControl;

/**
 * The propose of this class is to provide methods to check if inputs had been filled by the user
 * @author kasparov
 */
public class Check {

	private static String checkTextInput(TextInputControl input){
		if (input.getText().length() == 0){
			return String.format("El campo %s no ha sido llenado\n", input.getPromptText());
		}
		return "";
	}

	/**
	 * This method takes TexInputControl objects as parameters and checks which of them are not filled.
	 * @param inputs TexInputControl instances. It can be a TextField of PasswordFied
	 * @return Returns a String indicating which inputs hasn't been filled. Returns an empty String otherwise.
	 */
	public static String checkTextInputs(TextInputControl... inputs){
		StringBuilder sb = new StringBuilder();

		for (TextInputControl input : inputs){
			sb.append(checkTextInput(input));
		}

		return sb.toString();
	}
	
}
