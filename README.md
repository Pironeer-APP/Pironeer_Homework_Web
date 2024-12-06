# ✅ PIROCHECK
##### 🏆 피로그래밍 과제 채점 결과 알려주 서비스

### 📜 Contents
 1. [Overview](#-overview)
 2. [서비스 화면](#-서비스-화면)
 3. [주요 기능](#-주요-기능)
 4. [개발 환경](#%EF%B8%8F-개발-환경)
 5. [시스템 아키텍처](#-시스템-아키텍처)
 6. [기술 특이점](#-기술-특이점)
 7. [기획 및 설계 산출물](#-기획-및-설계-산출물)
 8. [Conventions](#-conventions)
 9. [팀원 소개](#-팀원-소개)
 
## ✨ Overview

🏆 개발 기간: 24.10.05 ~ 24.12.01<br>
- 현재 부원이 본인이 제출한 과제 결과를 확인하기 위해 엑셀에서 일일이 찾아야 하는 불편함이 있었습니다.
- 이런 문제를 해결하기 위해 과제 확인 시스템을 웹 애플리케이션 개발을 했습니다.
- 교육팀이 과제를 검토하고, 결과를 부원의 정보에 맞게 등록하면, 부원들은 자신의 이름과 전화번호 등의 기본 정보만 입력하여 과제 결과를 간편하게 확인 가능합니다.
- 추가로, 본인의 보증금 현황도 확인할 수 있는 기능을 제공합니다.

## ✨ PIROCHECK의 사이트
##### 🏆 [사이트](https://pirocheck.com/)


## ✨ PIROCHECK의 소통 플랫폼 
##### 🏆 [노션](https://www.notion.so/12a9fae2dd7c80878b6ce3c6c7041818?pvs=0)
##### 🏆 [ZEP](https://zep.us/)


## 👀 서비스 화면
### ✨ 모든 페이지 `모바일(아이폰 12 Pro 기준 max-width:480px)` 지원


### 홈
- 닌텐도 화면 적용
- 화면이 작아질 수록 DS - GBA - GDC 로 구현했다.
- Start 버튼을 통해 캐릭터리스트로 갈 수 있다.
- 로그인 및 회원가입 버튼을 누르수 있다
  
<div>
  <img src="https://github.com/user-attachments/assets/ece63fc2-ca98-441d-9a4c-ddc80e4b7c6b" width="75%"/>
  <img src="https://github.com/user-attachments/assets/ff5d977d-9281-4e4a-b1ce-020682c30086" width="20%"/>
</div>


### 회원가입 & 로그인 & 로그아웃
- `네이버, 카카오 구글 소셜 로그인` 및 유저 회원가입/로그인
- 로그인을 하면 캐릭터 리스트로 넘어간다.
<div>
<img src="https://github.com/user-attachments/assets/0e57d0ce-0ad5-4dac-9ff1-0c662a67d439" width="20%"/>
<img src="https://github.com/user-attachments/assets/b97d5b9e-feef-414d-9360-d90527ecb66f" width="75%">
</div>


### 마이페이지
- `프로필 이미지 변경`
- `닉넴임 변경`
<div>
<img src="https://github.com/user-attachments/assets/34053b7f-a080-4a15-9e72-bcdf90e28dd0" width="75%">
<img src="https://github.com/user-attachments/assets/5e5e1139-14d8-4a05-86ca-0d2a9ad82018" width="20%">
</div>


### 캐릭터 리스트
- 생성, 삭제, 편집 버튼을 이용해 캐릭터 관리 
<div>
<img src="https://github.com/user-attachments/assets/b153ded0-f9ce-4879-a4b9-a610c65515ce" width="20%">
<img src="https://github.com/user-attachments/assets/9a6c0d05-4b62-4656-bb69-3878b0e4e9e7" width="75%">
</div>



### 캐릭터 생성
- 5개의 캐릭터 중 하나를 뽑는다. 하지만 이미 생성된 캐릭터는 못 고른다.
- 캐릭터의 이름과 선택한 이유를 적으면 생성이 된다.
<div>
<img src="https://github.com/user-attachments/assets/30e88da7-8b5b-40be-b4dc-1df85bc1b333" width="75%">
<img src="https://github.com/user-attachments/assets/80557388-2081-4a7a-8b18-91fce71355fa" width="20%">
</div>


### 휴지통
- 캐릭터 리스트에서 버린 캐릭터들을 볼 수 있다.
- 여기서는 복구 및 영구 삭제를 할 수 있다. 
<div>
<img src="https://github.com/user-attachments/assets/2f525651-67e9-42da-95f6-448dc40eba47" width="20%">
<img src="https://github.com/user-attachments/assets/2c5642fe-11b9-421d-8891-6d827256086f" width="75%">
</div>


### 챗봇 
- 음성 인식을 통해 대화를 할 수 있다.
- 영상통화 버전에서는 TTS 구현되어있다.
- Ajax를 통해 실시간으로 캐릭터와 채팅! 
<div>
<img src="https://github.com/user-attachments/assets/3d1a2ca3-fcb7-49c9-b991-172636f5c0c2" width="75%"/>
<img src="https://github.com/user-attachments/assets/2afcf85a-804e-4647-819b-3a02022202e8" width="20%"/>
</div>

<div>
<img src="https://github.com/user-attachments/assets/e8f38e20-c9c6-40e6-847e-9f00ebbedbd8" width="20%"/>
<img src="https://github.com/user-attachments/assets/76c34384-8782-4794-b18c-1a31cd966f6f" width="75%"/>
</div>


### 피드백
- 별점 및 리뷰를 통해 해당 에플리케이션의 평가를 알 수 있다.
- 피드백을 통해 추후 계속 업데이트 할 예정이다.
 <div>
<img src="https://github.com/user-attachments/assets/27097924-b693-4c12-aca7-93dc3a93b3c2" width="75%">
<img src="https://github.com/user-attachments/assets/444327ce-8070-4f2b-9b6d-242c0661db92" width="20%">
</div>



### 감정 로그
- 5개의 캐릭터들과 챗봇을 통해 나온 결과를 보여준다.
- 주간 및 누적 기능이 있어 감저의 정보를 쉽게 파악할 수 있다.
<div>
<img src="https://github.com/user-attachments/assets/9aa369fc-a257-4693-a5ce-6772540210e2" width="20%">
<img src="https://github.com/user-attachments/assets/44cad03c-362b-4fc8-a86a-7e844ceb8c20" width="75%">
</div>

  
## ✨ 주요 기능

- `마스터 권한`
	- 분노, 기쁨, 불안, 두려움, 불안 총 5개의 캐릭터를 생성할 수 있다. 
  	- 생성된 캐릭터를 수정 및 휴지통에 버릴 수 있다. 
	- 휴지통에 버려진 캐릭터는 다시 복구 할 수 있고 영원히 삭제할 수 있다.
	
- `운영진 권한`
	- 총 5개의 캐릭터마다 프롬프트를 설계한다. 
	- 프롬프트에 이전 대화를 기억할 수 있도록 DB에서 해당 데이터를 찾아와 프롬프트에 넘겨준다.
  
- `부원 권한`
	- Open AI를 통해 API와 연결한뒤 사용자 답변에 따른 AI 답변이 제공이 된다.
	- Ajax를 통해 실시간으로 대화가 진행되며 시간 마지막 답변들도 실시간으로 추가가 된다. 
   
- `MVP`
	- 영상 통화 화면에 넘어간뒤 사용자가 답하면 AI 답변이 TTS로 제공이 된다. 
	- 여러 목소리 TTS 기능이 설정이 되어있다.


- `EC2 Dcoker`
	- 사용자들이 서비스를 이용하고 나서 후기를 올리 수 있는 공간이다.
   


## 🖥️ 개발 환경

**Management Tool**
- 형상 관리 : Git
- 커뮤니케이션 : Zep, Notion
- 디자인 : Figma

**🐳 Backend**
- Java `17`
- SpringBoot `3.3.4`
- `Spring JPA`
- `Spring Thymeleaf`
- `Spring Session`

**🦊 Frontend**
- lang: HTML5, CSS3, JAVASCRIPT

**🗂️ DB**
- `MySQL`  `8.0.4`

**🌐 Server**
- AWS EC2 (Ubuntu `20.04`)
- Nginx `1.23` (Reverse Proxy)
- `Docker`

**🗝️ API**
- [Web Speech API](https://developer.mozilla.org/en-US/docs/Web/API/Web_Speech_API)   

**🔨 IDE**
- Intellj `2023.2`
- MySQL Workbench `8.0.29`
- VSCode `1.69.2`

**🖼️ Dependencies**
```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.mysql:mysql-connector-j'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

## 💫 시스템 아키텍처

![image](https://github.com/user-attachments/assets/265a7eca-8434-411e-841d-2d1887dabc82)


## ✨ 기술 특이점
- 


# 📂 기획 및 설계 산출물

### [💭 요구사항 정의 및 기능 명세](https://www.notion.so/12a9fae2dd7c80b0a908c1b995a0388a)

![image](https://github.com/user-attachments/assets/cc99b56b-eef3-409c-a344-7e368e974fd7)


### [🎨 화면 설계서](https://www.figma.com/design/jBxM9KSvpz6mEfBB2t4dHx/Untitled?node-id=0-1&node-type=canvas&t=0uFlK9ORSMK6FDbv-0)

![image](https://github.com/user-attachments/assets/36869a76-dc8a-4666-a8e0-c674197676f4)



### [✨ ER Diagram](https://www.erdcloud.com/d/7hhDca3S7tqv6RbLW)

![image](https://github.com/user-attachments/assets/70248062-0edd-4be6-a741-d2256089f4a2)


# 💞 팀원 소개
##### ❤️‍🔥 PIROCHECK를 개발한 `피로그래밍 22기 운영진` 팀원들을 소개합니다!

| **[최승호](https://github.com/chltmdgh522)** | **[이지현](https://github.com/ljh130334)** | **[김민수](https://github.com/devkev00)** |
| :---------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/e792dfc6-e2a7-4b42-b5a5-27672d4df6c7" width="400"> | <img src="https://github.com/user-attachments/assets/ea00de58-e6c0-46a9-9ff7-646665798c5e" width="400"> | <img src="https://github.com/user-attachments/assets/4b0625e9-a7b2-460e-9524-0e35c698dc51" width="400"> |
|  PM & Backend | Frontend & Designer |  Backend |




## 😃 팀원 역할

- **최승호**
  - 팀장, 기획, 회원 및 MVP API 개발 및 서버 배포
- **이지현**
  - 회원관리, 마이페이지, 피드백, 감정 로그 
- **김민수**
  - 과제 API 개발 및 서버 배포
