package com.example.kyc_backend_report.config;

import com.example.kyc_backend_report.dto.CustomerDetailsDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.net.URL;
import com.lowagie.text.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public ByteArrayInputStream generateCustomerPdf(CustomerDetailsDto dto) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font bodyFont = new Font(Font.HELVETICA, 12);

            Paragraph title = new Paragraph("Customer Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Name: " + dto.getFirstName() + " " + dto.getLastName(), bodyFont));
            document.add(new Paragraph("Email: " + dto.getEmail(), bodyFont));
            document.add(new Paragraph("Phone: " + dto.getPhoneNumber(), bodyFont));
            document.add(new Paragraph("Date of Birth: " + dto.getDateOfBirth(), bodyFont));
            document.add(new Paragraph("Status: " + dto.getEmploymentStatus(), bodyFont)); 
            document.add(new Paragraph("County: " + dto.getCounty(), bodyFont));              
            document.add(new Paragraph("Front ID:", bodyFont));
try {
    Image frontImage = Image.getInstance(new URL(dto.getFrontPhotoIdUrl()));
    frontImage.scaleToFit(300, 200); // Resize image (optional)
    frontImage.setAlignment(Element.ALIGN_LEFT);
    document.add(frontImage);
} catch (Exception e) {
    document.add(new Paragraph("Unable to load front ID image.", bodyFont));
}

// Add back ID image
document.add(new Paragraph("Back ID:", bodyFont));
try {
    Image backImage = Image.getInstance(new URL(dto.getBackPhotoIdUrl()));
    backImage.scaleToFit(300, 200); // Resize image (optional)
    backImage.setAlignment(Element.ALIGN_LEFT);
    document.add(backImage);
} catch (Exception e) {
    document.add(new Paragraph("Unable to load back ID image.", bodyFont));
}


            document.close();
        } catch (DocumentException e) {
            e.printStackTrace(); // to prints error to help in debugging if there's a problem generating pdf
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
