package com.qqfall.cloud.mytest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qqfall.cloud.mytest.dao.User;
@RestController
@RequestMapping(path ="/user")
public class UserController {
	public static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@GetMapping()
	public List<UserDto> getAll()
	{
		List<User> users=userService.getAll2();
		logger.info("users"+users);
		List<UserDto> dtos=new ArrayList<>();
		for(User user:users)
		{
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setName(user.getName());
			dtos.add(dto);
		}
		return dtos;
	}
	
	@PostMapping()
	public UserDto add(@RequestBody UserDto dto)
	{
		User user=userService.add(dto);
		logger.info("user"+user);
		UserDto dto2 = new UserDto();
		dto2.setId(user.getId());
		dto2.setName(user.getName());
		return dto2;
	}
}
