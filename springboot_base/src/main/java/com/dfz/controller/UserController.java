package com.dfz.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dfz.model.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * User Controller class
 * 
 * this is an api example of handling requests(GET, POST, PUSH, DELETE) 
 * and returning json.
 * 
 * for api details, visit: http://localhost:8080/swagger-ui.html
 * 
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	//user Map instead of DB to store data
	static Map<Long, User> users=Collections.synchronizedMap(new HashMap<Long, User>());
	
	/**
	 * handle "/api/v1/users" GET request and get a list of User object
	 *  
	 * @return	list	a list of User
	 */
	@ApiOperation(value = "Get User List", notes = "Get a list of user object")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")	//@GetMapping("")
	public List<User> getUserList() {
		List<User> list=new ArrayList<User>(users.values());
		return list;
	}
	
	
	/**
	 * handle "/api/v1/users" POST request and create a user based on the submit user object
	 *  
	 * @param 	user	a User object
	 * @return	"success"	
	 */
	@ApiOperation(value="Create User", notes="Create a new user based on the submit user object")
	@ApiImplicitParam(name="user", value="User object", required = true, dataType = "User")
	@RequestMapping(value = "", method=RequestMethod.POST)	//@PostMapping("")
	public String postUser(@ModelAttribute User user) {
		users.put(user.getId(), user);
		
		return "success";
	}
	
	
	/**
	 * handle "/api/v1/users/{id}" GET request and get the detail of a user
	 *  
	 * @param 	id		user's id
	 * @return	a User object
	 */
	@ApiOperation(value="Get User Info", notes="Get user's detailed Info based on the user id in url")
	@ApiImplicitParam(name="id", value="User ID", required = true, dataType = "Long")
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")	//@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return users.get(id);
	}
	
	
	/**
	 * handle "/api/v1/users/{id}" PUT request and update a user's info
	 *  
	 * @param 	id		user's id
	 * @param 	user	a user object with new info
	 * @return	"success"
	 */
	@ApiOperation(value="Update User Info", notes="Update user's detailed Info based on the user id in url and the user object with new info")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="User ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "user", value = "User Object", required = true, dataType = "User")
	})
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)	//@PutMapping("/{id}")
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u=users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		
		return "success";
		
	}
	
	
	/**
	 * handle "/api/v1/users/{id}" DELETE request and delete a user
	 *  
	 * @param 	id		user's id
	 * @return	"success"
	 */
	@ApiOperation(value="Delete User", notes="Delete a user based on the user id in url")
    @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "Long")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)	//@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		
		return "success";
	}

}
