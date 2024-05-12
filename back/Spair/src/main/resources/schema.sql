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
    FOREIGN KEY (post_id) REFERENCES post(post_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- user 기본 데이터 추가 
INSERT INTO user (login_id, password, nickname, name, gender, address, latitude, longitude)
VALUES ("ssafy1", "ssafy1111", "김싸피", "김싸피", 1, "서울특별시 강남구 테헤란로 212", 37.501712, 127.039603),
	   ("ssafy2", "ssafy2222", "필라고수", "이싸피", 2, "서울특별시 강남구 테헤란로 212", 37.501712, 127.039603),
	   ("ssafy3", "ssafy3333", "ssafyee", "박싸피", 1, "경기도 부천시 원미구 길주로 210", 37.503538, 126.765895),
	   ("ssafy4", "ssafy4444", "자바짱", "정싸피", 2, "서울특별시 강남구 테헤란로 212", 37.501712, 127.039603) ;

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