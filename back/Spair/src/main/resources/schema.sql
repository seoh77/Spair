DROP DATABASE IF EXISTS `spair`;

CREATE DATABASE spair ;

USE spair ;

CREATE TABLE `user` (
	`user_id`	BIGINT	NOT NULL  AUTO_INCREMENT,
	`login_id`	VARCHAR(20)	NOT NULL  UNIQUE,
	`password`	VARCHAR(20)	NOT NULL,
	`nickname`	VARCHAR(20)	NOT NULL  UNIQUE,
	`name`	VARCHAR(20)	NOT NULL,
	`gender`	TINYINT(1)	NOT NULL,				-- 성별 : 남자(1), 여자(2)
	`address`	VARCHAR(100)	NOT NULL,			-- 도로명 주소
	`latitude`	DOUBLE	NOT NULL,
	`longitude`	DOUBLE	NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE `sports_center` (
	`center_id`	BIGINT	NOT NULL  AUTO_INCREMENT,
	`road_address`	VARCHAR(100)	NOT NULL,		-- 도로명 주소
	`local_address`	VARCHAR(100)	NOT NULL,		-- 지번 주소
	`latitude`	DOUBLE	NOT NULL,
	`longitude`	DOUBLE	NOT NULL,
    PRIMARY KEY (center_id)
);

CREATE TABLE `post` (
	`post_id`	BIGINT	NOT NULL  AUTO_INCREMENT,
	`user_id`	BIGINT	NOT NULL,
	`title`	VARCHAR(100)	NOT NULL,
	`content`	TEXT	NOT NULL,
	`status`	TINYINT(1)	NOT NULL	DEFAULT 1,		-- 모집여부 : 모집중(1), 모집 완료(0)
	`created_date`	TIMESTAMP	NOT NULL,
	`modified_date`	TIMESTAMP	NULL,
	`center_id`	BIGINT	NOT NULL,
	`exercise_type`	VARCHAR(10)	NOT NULL,				-- 운동종류 : PT, 필라테스, 기타
	`price`	INT	NOT NULL,								-- 1인당 내는 금액
	`gender`	TINYINT(1)	NOT NULL,					-- 모집성별 : 남자(1), 여자(2), 상관없음(3)
	`recruitment_num`	VARCHAR(10)	NOT NULL,			-- 모집인원 : 1명, 2명, 3명 이상, 상관없음
    PRIMARY KEY (post_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (center_id) REFERENCES sports_center(center_id)
);

CREATE TABLE `comment` (
	`comment_id`	BIGINT	NOT NULL  AUTO_INCREMENT,
	`post_id`	BIGINT	NOT NULL,
	`user_id`	BIGINT	NOT NULL,
	`content`	TEXT	NOT NULL,
	`status`	TINYINT(1)	NOT NULL,			-- 상태 : 공개(1), 비공개(0)
	`created_date`	TIMESTAMP	NOT NULL,
	`modified_date`	TIMESTAMP	NULL,
	`parent_id`	BIGINT	NULL,					-- 부모 댓글 ID
    PRIMARY KEY (comment_id),
    FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- user 기본 데이터 추가 
INSERT INTO user (login_id, password, nickname, name, gender, address, latitude, longitude)
VALUES ("katarinabluu", "temp11", "카리나", "유지민", 2, "서울특별시 성동구 뚝섬로 51 옥수강변풍림아이원아파트", 37.542215, 127.019368),
	   ("winter", "temp11", "겨울", "김민정", 2, "서울특별시 광진구 구의강변로 11 자양한양아파트", 37.531413, 127.091182),
	   ("jamiepark", "temp11", "지민팍", "박지민 ", 2, "경기도 부천시 원미구 길주로 210", 37.503538, 126.765895),
	   ("yerinb", "temp11", "스퀘어", "백예린 ", 2, "경기도 하남시 감일중앙로 20 감일에코앤e편한세상", 37.504454, 127.153881),
	   ("yujinAn", "temp11", "아쪼", "안유진", 2, "경기도 하남시 신평로 36 덕풍벽산블루밍아파트", 37.536217, 127.204765),
	   ("everyoung10", "temp11", "공듀", "장원영", 2, "경기도 부천시 소향로 181 중동센트럴파크푸르지오 아파트", 37.502280, 126.767966),
	   ("lizyeyo", "temp11", "리즈", "김지원", 2, "서울특별시 강남구 언주로 427 디오빌역삼아파트", 37.501461, 127.042683),
	   ("hanniii", "temp11", "하니", "팜하니", 2, "서울특별시 강남구 언주로93길 25 역삼마에스트로", 37.503151, 127.040136),
	   ("yysbeast", "temp11", "노래짱", "양요섭", 1, "서울특별시 강남구 역삼동 664-21 역삼프라임아파트", 37.505236, 127.039469),
	   ("baekhyunee", "temp11", "몽룡", "변백현", 1, "서울특별시 강남구 언주로 420 역삼자이아파트", 37.500482, 127.044464),
	   ("jongsuk0206", "temp11", "쫑", "이종석", 1, "서울특별시 강남구 역삼동 826-29 역삼동우정에쉐르1차아파트", 37.496204, 127.030212),
	   ("hihyunwoo", "temp11", "미래의배우", "이현우", 1, "서울특별시 서초구 서초동 강남대로55길 9-15 아르젠아파트", 37.495404, 127.027708),
	   ("nwh91", "temp11", "나무", "남우현", 1, "서울특별시 서초구 서초4동 사평대로58길 6 현대썬앤빌 강남", 37.503548, 127.023499),
	   ("hero11", "temp11", "히어로", "임영웅", 1,  "서울특별시 강남구 역삼동 826-29 역삼동우정에쉐르1차아파트", 37.496204, 127.030212),
	   ("jsomin86", "temp11", "쏨", "전소민", 2, "서울특별시 강남구 역삼동 664-21 역삼프라임아파트", 37.505236, 127.039469),
	   ("eunji11", "temp11", "러브데이", "정은지", 2, "서울특별시 강남구 언주로93길 25 역삼마에스트로", 37.503151, 127.040136),
	   ("jinjin", "temp11", "찐", "김석진", 1, "서울특별시 강남구 언주로 420 역삼자이아파트", 37.500482, 127.044464),
	   ("kimhyungjun", "temp11", "쭈니", "김형준", 1, "대구광역시 달성군 화원읍 비슬로539길 35 대곡역래미안아파트", 35.810900, 128.507637),
	   ("nayeonyny", "temp11", "하트", "임나연", 2, "대구광역시 달서구 대천동 497-2 월배태영데시앙아파트", 35.817842, 128.520822),
	   ("yerimiese", "temp11", "예리", "김예림", 2, "경기도 부천시 원미구 중3동 1054 증흥마을", 37.504352, 126.772706) ;

-- sports_center 기본 데이터 추가
INSERT INTO sports_center (road_address, local_address, latitude, longitude)
VALUES ("서울특별시 강남구 테헤란로 146", "서울특별시 강남구 역삼1동 736-6", 37.500264, 127.035526),
	   ("서울 강남구 테헤란로 27길 18 B2", "서울특별시 강남구 역삼동 678", 37.503250, 127.038033),
       ("서울 강남구 테헤란로 33길 7 대영빌딩 2층", "서울특별시 강남구 역삼동 678-28", 37.502976, 127.039309);

-- post 기본 데이터 추가
INSERT INTO post (user_id, title, content, status, created_date, center_id, exercise_type, price, gender, recruitment_num)
VALUES (1, "SSAFY 헬스장 그룹PT 2인 급구", "SSAFY 헬스장에서 같이 그룹 PT 받으실 분 찾습니다!!", 1, now(), 1, "PT", 40000, 1, 2),
	   (2, "싸필라테스 4인 그룹 수업 같이해요", "같이 필라테스 하실 분 구합니다!!", 1, now(), 3, "필라테스", 20000, 2, 2) ;

-- comment 기본 데이터 추가
INSERT INTO comment (post_id, user_id, content, status, created_date, parent_id) 
VALUES (1, 4, "역삼역 근처에서 일하는 직장인입니다. 그룹 PT 희망하는데, 혹시 연령대가 어떻게 되세요?", 1, now(), NULL),
	   (1, 1, "저도 역삼역 근처 멀티캠퍼스 다닙니다! 20대 입니다.", 1, now(), 1) ;