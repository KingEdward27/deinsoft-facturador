/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.BarcodeQRCode;
import java.io.File;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author EDWARD
 */
public class CodigoQR {
//    public CodigoQR() throws BadElementException
//    {
//        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("https://memorynotfound.com", 1000, 1000, null);
//        Image codeQrImage = barcodeQRCode.getImage();
//        codeQrImage.scaleAbsolute(100, 100);
//        document.add(codeQrImage);
//    }
    // Image properties
    private static final int qr_image_width = 400;
    private static final int qr_image_height = 400;
    private static final String IMAGE_FORMAT = "png";
    private static final String IMG_PATH = "qrcode.png";
    public static String GenerarQR (String pathToGenerate, String data) throws Exception{
 
        // Encode URL in QR format
        BitMatrix matrix;
        Writer writer = new QRCodeWriter();
        try {

            matrix = writer.encode(data, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
            // Create buffered image to draw to
            BufferedImage image = new BufferedImage(qr_image_width,
                    qr_image_height, BufferedImage.TYPE_INT_RGB);

            // Iterate through the matrix and draw the pixels to the image
            for (int y = 0; y < qr_image_height; y++) {
                for (int x = 0; x < qr_image_width; x++) {
                    int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                    image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                }
            }
            // Write the image to a file
            FileOutputStream qrCode = new FileOutputStream(pathToGenerate + IMG_PATH);
            ImageIO.write(image, IMAGE_FORMAT, qrCode);

            qrCode.close();
            return pathToGenerate + IMG_PATH;
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return "";
        }catch (Exception e) {
            e.printStackTrace(System.err);
            return "";
        }
    }
    public File generateQR(File file, String text, int h, int w) throws Exception {

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, w, h);

        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrix.getWidth(), matrix.getHeight());
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ImageIO.write(image, "png", file);

        return file;

    }

    public String decoder(File file) throws Exception {

        FileInputStream inputStream = new FileInputStream(file);

        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // decode the barcode
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return new String(result.getText());
    }
}
