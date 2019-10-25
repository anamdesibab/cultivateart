package com.aadhya.cultivateart.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/imageUpload")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("${file.logos}")
    String targetLogoLocation;

    @Value("${file.passport}")
    String targetPassportLocation;

    @Value("${file.eventImages}")
    String targetEventImagesLocation;

    @RequestMapping(value = "/school", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> school(@RequestParam("file") MultipartFile file) {
        String url = "";
            try{
                LOGGER.info("Uploading school Logo {}", file.getBytes());
                url = storeFile(file, targetLogoLocation, null ).getURL().toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        return new ResponseEntity("", HttpStatus.OK);
    }

    @RequestMapping(value = "/studentPassport", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> studentPassport(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        String url = "";
        try{
            LOGGER.info("Uploading StudentPassport {}", fileName);
            url = storeFile(file, targetPassportLocation, fileName).getURL().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("", HttpStatus.OK);
    }

    @RequestMapping(value = "/eventImages", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> eventImages(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        String url = "";
        try{
            LOGGER.info("Uploading event Images {}", fileName);
            url = storeFile(file, targetEventImagesLocation, fileName).getURL().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("", HttpStatus.OK);
    }


    @PostMapping("/uploadMultipleFiles")
    //https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
    public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        /*return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());*/
    }

    private Resource storeFile(MultipartFile file, String targetFileLocation, String fileName) {
        // Normalize file name
        if(fileName == null) {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
        }
        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path fileStorageLocation = Paths.get(targetFileLocation).toAbsolutePath().normalize();
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            //uploadImage();
            return loadFileAsResource(fileName, fileStorageLocation);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*private final Path fileStorageLocation;*/

    public Resource loadFileAsResource(String fileName, Path fileStorageLocation) {
        try {

            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                LOGGER.error("error while uploading file ");
            }
            LOGGER.info("Complete uploading the images to location {}", filePath.toUri());
        } catch (MalformedURLException ex) {
            LOGGER.error("error File not found " + ex.getLocalizedMessage());
        }

        return null;
    }

    public void uploadImage(){
        URLConnection urlconnection = null;
        try {
            File file = new File("/Users/ababu/Git/cultivatingart/src/main/resources/static/logos/0390_80000023_2457_f001.jpeg");
            URL url = new URL("http://www.cultivatingart.in/uploads/0390_80000023_2457_f001.jpeg");
            urlconnection = url.openConnection();
            urlconnection.setDoOutput(true);
            urlconnection.setDoInput(true);

            if (urlconnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnection).setRequestMethod("PUT");
                ((HttpURLConnection) urlconnection).setRequestProperty("Content-type", "image/jpeg");
                ((HttpURLConnection) urlconnection).connect();
            }

            BufferedOutputStream bos = new BufferedOutputStream(urlconnection.getOutputStream());
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            int i;
            // read byte by byte until end of stream
            while ((i = bis.read()) > 0) {
                bos.write(i);
            }
            bis.close();
            bos.close();
            System.out.println(((HttpURLConnection) urlconnection).getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
