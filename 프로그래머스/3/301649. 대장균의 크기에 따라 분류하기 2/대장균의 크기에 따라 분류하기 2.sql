-- 코드를 작성해주세요
SELECT A.ID, CASE WHEN B.per <= 0.25 THEN "CRITICAL"
                WHEN B.per <= 0.5 THEN "HIGH"
                WHEN B.per <= 0.75 THEN "MEDIUM"
                ELSE "LOW"
                END AS COLONY_NAME
FROM ECOLI_DATA A
JOIN (SELECT ID, 
     PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS per
      FROM ECOLI_DATA
) B
ON A.ID = B.ID
ORDER BY A.ID