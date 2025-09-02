# 🐰 WalkieTalkie (TripTalkie)

> **트립토키와 함께하는 여행, 빛나는 즐거움**  
> 한국소프트웨어산업협회 MSA 5차 프로젝트 (2025. 08. 29.)  
<div align="center">
<img width="400" height="1024" alt="image (17) (1)" src="https://github.com/user-attachments/assets/688b14a5-1085-4a1e-ab30-707b109a4881" />
</div>

---

## 📷 [프로젝트 시연 영상 보기](https://file.notion.so/f/f/28a23df9-6425-813f-bc14-0003220f7630/b12a724d-b60d-46ee-8ff3-5360c7b67a71/triptalkie_%EC%8B%9C%EC%97%B0_%EC%98%81%EC%83%81.mp4?table=block&id=26223df9-6425-80ec-832f-e1d3fbe08c91&spaceId=28a23df9-6425-813f-bc14-0003220f7630&expirationTimestamp=1756828800000&signature=zcDWr6XgfYG7jlEu_NPmWPJGXco36hqURegRjsiKX8c)

---

## 📌 프로젝트 개요
- **목적**  
  - Spring Boot + Thymeleaf 기반 프로젝트
  - 요구사항 정의 → 분석/설계 → 구현 전 과정을 실무와 유사하게 경험  
  - 여행 메이트 모집 + 커뮤니티 + 리뷰 + 채팅 등 서비스 통합 구현  
- **개발 동기**  
  - 여행 취향이 맞는 사람을 찾기 어려운 문제 해결  
  - 신뢰할 수 있는 여행 정보 공유 및 동행 매칭 서비스 제공  


---

## 🛠 적용 기술
- **Language** : Java 21, JavaScript, HTML, CSS  
- **Framework & Library** : Spring Boot, MyBatis, JUnit, Bootstrap4  
- **Template Engine** : Thymeleaf  
- **DB** : MySQL (MySQL Workbench)  
- **Version Control** : GitHub, Sourcetree  
- **Collaboration** : Jira, Notion  
- **Dev Methodology** : 애자일 스크럼, BDD  

---

## 아이템 기획
**린캔버스**
<div align="center">
<img width="800" height="732" alt="image (27)" src="https://github.com/user-attachments/assets/3fec528e-094a-42a7-8574-efc51f1c4248" />
</div>

---

## 📐 분석 & 설계
- **UI/UX 설계** : 와이어프레임, 프로토타입(Figma) 제작
**와이어프레임**
  <div align="center">
<img width="400" height="1293" alt="Page07 jpg" src="https://github.com/user-attachments/assets/9eb2b91e-8170-4964-a0e8-b130a27690e5" />
<img width="400" height="1293" alt="Page07 jpg" src="https://github.com/user-attachments/assets/a4d5521d-bdf6-4bf9-96a8-a401bc5b5eb6" />
  </div>

- **Use Case Diagram** : 비회원 / 회원 / 관리자 시나리오 정의
<div align="center">
<img width="700" height="941" alt="image (28)" src="https://github.com/user-attachments/assets/49b07029-0816-4e8c-b3df-f9daf41a894a" />
</div>

**프로토타입**
<div align="center">
  <img width="250" height="2498" alt="메인 페이지 - 로그인 O" src="https://github.com/user-attachments/assets/f68bec9e-69ee-402d-b0d3-f906ca2aefc0" />
  <img width="250" height="647" alt="image (23)" src="https://github.com/user-attachments/assets/84be4f7c-3e10-4cd8-8f17-526156e5db24" />
  <img width="250" height="555" alt="화면 캡처 2025-09-01 114238" src="https://github.com/user-attachments/assets/8f4cc4f5-7c07-44c9-b397-67672a1dc4d5" />
</div>

- **ERD** :  
  - 게시판 (Makemate, Community, TravelInfo, TravelReview)  
  - 댓글, 이미지 테이블 분리  
  - 메이트-멤버 N:M 관계 (MemberList 교차 테이블)  
  - 대륙 → 국가 → 도시 계층 구조  
  - 실시간 채팅용 ChatRoom, ChatMessage 테이블
  - <img width="700" height="814" alt="image (29)" src="https://github.com/user-attachments/assets/c9346d8f-6ab3-4987-8f3f-377eec463d73" />

- **Class Diagram**
  - Controller → Service → Mapper → Domain 구조  
  - 도메인 : Makemate, Community, TravelInfo, TravelReview  
  - 확장 기능 : Bookmark, Rating, Chat  
<img width="700" height="2230" alt="triptalkie-ver2 drawio" src="https://github.com/user-attachments/assets/31f24884-1bb9-486d-992f-f6864e13e794" />

---

## 📅 프로젝트 일정
- **아이템 기획** : 8/4 ~ 8/6  
- **분석/설계** : 8/7 ~ 8/13  
- **구현** : 8/13 ~ 8/26 (스프린트 3회)  
- **포트폴리오 정리** : 8/27 ~ 8/29  

---

## 🔑 주요 기능
- **멤버/마이페이지**
  - 회원가입, 로그인/로그아웃
  - 아이디/비밀번호 찾기
  - 내사진, 정보 수정
  - 내가 쓴 글, 깡총지수 평가가 ,채팅 확인 가능

- **메이크메이트 (여행 메이트 모집)**
  - 모집글 등록/수정/삭제
  - 다중 조건 검색(국가/도시/여행 컨셉) + 페이지네이션
  - 모집글 상세 조회 (여행 일정, 인원, 예산, 신청 상태)
  - 신청하기 / 채팅하기 버튼 제공 (상태별 UI 제어)

- **채팅**
  - WebSocket + STOMP 기반 실시간 채팅
  - DB 연동 (ChatRoom, ChatMessage 테이블)

- **여행 정보/후기 & 댓글**
  - 여행정보/후기 등록/수정/삭제
  - 다중 조건 검색(국가/도시/여행 컨셉) + 페이지네이션
  - 댓글 작성/삭제 (Fetch API, 비동기 처리)

- **깡총지수 (별점 평가)**
  - 동행자 평가 → Member `credit` 갱신
  - 최초 입력 고정 / 재평가 불가 / 자기 자신 평가 불가

- **커뮤니티 & 공지**
  - 공지사항, FAQ, QnA 글 조회

---

## 📊 팀 협업 규칙
- **Git Flow 전략**  
  - `main`: 운영 코드 / 항상 배포 가능 상태  
  - `develop`: 다음 버전 준비, 기능 통합 브랜치  
  - `feature/*`: 개별 기능 개발 후 develop 병합  

- **이슈 관리 규칙**  
  - 이슈 기록 → 30분 개인 고민 → 페어 프로그래밍 → 팀 전체 논의 → 해결 기록
<div align="center">
<img width="316" height="415" alt="화면 캡처 2025-09-01 163506" src="https://github.com/user-attachments/assets/94946e98-1b67-4e8d-a445-609837445f7b" />
</div>

- **데일리 회의**  
  - 시작 / 중간 / 마무리 회의로 일정 및 이슈 공유  

---

## 👥 팀 소개 및 역할
- **오현승** : 프로젝트 리더 · DB 설계 · 백엔드 리더 · 문서 관리  
- **김범준** : 형상 관리 리더 · 이슈 관리 리더 · 디자인 리더  
- **김남주** : 소통 리더 · 일정 관리 리더 · 프로젝트 설계 리더  
- **송예은** : 프론트엔드 리더 · UX 리더 · 스터디 리더

---

## 💡 프로젝트 회고
- MVC 패턴과 Spring Boot, MyBatis, Thymeleaf 등 실무 기술을 실제 적용
- Git을 이용하고 Git Flow를 적용한 형상관리
- Jira, Notion 등 협업 툴을 통해 실제 현업에 가까운 협업 방식 경험  
- UI/UX 설계 과정에서 팀원과의 소통과 합의 과정을 통해 협업의 가치 체감  
- 단순 개발을 넘어, 서비스 기획 → 분석/설계 → 구현 → 회고까지 전 과정을 경험  
