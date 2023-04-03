package com.example.MovieStarter.services;

import com.example.MovieStarter.DTO.LanguageDTO;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.Language;
import com.example.MovieStarter.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;
    public List<Language> getAllLanguages() {
            return languageRepository.findAll();

    }
    public ServiceResponseT<LanguageDTO> addLanguage(LanguageDTO languageDTO) {

        Optional<Language> language = Optional.ofNullable((Language) getLanguageByName(languageDTO.getName()));

        if (language.isPresent()) {
            return ServiceResponseT.createError(new ErrorMessage(HttpStatus.CONFLICT, "Language aldready exist !", ErrorCodes.CannotAdd));
        }
        Language l = new Language();
        l.setName(languageDTO.getName());
        languageRepository.save(l);
        return ServiceResponseT.forSuccess(languageDTO);
    }

    private Object getLanguageByName(String name) {
        return languageRepository.findByName(name);
    }

    public Optional<Language> findLanguageById(Integer id) {
        return languageRepository.findById(id);
    }

    public ServiceResponse DeleteLanguage(Integer id) {
        Optional<Language> language = findLanguageById(id);

        if (language.isPresent()) {
            languageRepository.deleteById(id);
            return ServiceResponse.forSuccess();
        } else
            return ServiceResponse.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "The langauge does not exists!", ErrorCodes.CannotDelete));
    }
}
