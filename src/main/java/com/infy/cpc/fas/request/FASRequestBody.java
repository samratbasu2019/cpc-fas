package com.infy.cpc.fas.request;

import java.util.List;

public class FASRequestBody {
private String name;
private String sex;
private String dob;
private List<FASRequestAddress> fasAddress;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public List<FASRequestAddress> getFasAddress() {
	return fasAddress;
}
public void setFasAddress(List<FASRequestAddress> fasAddress) {
	this.fasAddress = fasAddress;
}


}
