package com.walkietalkie.triptalkie.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.config.FileStorageProperties;
import com.walkietalkie.triptalkie.domain.TravelReviewImage;
import com.walkietalkie.triptalkie.mapper.TravelReviewImageMapper;

@Service
public class TravelReviewImageService {
	private final TravelReviewImageMapper travelReviewImageMapper;
	private final FileStorageProperties fileStorageProperties;

	public TravelReviewImageService(TravelReviewImageMapper travelReviewImageMapper,
			FileStorageProperties fileStorageProperties) {
		super();
		this.travelReviewImageMapper = travelReviewImageMapper;
		this.fileStorageProperties = fileStorageProperties;
	}

	/*
	 * 여행 후기 사진 업로드 메서드
	 */
	public TravelReviewImage uploadImage(MultipartFile file, long idx) throws IOException {
		if (file.isEmpty()) {
			return null;
		}

		String uuid = UUID.randomUUID().toString();
		String originalName = file.getOriginalFilename();
		String savedName = uuid + "_" + originalName;
		String uploadDir = System.getProperty("user.dir") + "/" + fileStorageProperties.getUploadDir() + "/review";
		File dir = new File(uploadDir);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		String filePath = uploadDir + "/" + savedName;

		file.transferTo(new File(filePath));

		TravelReviewImage travelReviewImage = new TravelReviewImage();
		travelReviewImage.setUuid(uuid);
		travelReviewImage.setOriginalName(originalName);
		travelReviewImage.setSavedName(savedName);
		travelReviewImage.setFilePath(filePath);
		travelReviewImage.setFileSize(file.getSize());
		travelReviewImage.setTravelReviewIdx(idx);

		int result = travelReviewImageMapper.registerTravelReviewImage(travelReviewImage);

		return result > 0 ? travelReviewImage : null;
	}

	public String findImageUrlByIdx(Long idx) {
		TravelReviewImage image = travelReviewImageMapper.findImageUrlByIdx(idx);

		if (image != null) {
			String uploadDir = fileStorageProperties.getUploadDir(); // "uploads"
			return "/" + uploadDir + "/review/" + image.getSavedName();
		}

		return "/images/original-review.png";
	}

	public void updateReviewImage(long idx, MultipartFile file) throws IOException {
		// 1. 기존 이미지 조회
		TravelReviewImage existingImage = travelReviewImageMapper.findImageUrlByIdx(idx);
		System.out.println("원래 existingImage 값 : " + existingImage);

		// 2. 기존 이미지가 있으면 서버 파일과 DB 삭제
		if (existingImage != null) {
			File oldFile = new File(existingImage.getFilePath());
			if (oldFile.exists()) {
				oldFile.delete(); // 서버 파일 삭제
			}
			travelReviewImageMapper.deleteReviewImageByIdx(idx); // DB 삭제
		}

		// 3. 새 이미지 업로드
		uploadImage(file, idx);

	}

	public void deleteReviewImageByIdx(long idx) {
		// 1. DB에서 이미지 정보 조회
		TravelReviewImage image = travelReviewImageMapper.findImageUrlByIdx(idx);

		if (image != null) {
			// 2. 서버 파일 삭제
			File file = new File(image.getFilePath());
			if (file.exists()) {
				boolean deleted = file.delete();
				if (!deleted) {
					System.out.println("이미지 파일 삭제 실패: " + image.getFilePath());
				}
			}

			// 3. DB 삭제
			travelReviewImageMapper.deleteReviewImageByIdx(idx);
		}
	}

}
