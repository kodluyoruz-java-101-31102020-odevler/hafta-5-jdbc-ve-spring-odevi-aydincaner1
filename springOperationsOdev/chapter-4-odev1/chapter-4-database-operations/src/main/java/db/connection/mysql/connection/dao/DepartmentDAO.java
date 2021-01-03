package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Department;


public class DepartmentDAO {

	private static final Logger logger = Logger.getLogger(DepartmentDAO.class);

	
	public List<Department> getAll() {
		
		List<Department> departments = new ArrayList<Department>();
		
		// Tüm departman listesini çeken SQL komutunu aşağıdaki satıra yazınız.
		ResultSet resultSet = DbSQLQuery.select("SELECT * FROM departments");
		
		try {
			
			// ResultSet içinde veritabanından gelen department kayıtları var.
			if (resultSet == null) {
				return departments;
			}
			
			
			while (resultSet.next()) {
				// ResultSet üzerinde satır satır ilerleyerek bir Department listesi oluştur.
				String deptno = resultSet.getString("dept_no");
				String deptname =  resultSet.getString("dept_name");
				
				// List<Department> departments bu listeye elemanları ekleyeceksiniz.
				Department department = new Department(deptno, deptname);
				departments.add(department);
			}
			// Kodlar ... :)
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return departments;
	}
	
}
