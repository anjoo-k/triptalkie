package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.MakemateImage;

@Mapper
public interface MakemateImageMapper {

	int registerImage(MakemateImage makemateImage);

}
