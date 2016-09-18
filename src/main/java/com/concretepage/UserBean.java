package com.concretepage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
	private String name;
	private Integer age;

	public String saveUser() {
        User user = new User();
        user.setName(name);
        user.setAge(age);
		UserDAO.save(user);

		return "output";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}