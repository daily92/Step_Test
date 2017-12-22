CREATE TABLE board_data
(num NUMBER PRIMARY KEY,
writer VARCHAR2(100) NOT NULL,
title VARCHAR2(100) NOT NULL,
/*	한 폴더에 파일을 계속 저장. 그러다보면 파일명 같은 거 있으면 덮어쓰기 됨!! (오 안돼)
 *  그래서 그것을 구분하기 위해 두 개의 ..명 필요
 * 		orgFileName		=> 원래 파일명
 * 		saveFileName	=> 새로 저장된 파일 명
 *  이제는 문자열이 아니고 파일이 들어와... 이제 파일을 다운로드 하고.. 그런 것을 배울 거야
 */
orgFileName VARCHAR2(100) NOT NULL,
saveFileName VARCHAR2(100) NOT NULL,
fileSize NUMBER NOT NULL,
regdate DATE);

CREATE SEQUENCE board_data_seq ;