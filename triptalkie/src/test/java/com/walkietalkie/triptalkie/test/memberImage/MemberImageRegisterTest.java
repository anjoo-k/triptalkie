package com.walkietalkie.triptalkie.test.memberImage;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.MemberImage;
import com.walkietalkie.triptalkie.mapper.MemberImageMapper;

@SpringBootTest
// 스프링 부트 통합 테스트를 위한 어노테이션
// 모든 빈이 로드 되기 때문에 실제 어플리케이션과 거의 동일한 환경에서 테스트가 가능하다.
@Transactional
// 테스트 끝난 후 테스트 내용 DB에 반영하지 않는다.
public class MemberImageRegisterTest {
	
	@Autowired
	private MemberImageMapper memberImageMapper;
	
	// DB 쪽만 테스트
	@Test
	void testRegisterMemberImage() {
		// given (테스트용 MemberImage 생성)
		String originalName = "test.png";
		String uuid = UUID.randomUUID().toString();
		String savedName = uuid + "_" + originalName;
		
		MemberImage memberImage = new MemberImage();
		memberImage.setUuid(uuid);
		memberImage.setOriginalName(originalName);
		memberImage.setSavedName(savedName);
		memberImage.setFilePath("/upload/profile/" + savedName);
		memberImage.setFileSize(1024L);
		memberImage.setMemberId("user11");
		
		// when
		int result = memberImageMapper.registerMemberImage(memberImage);
		
		// then
		assertThat(result).isEqualTo(1);
		MemberImage saved = memberImageMapper.findByUuid(uuid);
		assertThat(saved).isNotNull();
		assertThat(saved.getSavedName()).isEqualTo(savedName);
	}
	
	//
	@Test
    void testRegisterMemberImageWithFile() throws IOException {
        // 1. 임시 디렉토리에 실제 파일 생성
        String originalName = "test.png";
        String uuid = UUID.randomUUID().toString();
        String savedName = uuid + "_" + originalName;

        Path tempDir = Files.createTempDirectory("profileTest");
        Path tempFile = tempDir.resolve(savedName);

        // 실제 파일 내용 작성
        Files.write(tempFile, "테스트 이미지 파일".getBytes());

        // 2. MemberImage 객체 생성
        MemberImage memberImage = new MemberImage();
        memberImage.setUuid(uuid);
        memberImage.setOriginalName(originalName);
        memberImage.setSavedName(savedName);
        memberImage.setFilePath(tempFile.toString()); // 실제 파일 경로
        memberImage.setFileSize(Files.size(tempFile));
        memberImage.setMemberId("user11");

        // 3. DB 등록
        int result = memberImageMapper.registerMemberImage(memberImage);

        // 4. 검증
        assertThat(result).isEqualTo(1);

        MemberImage saved = memberImageMapper.findByUuid(uuid);
        assertThat(saved).isNotNull();
        assertThat(saved.getSavedName()).isEqualTo(savedName);
        assertThat(Files.exists(Path.of(saved.getFilePath()))).isTrue();

        // 5. 테스트 종료 후 파일 삭제
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempDir);
    }
	
	
}