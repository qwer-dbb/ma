package cn.smbms.tools;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String s) {
        Date date = null;
        if (s == null || "".equals(s.trim())) {
            return null;
        }
        try {
            if (s.length() == 10) {
                date = sdf.parse(s);
            }else if (s.length() == 19){
                date = sdf2.parse(s);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
