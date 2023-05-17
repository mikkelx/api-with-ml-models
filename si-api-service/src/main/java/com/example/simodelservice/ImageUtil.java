//package com.example.simodelservice;
//
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//
//public class ImageUtil {
//    public void readImage() {
//        File file = new File("C:\\Studia\\SI\\Projekt\\images\\cat.jpg");
//        byte[] imageBytes = new byte[(int) file.length()];
//        try (FileInputStream inputStream = new FileInputStream(file)) {
//            inputStream.read(imageBytes);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static BufferedImage loadImage(String filePath) throws IOException {
//        File file = new File(filePath);
//        BufferedImage image = ImageIO.read(file);
//        return image;
//    }
//
//    public static byte[] readImageBytes(String imagePath) throws IOException {
//        return Files.readAllBytes(Paths.get(imagePath));
//    }
//}