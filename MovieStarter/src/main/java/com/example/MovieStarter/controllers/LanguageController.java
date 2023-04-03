package com.example.MovieStarter.controllers;

import com.example.MovieStarter.DTO.GenreDTO;
import com.example.MovieStarter.DTO.LanguageDTO;
import com.example.MovieStarter.Responses.RequestResponse;
import com.example.MovieStarter.Responses.RequestResponseT;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.Language;
import com.example.MovieStarter.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    @GetMapping
    public List<Language> getAllLanguages() {

        return languageService.getAllLanguages();
    }

    @PostMapping("/add")
    public RequestResponse<LanguageDTO> addLanguage(@RequestBody LanguageDTO languageDTO) {

        ServiceResponseT<LanguageDTO> response =  languageService.addLanguage(languageDTO);
        return RequestResponseT.fromServiceResponseOfType(response);
    }

    @DeleteMapping("/del/{language_id}")
    public RequestResponse DeleteMovie(@PathVariable Integer language_id) {

        ServiceResponse response = languageService.DeleteLanguage(language_id);
        if (response.isOk()) {
            return RequestResponseT.okRequestResponse();
        }
        return new RequestResponseT("Failed to delete language", response.getError());
    }
}
