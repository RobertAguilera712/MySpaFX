package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import model.Empleado;
import model.Item;


public class Utils {

	public final static Item filtrosEstatus[] = {new Item("Activos", "1"), new Item("Inactivos", "0")};

	public static Empleado cuentaEmpleado;

	public static Window getCurrentWindow(ActionEvent event){
		return ((Node)event.getTarget()).getScene().getWindow();
	}

	public static Scene getCurrentScene(ActionEvent event){
		return ((Node)event.getTarget()).getScene();
	}

	public static String encodeImage(Image image){
		if (image == null){
			return "";
		}
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", byteOutput);
		} catch (IOException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Base64.getEncoder().encodeToString(byteOutput.toByteArray());
	}

	public static Image decodeImage(String imgString){
		return new Image(new ByteArrayInputStream(Base64.getDecoder().decode(imgString)));
	}

	public static Image readImage() throws Exception{
		FileChooser fc = new FileChooser();
		fc.setTitle("Seleccionar foto");
		fc.getExtensionFilters().clear();
		ExtensionFilter txtFilter = new ExtensionFilter("imagenes", "*.png", "*.jpeg", "*.jpg", "*.webp");
		fc.getExtensionFilters().add(txtFilter);
		fc.setSelectedExtensionFilter(txtFilter);
		Image img = null;
		img = new Image(new FileInputStream(fc.showOpenDialog(new Stage())));
		return img;
	}
	
}
