SELECT
    T2.INGREDIENT_TYPE,
    SUM(T1.TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF T1
JOIN ICECREAM_INFO T2
ON T1.FLAVOR = T2.FlAVOR
GROUP BY INGREDIENT_TYPE