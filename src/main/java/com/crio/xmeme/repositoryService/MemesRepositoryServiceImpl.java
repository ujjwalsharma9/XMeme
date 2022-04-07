package com.crio.xmeme.repositoryService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.crio.xmeme.data.MemeEntity;
import com.crio.xmeme.exceptions.DataAlreadyExistsException;
import com.crio.xmeme.repository.MemesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MemesRepositoryServiceImpl implements MemesRepositoryService {

    @Autowired
    private MongoTemplate mongoTemplate;
  

    @Autowired 
    private MemesRepository memesRepository;


    @Autowired
    private SequenceGeneratorRepositoryService sequenceGenerator;

    @Override
    public MemeEntity saveMemes(MemeEntity meme) throws DataAlreadyExistsException {
        if(!checkValidity(meme)) throw new DataAlreadyExistsException();
        meme.setId(sequenceGenerator.generateSequence(MemeEntity.SEQUENCE_NAME)+"");
        meme.setCreatedDate(new Date());
        meme = memesRepository.save(meme);

        return meme;
    }

    @Override
    public Optional<MemeEntity> getMemeById(String id) {

       
        return memesRepository.findById(id);
    }

    private boolean checkValidity(MemeEntity meme){

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(meme.getName())
        .and("url").is(meme.getUrl()).and("caption").is(meme.getCaption()));
        List<MemeEntity> memes = mongoTemplate.find(query, MemeEntity.class);

        if(memes.isEmpty()) return true;

        return false;

    }

    @Override
    public List<MemeEntity> getLastNMemes(int N) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "createdDate")).limit(N);
        List<MemeEntity> memes = mongoTemplate.find(query,MemeEntity.class);

        return memes;
    }
    
}