package com.crio.xmeme.service;

import java.util.List;
import java.util.Optional;

import com.crio.xmeme.exchange.MemeRequestDto;
import com.crio.xmeme.exchange.MemeRequestIdDto;

public interface MemesService {
    
    Optional<MemeRequestIdDto> saveMemes(MemeRequestDto newMeme);

    Optional<MemeRequestDto> getMemeById(String Id);

    List<MemeRequestDto> getLastNMemes(int N);
}