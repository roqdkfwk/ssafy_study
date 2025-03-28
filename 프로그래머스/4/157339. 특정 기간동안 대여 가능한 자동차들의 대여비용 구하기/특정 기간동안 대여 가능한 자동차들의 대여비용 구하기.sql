-- CAR_RENTAL_COMPANY_CAR : 자동차 정보
-- CAR_RENTAL_COMPANY_RENTAL_HISTORY : 자동차 대여 기록
-- CAR_RENTAL_COMPANY_DISCOUNT_PLAN : 할인 정책 정보

-- '세단' 또는 'SUV'
-- 11월 1일부터 11월 30일까지 대여 가능
-- 30일간의 대여 금액이 50만원 이상 200만원 미만

SELECT T1.CAR_ID
    , T2.CAR_TYPE
    , ROUND(T2.DAILY_FEE * (1 - (T3.DISCOUNT_RATE / 100)) * 30) AS FEE
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY T1
JOIN CAR_RENTAL_COMPANY_CAR T2
ON T1.CAR_ID = T2.CAR_ID
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN T3
ON T2.CAR_TYPE = T3.CAR_TYPE
WHERE T2.CAR_TYPE IN ('세단', 'SUV')
    AND T1.CAR_ID NOT IN (SELECT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
                          WHERE START_DATE <= '2022-11-30'
                            AND END_DATE >= '2022-11-01')
    AND T3.DURATION_TYPE = '30일 이상'
GROUP BY T1.CAR_ID
HAVING FEE BETWEEN 500000 AND 2000000
ORDER BY FEE DESC, T2.CAR_TYPE, T1.CAR_ID DESC
                          