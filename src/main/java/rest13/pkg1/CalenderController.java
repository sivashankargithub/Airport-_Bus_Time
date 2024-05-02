package rest13.pkg1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalenderController {
	@Autowired
	CalenderRepo cr;
	
	@PostMapping("/meetings/cs/add")
	public String addEvent(@RequestBody CalenderEntity ce) {
		cr.save(ce);
		return "Inserted Successfully";
	}
	
	public List<CalenderEntity> getEventsByYear(@PathVariable int year){
		return cr.findAll();
	}
}
