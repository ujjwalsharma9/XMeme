package com.crio.xmeme.repositoryService;

import java.util.List;
import java.util.Optional;

import com.crio.xmeme.data.MemeEntity;
import com.crio.xmeme.exceptions.DataAlreadyExistsException;

public interface MemesRepositoryService {

    MemeEntity saveMemes(MemeEntity meme) throws DataAlreadyExistsException;

    Optional<MemeEntity> getMemeById(String id);

    List<MemeEntity> getLastNMemes(int N);
    
}