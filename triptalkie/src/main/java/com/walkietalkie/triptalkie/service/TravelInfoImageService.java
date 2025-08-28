package com.walkietalkie.triptalkie.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.config.FileStorageProperties;
import com.walkietalkie.triptalkie.domain.TravelInfoImage;
import com.walkietalkie.triptalkie.mapper.TravelInfoImageMapper;

@Service
public class TravelInfoImageService {

	private final TravelInfoImageMapper travelInfoImageMapper;
	private final FileStorageProperties fileStorageProperties;

	public TravelInfoImageService(TravelInfoImageMapper travelInfoImageMapper,
			FileStorageProperties fileStorageProperties) {
		super();
		this.travelInfoImageMapper = travelInfoImageMapper;
		this.fileStorageProperties = fileStorageProperties;
	}

	// 업로드 메서드
	public TravelInfoImage uploadImage(MultipartFile file, Long travelInfoIdx) throws IOException {
		if (file.isEmpty())
			return null;

		String uuid = UUID.randomUUID().toString();
		// UUID 클래스 메서드 randomUUID() 사용해서 랜덤 uuid 생성
		String originalName = file.getOriginalFilename();
		// 업로드할 파일의 원본 이름 변수 origianlName에 저장
		String savedName = uuid + "_" + originalName;
		// 랜덤 uuid와 원본 이름 합쳐서 변수 savedName에 저장

		String uploadDir = System.getProperty("user.dir") + "/" + fileStorageProperties.getUploadDir() + "/travelinfo";
		// System.getProperty("user.dir") 현재 Java 어플리케이션이 실행되는 디렉토리 경로 가져옴(절대 경로 기준)
		// fileStorageProperties.getUploadDir() : application.properties에 설정된 업로드 폴더 경로를
		// 가져옴
		// "/travelinfo" : 업로드 하위 폴더명 설정
		// 최종적으로 : [프로젝트 루트] + /uploads + /travelinfo으로 업로드 폴더가 지정됨
		File dir = new File(uploadDir);
		// File dir 객체 생성 : 경로 정보를 가진 객체
		if (!dir.exists())
			dir.mkdirs();
		// 해당 경로가 존재하는 지 확인하고 존재하지 않는 다면 디렉토리를 생성

		String filePath = uploadDir + "/" + savedName;
		// 서버상 업로드 폴더 문자열과 파일명 문자열을 합쳐서
		// 서버상의 최종 경로를 만들고 문자열 변수 filePath에 저장한다.

		File dest = new File(filePath);
		// 파일경로를 사용해서 File 객체 생성

		try {
			file.transferTo(dest);
			// 실제 파일 업로드 작업이 수행된다.

			TravelInfoImage travelInfoImage = new TravelInfoImage();
			travelInfoImage.setUuid(uuid);
			travelInfoImage.setOriginalName(originalName);
			travelInfoImage.setSavedName(savedName);
			travelInfoImage.setFilePath(filePath);
			travelInfoImage.setFileSize(file.getSize());
			travelInfoImage.setTravelinfoIdx(travelInfoIdx);

			int result = travelInfoImageMapper.registerTravelInfoImage(travelInfoImage);
			// DB 데이터 저장 처리가 되고 그 결과값이 result에 저장된다.

			if (result <= 0) {
				dest.delete();
				// DB 저장 실패 시 파일 삭제
				return null;
			}
			return travelInfoImage;

		} catch (IOException e) {
			if (dest.exists()) {
				dest.delete();
				// 업로드 실패 시 파일 삭제
			}
			throw e;
		}
	}

	// getImageUrlByTravelinfoIdx 메서드 : travelreviewImage travelreviewIdx로 이미지 경로 반환
	public String getImageUrlByTravelinfoIdx(Long travelinfoIdx) {
		TravelInfoImage image = travelInfoImageMapper.findTravelInfoImageByIdx(travelinfoIdx);
		if (image != null) {
			String uploadDir = fileStorageProperties.getUploadDir(); // "uploads"
			return "/" + uploadDir + "/travelinfo/" + image.getSavedName();
		}
		return "/images/original-profile.png"; // 등록 이미지 없으면 기본 이미지
	}

	// getImageByUuid 메서드 : uuid로 조회
	public TravelInfoImage getImageByUuid(String uuid) {
		return travelInfoImageMapper.findByUuid(uuid);
	}

	// getImageByIdx 메서드 : idx로 조회
	public TravelInfoImage getImageByIdx(Long idx) {
		return travelInfoImageMapper.findByIdx(idx);
	}

	// deleteImageByUuid(String uuid)
	public boolean deleteImageByUuid(String uuid) {
		TravelInfoImage image = travelInfoImageMapper.findByUuid(uuid);
		if (image != null) {
			File file = new File(image.getFilePath());
			if (file.exists())
				file.delete();
			return travelInfoImageMapper.deleteByUuid(uuid) > 0;
		}
		return false;
	}

	// deleteImageByTravelReviewIdx
	// travelInfoImage travelreviewIdx로 이미지 삭제 메서드
	public boolean deleteImageByTravelInfoIdx(Long idx) {
		TravelInfoImage image = travelInfoImageMapper.findTravelInfoImageByIdx(idx);
		if (image != null) {
			File file = new File(image.getFilePath());
			if (file.exists())
				file.delete();
			return travelInfoImageMapper.deleteByIdx(image.getIdx()) > 0;
		}
		return false;
	}

	// deleteImageByIdx
	public boolean deleteImageByIdx(Long idx) {
		TravelInfoImage image = travelInfoImageMapper.findByIdx(idx);
		if (image != null) {
			File file = new File(image.getFilePath());
			if (file.exists())
				file.delete();
			return travelInfoImageMapper.deleteByIdx(idx) > 0;
		}
		return false;
	}

}
