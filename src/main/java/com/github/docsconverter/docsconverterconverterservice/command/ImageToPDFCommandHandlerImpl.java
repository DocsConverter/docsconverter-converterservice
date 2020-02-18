package com.github.docsconverter.docsconverterconverterservice.command;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageToPDFCommandHandlerImpl implements CommandHandler<Void, File, File> {
    @Override
    public Void execute(File input, File output) throws IOException {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(output));
            document.open();

            document.newPage();
            Image image = Image.getInstance(input.getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsolute(PageSize.A4);
            document.add(image);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }
}
