package com.example.MovieStarter.controllers;

import com.example.MovieStarter.DTO.DirectorDTO;
import com.example.MovieStarter.Responses.RequestResponse;
import com.example.MovieStarter.Responses.RequestResponseT;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PutMapping("/{directorid}/movies/{movieid}")
    public RequestResponse addCreatedMovies(@PathVariable Integer directorid, @PathVariable Integer movieid) {

        ServiceResponse response =  directorService.addMovies(directorid, movieid);
        return RequestResponseT.fromError(response.getError());
    }

    @PostMapping("/add")
    public RequestResponse<DirectorDTO> addDirector(@RequestBody DirectorDTO director) {
        ServiceResponseT<DirectorDTO> response = directorService.addDirector(director);

        return RequestResponseT.fromServiceResponseOfType(response);
    }

    @DeleteMapping("/del/{director_id}")
    public RequestResponse DeleteMovie(@PathVariable Integer director_id) {

        ServiceResponse response = directorService.DeleteDirector(director_id);
        if (response.isOk()) {
            return RequestResponseT.okRequestResponse();
        }
        return new RequestResponseT("Failed to delete director", response.getError());
    }
}
