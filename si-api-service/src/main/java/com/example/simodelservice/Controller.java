package com.example.simodelservice;

import com.example.simodelservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    private PythonAppClient pythonAppClient;

    @Autowired
    public Controller(PythonAppClient pythonAppClient) {
        this.pythonAppClient = pythonAppClient;
    }

    @GetMapping
    public String getHome(Model model) {
        return "index";
    }

    @PostMapping("/upload")
    public String predict(Model model, @RequestParam("image") MultipartFile file) throws IOException{
        try {
            ModelResultDto results = pythonAppClient.predict(file);

            byte[] fileInBytes = file.getBytes();
            byte[] newFileinBytes = getPicture();
            String base64Picture = Base64.getEncoder().encodeToString(fileInBytes);
            String base64NewPicture = Base64.getEncoder().encodeToString(newFileinBytes);

            model.addAttribute("imageFile", base64Picture);
            model.addAttribute("newImageFile", base64NewPicture);

            ModelResultDto ModelResultDto = voteMethod(results);
            model.addAttribute("resultDto", ModelResultDto);

            return "resultsPage";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Connection problem with model service");
            return "errorPage";
        }
    }


    @PostMapping("/test")
    public ResponseEntity<ResultDto> test(Model model, @RequestParam("image") MultipartFile file) {
        ModelResultDto results = pythonAppClient.predict(file);
        ModelResultDto modelResultDto = voteMethod(results);
        ResultDto resultDto = new ResultDto(modelResultDto.getStackingClass(), modelResultDto.getStackingConfidence(),
                modelResultDto.getVotedClass(), modelResultDto.getVotedConfidence());
        return ResponseEntity.ok(resultDto);
    }

    private static ModelResultDto voteMethod(ModelResultDto modelResultDto) {
        Result result1 = new Result(modelResultDto.getM1_class(), modelResultDto.getM1_confidence());
        Result result2 = new Result(modelResultDto.getM2_class(), modelResultDto.getM2_confidence());
        Result result3 = new Result(modelResultDto.getM3_class(), modelResultDto.getM3_confidence());
        Result[] resultsTab = {result1, result2, result3};


        HashMap<String, Float> accuracies = new HashMap<>();
        HashMap<String, Integer> occurencies = new HashMap<>();
        initalizeHashMap(accuracies, occurencies);

        for(int i=0; i<resultsTab.length; i++) {
            String votedClass = resultsTab[i].getVotedClass();
            accuracies.put(votedClass, accuracies.get(votedClass) + resultsTab[i].getVotedConfidence());
            occurencies.put(votedClass, occurencies.get(votedClass) + 1);
        }

        String lastKey = "";
        Integer lastValue = -1;

        for(Map.Entry<String, Integer> occurence : occurencies.entrySet()) {
            if(occurence.getValue() > lastValue) {
                lastValue = occurence.getValue();
                lastKey = occurence.getKey();
            }
        }

        modelResultDto.setVotedClass(lastKey);
        modelResultDto.setVotedConfidence(accuracies.get(lastKey)/lastValue);

        return modelResultDto;
    }

    private byte[] getPicture() throws IOException {
        ResponseEntity<Resource> response = pythonAppClient.getPicture();
        Resource resource = response.getBody();
        InputStream inputStream = resource.getInputStream();
        return inputStream.readAllBytes();
    }

    private static void initalizeHashMap(HashMap<String, Float> accuracies,
                                 HashMap<String, Integer> occurencies) {
        accuracies.put("T-shirt/top", 0.0f);
        accuracies.put("Trouser", 0.0f);
        accuracies.put("Pullover", 0.0f);
        accuracies.put("Dress", 0.0f);
        accuracies.put("Coat", 0.0f);
        accuracies.put("Sandal", 0.0f);
        accuracies.put("Shirt", 0.0f);
        accuracies.put("Sneaker", 0.0f);
        accuracies.put("Bag", 0.0f);
        accuracies.put("Ankle boot", 0.0f);

        occurencies.put("T-shirt/top", 0);
        occurencies.put("Trouser", 0);
        occurencies.put("Pullover", 0);
        occurencies.put("Dress", 0);
        occurencies.put("Coat", 0);
        occurencies.put("Sandal", 0);
        occurencies.put("Shirt", 0);
        occurencies.put("Sneaker", 0);
        occurencies.put("Bag", 0);
        occurencies.put("Ankle boot", 0);
    }
}
