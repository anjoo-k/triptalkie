package com.walkietalkie.triptalkie.test.memberImage;

import static org.assertj.core.api.Assertions.assertThat;

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
public class MemberImageRegisterTest {
	
	@Autowired
	private MemberImageMapper memberImageMapper;
	
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
		memberImage.setMemberId("user1");
		
		// when
		int result = memberImageMapper.registerMemberImage(memberImage);
		
		// then
		assertThat(result).isEqualTo(1);
		MemberImage saved = memberImageMapper.findByUuid(uuid);
		assertThat(saved).isNotNull();
		assertThat(saved.getSavedName()).isEqualTo(savedName);

	}
}
