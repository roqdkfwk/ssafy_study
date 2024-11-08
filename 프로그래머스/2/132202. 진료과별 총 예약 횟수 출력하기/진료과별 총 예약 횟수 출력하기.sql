-- 코드를 입력하세요
# 2022년 5월 예약한 환자 수 진료과코드 별로 조회
SELECT MCDP_CD AS 진료과코드, COUNT(DISTINCT PT_NO) AS 5월예약건수
FROM APPOINTMENT
WHERE DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'
GROUP BY MCDP_CD
ORDER BY 5월예약건수, 진료과코드;

