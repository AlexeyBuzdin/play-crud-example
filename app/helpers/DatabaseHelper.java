package helpers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;

public class DatabaseHelper {

    private static String sql = "SELECT (DATEDIFF(dd, BOOKED_FROM, BOOKED_TO) * PRICE) VALUE " +
            "FROM APARTMENT_HISTORY \n" +
            "INNER JOIN APARTMENT_PROPOSAL ON APARTMENT_HISTORY.APARTMENT_PROPOSAL_ID = APARTMENT_PROPOSAL.ID " +
            "WHERE APARTMENT_HISTORY.ID = :id";

    public static Long getFullPrice(Long id) {
        SqlQuery query = Ebean.createSqlQuery(sql).setParameter("id", id);

        return query.findUnique().getLong("VALUE");
    }

}
