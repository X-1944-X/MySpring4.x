package nju.software.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class  DateUtil {

    public static java.sql.Date getCurrentDateForSql() throws ParseException {
        Date date = new Date();//获得系统时间
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time1 = sdf.parse( nowTime );

        return new java.sql.Date(time1.getTime());

    }
}
