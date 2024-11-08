-- 코드를 입력하세요
# 고양이 몇마리? 개 몇마리?
# 고양이를 개보다 먼저 조회
SELECT ANIMAL_TYPE, COUNT(*) AS count
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE