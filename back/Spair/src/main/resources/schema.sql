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
