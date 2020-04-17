package com.example.reqresclientandroid.data.model;

import com.google.gson.annotations.SerializedName;


public class BaseRespone{

	@SerializedName("data")
	private UserResponse userResponse;

	public UserResponse getData(){
		return userResponse;
	}

	@Override
 	public String toString(){
		return 
			"BaseRespone{" + 
			"data = '" + userResponse + '\'' +
			"}";
		}
}