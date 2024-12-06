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

### 🍀 부원 기준 🍀
### 홈
- 로그인 유도 화면
  
<div>
  <img src="https://github.com/user-attachments/assets/60500261-3b11-4ea3-8988-1861cfb63e26" width="75%" height="320px"/>
  <img src="https://github.com/user-attachments/assets/be2fcb76-6ee9-4c24-a784-4645ad5b72b0" width="20%"/>
</div>


### 로그인 화면
- 이름 + 전화번호를 입력한다. 
<div>
<img src="https://github.com/user-attachments/assets/04c0b63e-f32b-40ee-a657-f01895564dea" width="20%"/>
<img src="https://github.com/user-attachments/assets/dabf47c4-051f-460d-9378-7ffa9ccaba51" width="75%" height="360px" >
</div>


### 과제 결과 화면
- 목록을 클릭하면서 과제 결과를 확인한다. 
- O면 성공, X는 실패, △는 미흡이다.
- 각각 0원 -20000, -10000원이 차감된다.
<div>
<img src="https://github.com/user-attachments/assets/99ae35ae-b5f8-46d8-b2f2-94f07f6e5ed0" width="75%" height="360px"> 
<img src="https://github.com/user-attachments/assets/47619149-244c-4326-8e2c-6b81a19ca8a8" width="20%">
</div>


### 🍀 운영진 기준 🍀
### 홈
- 로그인/회원가입 유도 화면
<div>
<img src="https://github.com/user-attachments/assets/cef7d507-fd4f-4bba-bb3c-73f5f5a061eb" width="20%">
<img src="https://github.com/user-attachments/assets/87f098e5-6163-4f2c-8a5f-996d448276ce" width="75%" height="320px">
</div>


### 회원가입 화면
- 이름 + 전화번호 + 비밀번호 + 비밀번호 확인 을 입력한다.
- 이때 가입을 하면 PREADMIN으로 로그인을 할 수 없다.
- 마스터 권한자가 ADMIN으로 승격할때까지 기다려야된다. 
<div>
<img src="https://github.com/user-attachments/assets/a3c34dde-3ec6-4c0b-8feb-34da6557f4ab" width="75%" height="360px">
<img src="https://github.com/user-attachments/assets/4a9a4eb0-bf33-43b9-b7c6-e7cbc53052c1" width="20%">
</div>


### 로그인 화면
- 운영진은 일반 부원과 다르게 보안을 위해 이름 + 전화번호 + 비밀번호를 입력해야된다.
<div>
<img src="https://github.com/user-attachments/assets/bf988bf8-e32f-476f-ae3e-fd0334779419" width="75%" height="360px">
<img src="https://github.com/user-attachments/assets/08f3d250-a309-44f3-932b-36cc9f5fd5a7" width="20%">
</div>


### 부원 등록 화면
- 이름 + 전화번호로 부원들의 회원을 등록할 수 있다. 
<div>
<img src="https://github.com/user-attachments/assets/b153ded0-f9ce-4879-a4b9-a610c65515ce" width="20%">
<img src="https://github.com/user-attachments/assets/ff7cbce7-bab4-43a2-93a2-83f9f70f13a9" width="75%" height="360px" >
</div>


### 과제 결과 반영 화면
- 방어권 갯수 및 과제 결과를 반영할 수 있다.
- 체크박스는 해당 부원이 과제를 확인 또는 미확인 할 수 있도록 넣어났다.
- 예시로 1주차 월요일 과제 제로초 27강이 체크박스가 해제가 된채로 있으면 해당 부원은 과제 이에 대한 과제 결과를 확인할 수 없다.
<div>
<img src="https://github.com/user-attachments/assets/786c73e8-7629-47ea-93f3-60acb1c53ba6" width="75%" height="360px">
<img src="https://github.com/user-attachments/assets/fae71269-4862-43e8-af10-27d195f2c4d3" width="20%">
</div>

### 🍀 마스터 기준 🍀
### 운영진 등급 향상 화면
- 처음 회원 가입한 운영진은 PREADMIN이다. 따라서 ADMIN으로 올릴 수 있다.  
<div>
<img src="https://github.com/user-attachments/assets/a7b4603d-3993-459e-a694-f5d4bf3e7e67" width="20%">
<img src="https://github.com/user-attachments/assets/c0ba8d52-4852-4a51-8627-68d34133c1db" width="75%" height="360px" >
</div>

  
## ✨ 주요 기능

- `마스터 권한`
	- 처음 가입한 운영진은 등급이 PREADMIN이다. 이때 마스터 권한자(교육팀장)가 ADMIN으로 승격해야지 운영진은 부원들 과제를 운영할 수 있다.
   	- 이렇게 한 이유는 무분별한 운영진 유입을 막기 위해서이다.
   	- 따라서 운영진 url도 /system이다.
	
- `운영진 권한`
	- 부원들의 과제를 결과를 메길 수 있고, 보증금 방어권을 수여할 수 있다. 
	- 동아리 부원을 등록할 수 있다.

- `부원 권한`
	- 부원들 등급은 USER이다.
	- 따로 회원가입은 안해도 된다. 왜냐하면 앞에서 말했듯이 운영진이 동아리 부원을 다 등록을 해서 전화번호 + 이름만으로 로그인만 하면 된다.
 	- 과제 결과를 확인만 하면 된다. 
   
- `MVP`
	- 해당 주차에 MVP 부원은 해당 화면 들어가면 바로 축하메시지가 뜬다.  
	- MVP 테이블의 flag, visual 컬럼을 활용하여 현재 1주차면 2,3,4, 챌린저 mvp는 클릭 못하게 구현을 해놓았다. 시간이 지나면 차츰 해제할 것이다.
   	- 참고로 MVP 권한은 오직 마스터만 할 수 있다.

- `EC2 Dcoker`
	- 스프링과 DB를 Docker를 활용해서 배포를 했다.
 	- EC2에서 pull을 한 후 도커 서버를 실행했다.  

   
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
- 배포를 완료한 후 6시간 간격으로 EC2의 CPU 사용률이 99%까지 치솟아 서버가 다운되는 문제가 발생했다. 이를 해결하기 위해 swap을 도입했는데, 이는 메모리 부족 시 디스크 공간을 임시로 활용함으로써 메모리 부족으로 인한 시스템 과부하를 완화하는 역할을 한다. 이를 통해 CPU 사용률 급등 문제를 해결하고 서버의 안정성을 확보할 수 있었다.


# 📂 기획 및 설계 산출물

### [💭 요구사항 정의 및 기능 명세](https://www.notion.so/12a9fae2dd7c80b0a908c1b995a0388a)

![image](https://github.com/user-attachments/assets/56a042b1-ef25-4361-9248-6fd36470fea7)


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
  - 피그마 디자인, 프론트 개발발
- **김민수**
  - 과제 API 개발 및 서버 배포
