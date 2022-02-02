package org.ericghara.Lesson10.services;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    public byte[] getPdf(String resourceName) {
        byte[] pdf;
        try {
            URI uri = getClass().getResource(resourceName)
                                .toURI();
            pdf = Files.readAllBytes(Paths.get(uri) );
        } catch (Exception e) {
            throw new RuntimeException("Couldn't Open the file", e);
        }
        return pdf;
    }
}
