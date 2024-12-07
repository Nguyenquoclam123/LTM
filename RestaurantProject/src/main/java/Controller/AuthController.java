package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Model.BEAN.User;
import Model.BO.RoleBO;
import Model.BO.StatusBO;
import Model.BO.UserBO;

/**
 * Servlet implementation class AuthController
 */
@WebServlet("/AuthController")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String phonenumber = request.getParameter("phonenumber");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String action = request.getParameter("action");
		
		UserBO userBO = new UserBO();
		RoleBO roleBO = new RoleBO();
		StatusBO statusBO = new StatusBO();
		
		if(action.equals("login")) {
			if(userBO.checkLogin(username, password)) {
				String name = userBO.getUserByUsername(username).getFullName();
				User user = userBO.getUserByUsername(username);
				request.getSession().setAttribute("listUser", userBO.getAllUser());
				request.getSession().setAttribute("listRole", roleBO.getAllRole());
				request.getSession().setAttribute("listStatus", statusBO.getAllStatus());
				request.getSession().setAttribute("name",name);
				request.getSession().setAttribute("user",user);
				if(user.getRole().getId() == 1) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/WelcomeAdmin.jsp");
					rs.forward(request, response);
				}
				if(user.getRole().getId() == 2) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/WelcomeUser.jsp");
					rs.forward(request, response);
				}
			}
			else {
				request.setAttribute("errorMessage", "Login failed");
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Login.jsp");
				rs.forward(request, response);
			}
		}
		else if(action.equals("signup")) {
			User user = new User();
			user.setFullName(fullName); user.setGender(gender);
			user.setAddress(address); user.setPhonenumber(phonenumber);
			user.setAge(age); user.setPassword(password);
			user.setUsername(username); user.setRole(roleBO.getRoleById(2));
			if(userBO.create(user)) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Register.jsp");
				request.setAttribute("errorMessage", "Sign up successfull !");
				rs.forward(request, response);
			}
			else {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/Regisster.jsp");
				request.setAttribute("errorMessage", "Check your information again! Maybe existed username or empty field.");
				rs.forward(request, response);
			}
		}
		else if(action.equals("logout")) {
			request.getSession().removeAttribute("name");
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/WelcomeUser.jsp");
			rs.forward(request, response);
		}
	}

}
