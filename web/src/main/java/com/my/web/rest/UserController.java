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
	//ģ������Դ,�����ʼ����
		public UserController(){
			users.put("������", new User("������", "����ƿ", "02200059", "menyouping@yeah.net"));
			users.put("��Ѱ��", new User("��Ѱ��", "��̽��", "08866659", "lixunhuan@gulong.cn"));
			users.put("�ذ�Ұ", new User("�ذ�Ұ", "�����", "05577759", "tuobaye@manhuang.cc"));
			users.put("�����", new User("�����", "������", "03311159", "sunhouzi@xiyouji.zh"));
		}
		/**
		 * ������û�
		 * @see ����/user/addʱ��GET�����ִ��addUser(Model model)������POST�����ִ��addUser(User user)����
		 */
		/**
		 * ������û�
		 * @see ����/user/addʱ��GET�����ִ��addUser(Model model)������POST�����ִ��addUser(User user)����
		 */
		@RequestMapping(value="/add", method=RequestMethod.GET)
		public String addUser(Model model,HttpServletRequest request,HttpServletResponse response){
			//����Ҫ����ǰ̨һ���ն��󣬷���ᱨ��java.lang.IllegalStateException�쳣
			//�쳣��ϢΪNeither BindingResult nor plain target object for bean name 'user' available as request attribute
			//���Ҵ���ȥ��keyֵҪ��ǰ̨modelAttribute����ֵ��ͬ����model.addAttribute("user", new User());
			//����Ҳ����д���������ַ�ʽ����ʱSpringMVC���Զ��Ѷ�����ת��ΪСдֵ��Ϊkey����User-->user
			model.addAttribute(new User());
			System.out.println("add............");
			return "user/add";
			
		}
		@RequestMapping(value="/add", method=RequestMethod.POST)
		public String addUser(User user){ //��������е�user��Ӧ����add.jsp�е�modelAttribute="user"һ����
			users.put(user.getUsername(), user);
			return "redirect:/user/list";
		}
		
		/**
		 * �г������û���Ϣ
		 */
		@RequestMapping("/list")
		public @ResponseBody String list(Model model){
			model.addAttribute("users", users);
			System.out.println("list............");
			return "user/list";
		}
		
		/**
		 * ��ѯ�û���Ϣ
		 * @see ���ʸ÷�����·����Ӧ����"/user/������û���"
		 * @see ����value="/{username}"��д������Ҫ����ע��һ�£�����һ��·����������ʱ��������ǰ̨��һ����Դ
		 * @see ��ʱvalue="/{username}"�ͻᵽ������������@PathVariable String username������·������ֵ����username����
		 */
		@RequestMapping(value="/{myname}", method=RequestMethod.GET)
		public String show(@PathVariable String myname, Model model){
			model.addAttribute(users.get(myname));
			return "user/show";
		}
		
		/**
		 * �༭�û���Ϣ
		 * @see ���ʸ÷�����·����Ӧ����"/user/������û���/update"
		 */
		@RequestMapping(value="/{myname}/update", method=RequestMethod.GET)
		public String update(@PathVariable String myname, Model model){
			model.addAttribute(users.get(myname));
			return "user/update";
		}
		@RequestMapping(value="/{myname}/update", method=RequestMethod.POST)
		public String update(User user){
			users.put(user.getUsername(), user);
			return "redirect:/user/list"; //Ҳ����retun "forward:/user/list",��ʱ�������ַ�����в�ͬ
		}
		
		/**
		 * ɾ���û���Ϣ
		 */
		@RequestMapping(value="/{myname}/delete", method=RequestMethod.GET)
		public String delete(@PathVariable String myname){
			users.remove(myname);
			return "redirect:/user/list"; //ɾ����ɺ���ʾ��ǰ���ڵ������û���Ϣ
		}
		
		@RequestMapping(value="/print",method=RequestMethod.GET)
	    public String getPrintInfo() {
			System.out.println("������ : "+users.get("������").getNickname());
			return null;
	        
	    }
		
		@RequestMapping(value="/printjson",method=RequestMethod.GET)
	    public @ResponseBody User returnJson() {
			//System.out.println("������ : "+users.get("������").getNickname());
			return users.get("������");
	        
	    }
}
