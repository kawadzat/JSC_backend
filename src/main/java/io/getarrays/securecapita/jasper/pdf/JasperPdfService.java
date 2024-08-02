package io.getarrays.securecapita.jasper.pdf;

import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.domain.UserPrincipal;
import io.getarrays.securecapita.dto.AssetsStats;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.jasper.downloadtoken.DownloadToken;
import io.getarrays.securecapita.jasper.downloadtoken.DownloadTokenService;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JasperPdfService {
    private final ApplicationContext context;
    private final ResourceLoader resourceLoader;
    private final AssertService assertService;
    private final DownloadTokenService downloadTokenService;
    private final UserService userService;

    private void authenticateUser(User user) {
//        UserPrincipal userPrincipal=userService.
//        [[TODO]]
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public ResponseEntity<?> generateAssetReport(UUID token) throws JRException, IOException {
        Optional<DownloadToken> downloadTokenOptional = downloadTokenService.validateDownloadToken(token);
        if (downloadTokenOptional.isPresent() && downloadTokenOptional.get().isValid()&&downloadTokenOptional.get().isAssertStat()) {
            AssetsStats assetStats = assertService.getStatsUsingToken(downloadTokenOptional.get());

            JasperReport jasperReport = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:jasper/assettemplate.jrxml").getAbsolutePath());

//        List<Map<String, Object>> dataSource = List.of(
//                Map.of("field1", "Data 1", "field2", "Data 2"),
//                Map.of("field1", "Data 3", "field2", "Data 4")
//        );

            List<Map<String, Object>> dataSource = new ArrayList<>();
            assetStats.getAssetsStats().forEach((assetItemStat -> {
                dataSource.add(Map.of("name",assetItemStat.getName(), "total",assetItemStat.getTotal()));
            }));
            JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("COMPANY_ADDRESS", "1234 Street, City, Country");
            parameters.put("LOGO_PATH", Objects.requireNonNull(getClass().getResource("/images/logo.png")).toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "assetreport.pdf");
            headers.setContentLength(outputStream.size());

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new CustomMessage("You are not authorized."));
    }
}
