package org.jtgm.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jtgm.core.service.ExcelExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/jtgm/excel")
@Slf4j
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelExtractor excelExtractor;

    @PostMapping(path = "/mgroup",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveMgroup(@RequestPart(value="file") MultipartFile file) {
        try{
            log.info("[START] Initiating process of MGroup files to staging excel.");
            excelExtractor.extract(file);
            log.info("[END] Done processing the MGroup files to staging excel.");
            return new ResponseEntity<>("Extraction finished", HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Failed to extract the excel file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
