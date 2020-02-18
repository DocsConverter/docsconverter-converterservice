package com.github.docsconverter.docsconverterconverterservice.command;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;

import static com.itextpdf.kernel.colors.DeviceRgb.BLACK;

public class TextToPDFCommandHandlerImpl implements CommandHandler<Void, String, File> {

    @Override
    public Void execute(String input, File output) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(output));

        try (Document document = new Document(pdfDocument)){

            Text text = new Text(input)
                    .setFontColor(BLACK)
                    .setFont(PdfFontFactory.createFont());

            Paragraph paragraph = new Paragraph(text);

            document.add(paragraph);
        }

        return null;
    }
}
