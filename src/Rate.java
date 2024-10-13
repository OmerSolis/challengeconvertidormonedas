import java.util.Map;

public record Rate(String result,
                   String documentation,
                   String terms_of_use,
                   String time_last_update_unix,
                   String time_last_update_utc,
                   String time_next_update_unix,
                   String time_next_update_utc,
                   String base_code,
                   Map<String, Double> conversion_rates) {
}
/*
	"result": "success",
            "documentation": "https://www.exchangerate-api.com/docs",
            "terms_of_use": "https://www.exchangerate-api.com/terms",
            "time_last_update_unix": 1585267200,
            "time_last_update_utc": "Fri, 27 Mar 2020 00:00:00 +0000",
            "time_next_update_unix": 1585353700,
            "time_next_update_utc": "Sat, 28 Mar 2020 00:00:00 +0000",
            "base_code": "USD",
            "conversion_rates": {

 */