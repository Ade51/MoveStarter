package com.example.MovieStarter.controllers;

import com.example.MovieStarter.DTO.GenreDTO;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.RequestResponse;
import com.example.MovieStarter.Responses.RequestResponseT;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;
    @GetMapping
    public RequestResponse getAllGenres(Model model) {

        var genreList = genreService.getAllGenres();
        if (genreList.isEmpty()) {
            return RequestResponseT.fromError(new ErrorMessage(HttpStatus.NOT_FOUND, "No movies found!", ErrorCodes.PhysicalFileNotFound));
        }
        model.addAttribute("GenresList", genreService.getAllGenres());
        return RequestResponseT.okRequestResponse();
    }

    @PostMapping("/add")
    public RequestResponse<GenreDTO> addGenre(@RequestBody GenreDTO genre) {

        ServiceResponseT<GenreDTO> response =  genreService.addGenre(genre);
        return RequestResponseT.fromServiceResponseOfType(response);
    }

    @DeleteMapping("/del/{genre_id}")
    public RequestResponse DeleteMovie(@PathVariable Integer genre_id) {

        ServiceResponse response = genreService.DeleteGenre(genre_id);
        if (response.isOk()) {
            return RequestResponseT.okRequestResponse();
        }
        return new RequestResponseT("Failed to delete genre", response.getError());
    }
}
