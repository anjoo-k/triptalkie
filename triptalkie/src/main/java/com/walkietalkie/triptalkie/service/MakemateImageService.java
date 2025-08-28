package com.walkietalkie.triptalkie.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.config.FileStorageProperties;
import com.walkietalkie.triptalkie.domain.MakemateImage;
import com.walkietalkie.triptalkie.mapper.MakemateImageMapper;

@Service
@Transactional
public class MakemateImageService {

	private final MakemateImageMapper makemateImageMapper;
	private final FileStorageProperties fileProperties;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public MakemateImageService(MakemateImageMapper makemateImageMapper, FileStorageProperties fileProperties) {
		super();
		this.makemateImageMapper = makemateImageMapper;
		this.fileProperties = fileProperties;
	}

	public void registerImage(MultipartFile photo, Long makemateIdx) throws IOException {
		String originalName = photo.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String savedName = uuid + "_" + originalName;
		Path savePath = Paths.get(System.getProperty("user.dir"), fileProperties.getUploadDir(), "makemate", savedName);
		Files.createDirectories(savePath.getParent());
		photo.transferTo(savePath.toFile());
		int result = makemateImageMapper.registerImage(
				new MakemateImage(uuid, originalName, savedName, savePath.toString(), photo.getSize(), makemateIdx));
		if (result <= 0) {
			throw new IllegalArgumentException("이미지 등록에 실패했습니다.");
		}
	}

	public MakemateImage findImageByMakemateIdx(Long makemateIdx) {
		return makemateImageMapper.findImageByMakemateIdx(makemateIdx);
	}

	public int updateImageByUuidAndMakemateIdx(long makemateIdx, MultipartFile newPhoto)
			throws IllegalStateException, IOException {
		MakemateImage originalImage = makemateImageMapper.findImageByMakemateIdx(makemateIdx);
		int result = 0;
		if (originalImage == null || originalImage.getUuid() == null || originalImage.getUuid().isEmpty()) {
			registerImage(newPhoto, makemateIdx);
			result = 1;
		} else {
			File originalFile = new File(originalImage.getFilePath());
			if (originalFile.exists()) {
				originalFile.delete();
			}

			MakemateImage photo = new MakemateImage();

			String originalName = newPhoto.getOriginalFilename();
			String savedName = originalImage.getUuid() + "_" + originalName;
			Path savePath = Paths.get(System.getProperty("user.dir"), fileProperties.getUploadDir(), savedName);
			Files.createDirectories(savePath.getParent());
			newPhoto.transferTo(savePath.toFile());
			photo.setOriginalName(originalName);
			photo.setSavedName(savedName);
			photo.setFilePath(savedName);
			photo.setFileSize(newPhoto.getSize());
			photo.setMakemateIdx(originalImage.getMakemateIdx());
			photo.setUuid(originalImage.getUuid());
			result = makemateImageMapper.updateImageByUuidAndMakemateIdx(photo);
		}
		if (result <= 0) {
			throw new IllegalArgumentException("이미지 수정에 실패했습니다.");
		}
		return result;
	}

	public int deleteImageByUuidAndMakemateIdx(Long makemateIdx) {
		MakemateImage originalImage = makemateImageMapper.findImageByMakemateIdx(makemateIdx);
		int result = 0;
		if (originalImage == null || originalImage.getUuid() == null || originalImage.getUuid().isEmpty()) {
			result = 1;
		} else {
			File originalFile = new File(originalImage.getFilePath());
			if (originalFile.exists()) {
				originalFile.delete();
			}
			result = makemateImageMapper.deleteImageByUuidAndMakemateIdx(originalImage.getUuid(), makemateIdx);
		}
		return result;
	}
}