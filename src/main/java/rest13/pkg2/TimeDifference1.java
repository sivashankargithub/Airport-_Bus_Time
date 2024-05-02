package rest13.pkg2;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeDifference1 {
	public static List<LocalTime> loadData(String fname)throws Exception{
		List<LocalTime>list1 = new ArrayList<>(); 
		File f1 = new File(fname);
		Scanner sc1 = new Scanner(f1);
		while(sc1.hasNext()) {
			String s1=sc1.nextLine();
			list1.add(LocalTime.parse(s1));
		}
		return list1;
	}
	@GetMapping("/bmtc/bus/timings/{ftime}/{bttime}")
    public String getBus(@PathVariable String ftime, @PathVariable long bttime) throws Exception{
		LocalTime bus=null;
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        LocalDateTime ldt1=LocalDateTime.parse(ftime,dtf);
        long atime = ldt1
        		.atZone(ZoneId.systemDefault())
        	    .toInstant().toEpochMilli();
        bttime=bttime*60*60*1000;
        long diff=atime-bttime;
        Instant instant1=Instant.ofEpochMilli(diff);
        LocalDateTime ldt2=LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
        System.out.println(diff);
        LocalTime lt=ldt2.toLocalTime();
        System.out.println(lt);
        List<LocalTime> list1=loadData("airport.txt");
        for (int i = 0; i < list1.size(); i++) {
			if(list1.get(i).isBefore(lt)|| list1.get(i).equals(lt)) {
				bus=list1.get(i);
				break;
			}
		}
        String s1="You hace to take bus before "+ldt2+" and the last bus is at "+bus;
        return s1;
    }
}
