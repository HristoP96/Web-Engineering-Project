

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdiba.webeng.model.FacultyEntity;

/**
 * Servlet implementation class UniServlet
 */
@WebServlet("/UniServlet")
public class UniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PERSISTENCE_UNIT_NAME = "Web_Engineering_Project";
	private static EntityManagerFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UniServlet() {
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
		// TODO Auto-generated method stub
		String university = request.getParameter("university");
		String faculty = request.getParameter("facultyName");
		
		FacultyEntity fe = new FacultyEntity();
		fe.setFaculty(faculty);
		fe.setUniversity(university);
		
		createEntity(fe);
		
		readEntity();
		response.sendRedirect("home.html");

	}
	
	public void createEntity(FacultyEntity fe) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(fe);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	public void readEntity() 
	{
		EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select f from FacultyEntity f");
        List<FacultyEntity> facultyList = q.getResultList();
        for (FacultyEntity faculty : facultyList) {
        	System.out.println("-------------------------");
        	System.out.println(faculty.getFaculty());
            System.out.println(faculty.getUniversity());
            
            
        }
        System.out.println("Size: " + facultyList.size());

	}
	
	

}
