package com.fdiba.webeng;


import java.io.IOException;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fdiba.webeng.model.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PERSISTENCE_UNIT_NAME = "WebEngProject";
	private static EntityManagerFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.encodeRedirectURL("home.html");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String passConf = request.getParameter("passConf");
		
		// TODO Auto-generated method stub
//		System.out.println(request.getParameter("userName"));
//		System.out.println(request.getParameter("firstName"));
//		System.out.println(request.getParameter("lastName"));
//		System.out.println(request.getParameter("password"));
//		System.out.println(request.getParameter("passConf"));
		
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		
		createEntity(user);
		
		readEntity();
		response.sendRedirect("home.html");
		
	}
	
	public void createEntity(UserEntity user) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(user);
        em.getTransaction().commit();

        em.close();
	}
	
	public void readEntity() 
	{
		EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select u from UserEntity u");
        List<UserEntity> userList = q.getResultList();
        for (UserEntity user : userList) {
        	System.out.println("-------------------------");
        	System.out.println(user.getUsername());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getPassword());
            
        }
        System.out.println("Size: " + userList.size());

	}

}
