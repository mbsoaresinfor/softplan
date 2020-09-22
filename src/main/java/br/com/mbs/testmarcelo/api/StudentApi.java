package br.com.mbs.testmarcelo.api;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface StudentApi <T>{

	
		@ApiOperation(value = "Save Student")
		@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "sucess the save Student"),			    
			    @ApiResponse(code = 405, message = "Problem in the save Student"),
			})
		
	 	@RequestMapping( method = RequestMethod.POST, produces="text/plain" )	 
	    ResponseEntity<String> save(@RequestBody T student);
		
		
		@ApiOperation(value = "List Student",responseContainer="List")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "Sucess search list Student")			   
				})	 
		 @RequestMapping( method = RequestMethod.GET, produces="application/json")	 
		  public List<T> getStudents();
		
		  
		 
		 @ApiOperation(value = "Get one Student")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "sucess search one Student"),
				    @ApiResponse(code = 404, message = "Student not found"),
				})
		 @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")	 
		  public ResponseEntity<T> getStudent(@PathVariable Long id) ;
			
		 
		 
		 @ApiOperation(value = "delete Student")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "sucess remove Student"),
				    @ApiResponse(code = 404, message = "Student not found"),			  
				})	 
		 @DeleteMapping("/{id}")
		 public ResponseEntity<Void> delete(@PathVariable Long id) ;
			
		 
		 
		 @ApiOperation(value = "update Student")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "sucess update Student"),
				    @ApiResponse(code = 405, message = "Problem in the update Student"),			  
				})	 
		 @RequestMapping( method = RequestMethod.PUT, produces="text/plain")	 
		 public ResponseEntity<String> update(@RequestBody T student);
			


}
