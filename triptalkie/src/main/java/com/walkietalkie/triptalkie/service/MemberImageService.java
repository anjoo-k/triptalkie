package com.walkietalkie.triptalkie.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.config.FileStorageProperties;
import com.walkietalkie.triptalkie.domain.MemberImage;
import com.walkietalkie.triptalkie.mapper.MemberImageMapper;

@Service
public class MemberImageService {

	private final MemberImageMapper memberImageMapper;
	private final FileStorageProperties fileStorageProperties;

	public MemberImageService(MemberImageMapper memberImageMapper, FileStorageProperties fileStorageProperties) {
		this.memberImageMapper = memberImageMapper;
		this.fileStorageProperties = fileStorageProperties;
	}

	// 업로드 메서드
	public MemberImage uploadImage(MultipartFile file, String memberId) throws IOException {
		if (file.isEmpty()) return null;
		
		String uuid = UUID.randomUUID().toString();
		// UUID 클래스 메서드 randomUUID() 사용해서 랜덤 uuid 생성
		String originalName = file.getOriginalFilename();
		// 업로드할 파일의 원본 이름 변수 origianlName에 저장
		String savedName = uuid + "_" +originalName;
		// 랜덤 uuid와 원본 이름 합쳐서 변수 savedName에 저장
		
		String uploadDir = System.getProperty("user.dir") + "/" + fileStorageProperties.getUploadDir() + "/profile";
        // System.getProperty("user.dir") 현재 Java 어플리케이션이 실행되는 디렉토리 경로 가져옴(절대 경로 기준)
        // fileStorageProperties.getUploadDir() : application.properties에 설정된 업로드 폴더 경로를 가져옴
        // "/profile" : 업로드 하위 폴더명 설정
        // 최종적으로 : [프로젝트 루트] + /uploads + /profile으로 업로드 폴더가 지정됨
		File dir = new File(uploadDir);
		// File dir 객체 생성 : 경로 정보를 가진 객체
		if (!dir.exists()) dir.mkdir();
		// 해당 경로가 존재하는 지 확인하고 존재하지 않는 다면 디렉토리를 생성
		
		String filePath = uploadDir + "/" + savedName;
		// 서버상 업로드 폴더 문자열과 파일명 문자열을 합쳐서
		// 서버상의 최종 경로를 만들고 문자열 변수 filePath에 저장한다.
		file.transferTo(new File(filePath));
		// 실제 파일 업로드 작업이 수행된다.
		
		MemberImage memberImage = new MemberImage();
		memberImage.setUuid(uuid);
		memberImage.setOriginalName(originalName);
		memberImage.setSavedName(savedName);
		memberImage.setFilePath(filePath);
		memberImage.setFileSize(file.getSize());
		memberImage.setMemberId(memberId);
		
		int result = memberImageMapper.registerMemberImage(memberImage);
		
		return result > 0 ? memberImage : null;
		}
	
		// getImageByUuid 메서드 : uuid로 조회
		public MemberImage getImageByUuid(String uuid) {
			return memberImageMapper.findByUuid(uuid);
		}
		// getImageByIdx 메서드 : idx로 조회
		public MemberImage getImageByIdx(Long idx) {
			return memberImageMapper.findByIdx(idx);
		}
		
	
		// deleteImageByUuid(String uuid) 
		public boolean deleteImageByUuid(String uuid) {
			MemberImage image = memberImageMapper.findByUuid(uuid);
			if (image != null) {
				File file = new File(image.getFilePath());
				if (file.exists()) file.delete();
				return memberImageMapper.deleteByUuid(uuid) > 0;
			}
			return false;
		}
		// deleteImageByIdx
		public boolean deleteImageByIdx(Long idx) {
			MemberImage image= memberImageMapper.findByIdx(idx);
			if (image != null) {
				File file = new File(image.getFilePath());
				if (file.exists()) file.delete();
				return memberImageMapper.deleteByIdx(idx) > 0;
			}
			return false;
		}
	}

