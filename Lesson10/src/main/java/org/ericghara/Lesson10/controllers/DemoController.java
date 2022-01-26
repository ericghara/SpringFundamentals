package org.ericghara.Lesson10.controllers;

import org.ericghara.Lesson10.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController // meta-annotation for Controller and ResponseBody
public class DemoController {

        private final FileService fileService;

        @Autowired
        DemoController(FileService fileService) {
            this.fileService = fileService;
        }

        @PostMapping(path = "/test/{name}")
        public String test(@PathVariable String name,
                           @RequestHeader String a,
                           @RequestHeader String b,
                           @RequestHeader String c,
                           @RequestBody String body,
                           HttpServletResponse response ) {
            response.addHeader("test", "good"); // add header to response
            return String.format("%s %s %s %s %s", name, a, b, c, body);
        }

        @GetMapping(path = "/all")
        public Map<String, String> allHeaders(@RequestHeader Map<String,String> map) {
            // get all headers as a map
            return map;
        }

        // Returns pdf with the filename = name of a file in the resources directory
        @GetMapping(path = "/pdf/{filename}", produces = MediaType.APPLICATION_PDF_VALUE)
        public byte[] file(@PathVariable String filename) {
            // works in firefox but not hoppscotch for some reason
            return fileService.getPdf("/" + filename);
        }

}
