/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.xmeme.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.crio.xmeme.exchange.MemeRequestDto;
import com.crio.xmeme.exchange.MemeRequestIdDto;
import com.crio.xmeme.service.MemesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MemesController {

    public static final String MEMES_API_ENDPOINT = "/memes";
    public static final int N = 100;

    @Autowired
    private MemesService memesService;

    @PostMapping(MEMES_API_ENDPOINT)
    public ResponseEntity<MemeRequestIdDto> saveMemes(@RequestBody(required = true) @Valid  MemeRequestDto memeRequest) {

    // log.info("getRestaurants called with {}", getRestaurantsRequest);
    // GetRestaurantsResponse getRestaurantsResponse;

      Optional<MemeRequestIdDto> memeIdDTO = memesService
          .saveMemes(memeRequest);
    // log.info("getRestaurants returned {}", getRestaurantsResponse);

      if(memeIdDTO.isPresent()) return ResponseEntity.ok().body(memeIdDTO.get());

      return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @GetMapping(MEMES_API_ENDPOINT+"/{id}")
    public ResponseEntity<MemeRequestDto> getMemeById(@PathVariable(name = "id") long id) {

    // log.info("getRestaurants called with {}", getRestaurantsRequest);
    // GetRestaurantsResponse getRestaurantsResponse;

       Optional<MemeRequestDto> memeDto = memesService.getMemeById(id+"");
    // log.info("getRestaurants returned {}", getRestaurantsResponse);
       if(memeDto.isPresent()) return ResponseEntity.ok().body(memeDto.get());

       return ResponseEntity.notFound().build();
  }

  @GetMapping(MEMES_API_ENDPOINT)
  public ResponseEntity<List<MemeRequestDto>> getLastNMemes() {

  // log.info("getRestaurants called with {}", getRestaurantsRequest);
  // GetRestaurantsResponse getRestaurantsResponse;

    List<MemeRequestDto> memesDto = memesService.getLastNMemes(N);
  // log.info("getRestaurants returned {}", getRestaurantsResponse);
    return ResponseEntity.ok().body(memesDto);
}

  

}

