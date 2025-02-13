SELECT
    YEAR(SALES_DATE) AS YEAR,
    MONTH(SALES_DATE) AS MONTH,
    GENDER,
    COUNT(DISTINCT T1.USER_ID) AS USERS
FROM USER_INFO T1
JOIN ONLINE_SALE T2
ON T1.USER_ID = T2.USER_ID
WHERE GENDER IS NOT NULL
GROUP BY
    YEAR(SALES_DATE),
    MONTH(SALES_DATE),
    GENDER
ORDER BY
    YEAR(SALES_DATE),
    MONTH(SALES_DATE),
    GENDER

    