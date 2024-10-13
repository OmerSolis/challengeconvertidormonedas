# challengeconvertidormonedas
Pequeño aplicativo que se conecta con una API que permite obtener el cambio de moneda (money exchange). 
Presenta 6 opciones de cambio:
1. De Dólar a Peso Argentino
2. De Peso argentino a Dólar
3. De Dólar a Real Brasilero
4. De Real Brasilero a Dólar
5. De Dólar a Peso Colombiano
6. De Peso Colombiano a Dólar

Esto se logra mediante la conexión a la API https://v6.exchangerate-api.com/v6/51bbe2e43f7dfc64c538e186/latest/<moneda_origen>

La API devuelve, entre otros datos, un listado con los valores de cambio de muchas monedas, dada una de origen.

Para el desarrollo de este desafío se creó la clase Conversor, que contiene la llamada a la API, y un registro llamado "Rate", que contiene la misma estructura de salida de la API. 
 
