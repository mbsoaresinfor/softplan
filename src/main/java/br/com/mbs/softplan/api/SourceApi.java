package br.com.mbs.softplan.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping("/source")
public class SourceApi {

	@RequestMapping(method = RequestMethod.GET)	 
	 public String getCodigoFonteGitHub() {
		return "https://github.com/mbsoaresinfor/softplan";
	}
		
	
	
}
