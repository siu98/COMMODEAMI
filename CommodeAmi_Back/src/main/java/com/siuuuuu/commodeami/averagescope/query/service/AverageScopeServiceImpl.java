package com.siuuuuu.commodeami.averagescope.query.service;

import com.siuuuuu.commodeami.averagescope.query.aggregate.AverageScope;
import com.siuuuuu.commodeami.averagescope.query.aggregate.AverageScopeDTO;
import com.siuuuuu.commodeami.averagescope.query.repository.AverageScopeMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AverageScopeServiceImpl implements AverageScopeService {

    private final AverageScopeMapper averageScopeMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public AverageScopeServiceImpl(AverageScopeMapper averageScopeMapper,
                                   ModelMapper modelMapper) {
        this.averageScopeMapper = averageScopeMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AverageScopeDTO> getAllAverageScopes() {
        List<AverageScope> averageScopes = averageScopeMapper.selectAllAverageScopes();
        List<AverageScopeDTO> averageScopeDTOS =
                averageScopes.stream().map(averageScope -> modelMapper.map(averageScope, AverageScopeDTO.class)).collect(Collectors.toList());
        return averageScopeDTOS;
    }

    @Override
    public AverageScopeDTO getAverageScopeByMovieId(Long movieId) {
        AverageScope averageScope = averageScopeMapper.selectAverageScopeByMovieId(movieId);

        if (averageScope == null) {
            throw new IllegalArgumentException("해당 영화에 대한 평가 데이터가 없습니다: movieId=" + movieId);
        }

        return modelMapper.map(averageScope, AverageScopeDTO.class);
    }
}
