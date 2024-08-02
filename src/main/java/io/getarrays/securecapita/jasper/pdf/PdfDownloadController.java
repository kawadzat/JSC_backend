package io.getarrays.securecapita.jasper.pdf;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/jasper/pdf")
@RequiredArgsConstructor
public class PdfDownloadController {
    private final JasperPdfService jasperPdfService;

    @GetMapping("/asset")
    public ResponseEntity<?> downloadAssetsPdf(@RequestParam("token")UUID token) throws JRException, IOException {
        return jasperPdfService.generateAssetReport(token);
    }
}
