package com.my.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.web.user.model.User;
@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController {
	private final static Map<String,User> users = new HashMap<String,User>();
	//模拟数据源,构造初始数据
		public UserController(){
			users.put("张起灵", new User("张起灵", "闷油瓶", "02200059", "menyouping@yeah.net"));
			users.put("李寻欢", new User("李寻欢", "李探花", "08866659", "lixunhuan@gulong.cn"));
			users.put("拓拔野", new User("拓拔野", "搜神记", "05577759", "tuobaye@manhuang.cc"));
			users.put("孙悟空", new User("孙悟空", "美猴王", "03311159", "sunhouzi@xiyouji.zh"));
		}
		/**
		 * 添加新用户
		 * @see 访问/user/add时，GET请求就执行addUser(Model model)方法，POST请求就执行addUser(User user)方法
		 */
		/**
		 * 添加新用户
		 * @see 访问/user/add时，GET请求就执行addUser(Model model)方法，POST请求就执行addUser(User user)方法
		 */
		@RequestMapping(value="/add", method=RequestMethod.GET)
		public String addUser(Model model,HttpServletRequest request,HttpServletResponse response){
			//这里要传给前台一个空对象，否则会报告java.lang.IllegalStateException异常
			//异常信息为Neither BindingResult nor plain target object for bean name 'user' available as request attribute
			//并且传过去的key值要与前台modelAttribute属性值相同，即model.addAttribute("user", new User());
			//我们也可以写成下面这种方式，此时SpringMVC会自动把对象名转换为小写值作为key，即User-->user
			model.addAttribute(new User());
			System.out.println("add............");
			return "user/add";
			
		}
		@RequestMapping(value="/add", method=RequestMethod.POST)
		public String addUser(User user){ //这里参数中的user就应该与add.jsp中的modelAttribute="user"一致了
			users.put(user.getUsername(), user);
			return "redirect:/user/list";
		}
		
		/**
		 * 列出所有用户信息
		 */
		@RequestMapping("/list")
		public @ResponseBody String list(Model model){
			model.addAttribute("users", users);
			System.out.println("list............");
			return "user/list";
		}
		
		/**
		 * 查询用户信息
		 * @see 访问该方法的路径就应该是"/user/具体的用户名"
		 * @see 这里value="/{username}"的写法，需要格外注意一下，它是一个路径变量，此时用来接收前台的一个资源
		 * @see 这时value="/{username}"就会到方法参数中找@PathVariable String username，并将路径变量值传给username参数
		 */
		@RequestMapping(value="/{myname}", method=RequestMethod.GET)
		public String show(@PathVariable String myname, Model model){
			model.addAttribute(users.get(myname));
			return "user/show";
		}
		
		/**
		 * 编辑用户信息
		 * @see 访问该方法的路径就应该是"/user/具体的用户名/update"
		 */
		@RequestMapping(value="/{myname}/update", method=RequestMethod.GET)
		public String update(@PathVariable String myname, Model model){
			model.addAttribute(users.get(myname));
			return "user/update";
		}
		@RequestMapping(value="/{myname}/update", method=RequestMethod.POST)
		public String update(User user){
			users.put(user.getUsername(), user);
			return "redirect:/user/list"; //也可以retun "forward:/user/list",此时浏览器地址栏会有不同
		}
		
		/**
		 * 删除用户信息
		 */
		@RequestMapping(value="/{myname}/delete", method=RequestMethod.GET)
		public String delete(@PathVariable String myname){
			users.remove(myname);
			return "redirect:/user/list"; //删除完成后显示当前存在的所有用户信息
		}
		
		@RequestMapping(value="/print",method=RequestMethod.GET)
	    public String getPrintInfo() {
			System.out.println("张起灵 : "+users.get("张起灵").getNickname());
			return null;
	        
	    }
		
		@RequestMapping(value="/printjson",method=RequestMethod.GET)
	    public @ResponseBody User returnJson() {
			//System.out.println("张起灵 : "+users.get("张起灵").getNickname());
			return users.get("张起灵");
	        
	    }
}
