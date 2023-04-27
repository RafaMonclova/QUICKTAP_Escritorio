/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.*;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class FileUtils {

    public static byte[] fileToBytes(File file) {
        byte[] fileBytes = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            fileBytes = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileBytes;
    }

    public static Image bytesToImage(byte[] bytes) {
        Image image = new Image(new ByteArrayInputStream(bytes));
        return image;
    }

    public static byte[] imageToBytes(Image image, String format) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = out.toByteArray();
        return byteArray;
    }

}
