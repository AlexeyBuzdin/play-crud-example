package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelper {

    public static String formatPrice(Long price) {
        String priceString = price + "";
        int length = priceString.length();
        if (length > 2) return priceString.substring(0, length - 2) + "." + priceString.substring(length - 2);
        String result = "0." + price;
        return result.length() == 3 ? result : "0.0" + price;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

}
