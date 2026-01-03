package com.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class Cloudinaryimg {

	@Bean
	public Cloudinary cloudinary()
	{
		return new Cloudinary(ObjectUtils.asMap("cloud_name" , "dczngjmsb" , "api_key" ,"899889586444528" , "api_secret" , "p3H7IaGIcrI8uKG6357cRQ6PqmQ"));
	}

	

}
