package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.service.MinioService;

import java.io.File;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/files")
@RequiredArgsConstructor
public class MinioController {

    private final MinioService minioService;

    @GetMapping
    public ResponseEntity<List<FileDTO>> getFiles() {
        return ResponseEntity.ok(minioService.getListObjects());
    }

    @GetMapping("/{songId}")
    public ResponseEntity<ResponseMessageDTO> getSongFiles(@PathVariable Long songId) {

        ResponseMessageDTO responseDTO = new ResponseMessageDTO();
        try {
            minioService.getSongFilesById(songId);
            responseDTO.setMessage("OK");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping
    public ResponseEntity<String> uploadFiles(@RequestParam File pictureFile,
                                                          @RequestParam File songFile) {

        return ResponseEntity.ok("OK");
    }
}
