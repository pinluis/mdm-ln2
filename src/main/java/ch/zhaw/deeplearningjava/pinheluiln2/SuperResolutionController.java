package ch.zhaw.deeplearningjava.pinheluiln2;

import ai.djl.ModelException;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.translate.TranslateException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class SuperResolutionController {

    @PostMapping(value = "/enhance", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> enhanceImage(@RequestParam("file") MultipartFile file) throws IOException, ModelException, TranslateException {
        ImageFactory imageFactory = ImageFactory.getInstance();

        Image inputImage = imageFactory.fromInputStream(file.getInputStream());
        Image enhancedImage = SuperResolution.enhanceSingle(inputImage);

        // Save the enhanced image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        enhancedImage.save(baos, "png");
        byte[] imageBytes = baos.toByteArray();

        // Set the appropriate response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageBytes.length);

        // Return the byte array with the headers and status
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
