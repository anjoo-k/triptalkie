package com.walkietalkie.triptalkie.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.config.FileStorageProperties;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Land;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.MakemateImage;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MakemateImageMapper;
import com.walkietalkie.triptalkie.mapper.MakemateMapper;

@Service
@Transactional
public class MakemateImageService {
	
	private final MakemateMapper makemateMapper;
	private final MakemateImageMapper makemateImageMapper;
	private final FileStorageProperties fileProperties;

	public MakemateImageService(MakemateMapper makemateMapper, MakemateImageMapper makemateImageMapper,
			FileStorageProperties fileProperties) {
		super();
		this.makemateMapper = makemateMapper;
		this.makemateImageMapper = makemateImageMapper;
		this.fileProperties = fileProperties;
	}

	public void registerImage(MultipartFile photo, Long makemateId) throws IOException {
		String originalName = photo.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String savedName = uuid  + "_" + originalName;
		Path savePath = Paths.get(System.getProperty("user.dir"), fileProperties.getUploadDir(), savedName);
		Files.createDirectories(savePath.getParent());
		photo.transferTo(savePath.toFile());
		int result = makemateImageMapper.registerImage(new MakemateImage(uuid, originalName, savedName, savePath.toString(), photo.getSize(), makemateId));
		if (result <= 0) {
		    throw new IllegalArgumentException("이미지 등록에 실패했습니다.");
		}
	}

	public MakemateImage findMakemateByIdx(Long makemateId) {
		return makemateImageMapper.findImagesByIdx(makemateId);	}
}
