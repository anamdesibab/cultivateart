package com.aadhya.cultivateart.resource;

import io.swagger.annotations.ApiOperation;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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

    //@Value("${file.logos}")
    String logoURL = "/Users/ababu/Documents/Arvind/logos/";

    @ApiOperation(value = "Activate Selected Offer For SKU")
    @RequestMapping(value = "/school", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> school(@RequestParam("file") MultipartFile file) {
        String url = "";
            try{
                url = storeFile(file).getURL().toString();
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

    private Resource storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Copy file to the target location (Replacing existing file with the same name)
            Path fileStorageLocation = Paths.get(logoURL).toAbsolutePath().normalize();
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
                System.out.println("error ");
            }
        } catch (MalformedURLException ex) {
            System.out.println("error File not found " + ex.getLocalizedMessage());
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
