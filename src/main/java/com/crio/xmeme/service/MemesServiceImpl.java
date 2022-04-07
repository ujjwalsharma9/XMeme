package com.crio.xmeme.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crio.xmeme.data.MemeEntity;
import com.crio.xmeme.exceptions.DataAlreadyExistsException;
import com.crio.xmeme.exchange.MemeRequestDto;
import com.crio.xmeme.exchange.MemeRequestIdDto;
import com.crio.xmeme.repositoryService.MemesRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

@Service
public class MemesServiceImpl implements MemesService {

	@Autowired
	private MemesRepositoryService memesRepositoryService;

	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Instantiates a new meme service.
	 */
	public MemesServiceImpl() {
		super();
		modelMapper.getConfiguration().setFieldMatchingEnabled(false)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setSkipNullEnabled(true);
	}

	public Optional<MemeRequestIdDto> saveMemes(MemeRequestDto newMeme) {
		Optional<MemeRequestIdDto> memeIdDTO = Optional.empty();
		try {
			MemeEntity meme = modelMapper.map(newMeme, MemeEntity.class);
			MemeEntity savedMeme = memesRepositoryService.saveMemes(meme);
			MemeRequestIdDto dto = modelMapper.map(savedMeme, MemeRequestIdDto.class);
			memeIdDTO = Optional.of(dto);
		} catch (DataAlreadyExistsException e) {
			//System.out.println(e.getMessage());
		}
		return memeIdDTO;
	}

	@Override
	public Optional<MemeRequestDto> getMemeById(String id) {

		Optional<MemeEntity> meme = memesRepositoryService.getMemeById(id);
		Optional<MemeRequestDto> memeDTO = Optional.empty();
		if (meme.isPresent()) {
			MemeRequestDto map = modelMapper.map(meme.get(), MemeRequestDto.class);
			memeDTO = Optional.of(map);
		}

		return memeDTO;
	}

	@Override
	public List<MemeRequestDto> getLastNMemes(int N) {
		List<MemeEntity> memesList = memesRepositoryService.getLastNMemes(N);
		List<MemeRequestDto> memes = new ArrayList<>();

		for(MemeEntity memeEntity: memesList){
			MemeRequestDto map = modelMapper.map(memeEntity, MemeRequestDto.class);
			memes.add(map);
		}

		return memes;
	}
    
}