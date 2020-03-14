package com.barrier.gate.Controller;

import com.barrier.gate.service.CheckOutService;
import com.barrier.gate.service.CheckInService;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/gate")
public class CheckingController {
    private final Logger logger = LoggerFactory.getLogger(CheckingController.class);

    @Autowired
    private CheckOutService checkOutService;
    @Autowired
    private CheckInService checkInService;
    @Autowired
    ServletContext servletContext;

    /*@PostMapping(value = "/checkOut")
    public ResponseEntity<String> postCardDetails(@RequestBody CardDetailsWrapper cardDetails){
        try {
            CardDetails cardDetails1 = new CardDetails();
            cardDetails1.setCheckIn(new Date());
            cardDetails1.setBarcode(cardDetails.getBarcode());
            cardDetails1.setPrice(cardDetails.getPrice());
            //byte[] imageByte= Base64.decodeBase64(cardDetails.getFilePath());
            byte[] data = Base64.getDecoder().decode(cardDetails.getFilePath());
            String webInfPath = servletContext.getRealPath("WEB-INF" );;
            String downloadPath = webInfPath + "/attachments/";
            int fileRandomName = 10000 + new Random().nextInt(90000);
            String fileLocation = downloadPath + fileRandomName + ".jpg";
            OutputStream outputStream = new FileOutputStream(fileLocation);
            outputStream.write(data);
            cardDetails1.setFilePath(fileLocation);
            checkInService.postCardDetails(cardDetails1);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("failed",HttpStatus.EXPECTATION_FAILED);
        }
    }*/

    /*@GetMapping(value = "/checkIn")
    @ResponseBody
    public ResponseEntity<CardDetailsWrapper> getAllClient(@RequestParam Long barcode) {
        try {
            CardDetails getAllClients = (CardDetails) checkOutService.getCardById(barcode);
            CardDetailsWrapper carDetailsWrapper = new CardDetailsWrapper();
            carDetailsWrapper.setId(getAllClients.getId());
            carDetailsWrapper.setBarcode(getAllClients.getBarcode());
            carDetailsWrapper.setCheckIn(getAllClients.getCheckIn());
            carDetailsWrapper.setCheckOut(getAllClients.getCheckOut());
            carDetailsWrapper.setPrice(getAllClients.getPrice());
            carDetailsWrapper.setFilePath(getAllClients.getFilePath());
            File f =  new File(getAllClients.getFilePath());
            FileInputStream fileInputStreamReader = new FileInputStream(f);
            byte[] bytes = new byte[(int)f.length()];
            fileInputStreamReader.read(bytes);
            String encodedFile = Base64.getEncoder().encodeToString(bytes);
            carDetailsWrapper.setImageBytes(encodedFile);
            ResponseEntity<CardDetailsWrapper> responseEntity = new ResponseEntity<CardDetailsWrapper>((CardDetailsWrapper) carDetailsWrapper, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<CardDetailsWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /*@PutMapping(value = "checkUpdate")
    public ResponseEntity<String> putCardDetails(@RequestBody CardDetailsWrapper cardDetails){
        try {
            CardDetails cardDetails1 = new CardDetails();
            cardDetails1.setId(cardDetails.getId());
            cardDetails1.setCheckIn(cardDetails.getCheckIn());
            cardDetails1.setCheckOut(new Date());
            cardDetails1.setBarcode(cardDetails.getBarcode());
            cardDetails1.setPrice(cardDetails.getPrice());
            cardDetails1.setFilePath(cardDetails.getFilePath());
            checkInService.postCardDetails(cardDetails1);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("failed",HttpStatus.EXPECTATION_FAILED);
        }
    }*/


}
