package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.Manager;

public class ManagerDAO {

	public List<Manager> loadAllActiveManagers() {
		
		// Manager sınıfını sizin oluşturdum.
		List<Manager> managers = new ArrayList<Manager>();
		
		// Burada halen aktif olarak yöneticilik yapan tüm çalışanları departman isimleriyle birlikte liste halinde hazırlayınız.
		// İpucu: Bu sorgunun bir benzerini derste anlatmıştım. Hatta "sql_query_samples.sql" dosyası içinde benzeri mevcut.
		LocalTime now= LocalTime.now();
		String query = " select emp.* , dp.dept_name as department_name from employees emp " +
				" LEFT JOIN dept_manager dm ON dm.emp_no = emp.emp_no " +
				" LEFT JOIN departments dp ON dp.dept_no = dm.dept_no " +" where dm.to_date >= " + now;
		String deptName = "";

		ResultSet resultSet = DbSQLQuery.select(query);
		try {
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getLong("emp_no"));
				employee.setName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setGender(resultSet.getString("gender"));
				employee.setBirthDate(resultSet.getDate("birth_date"));
				employee.setHireDate(resultSet.getDate("hire_date"));
				deptName = resultSet.getString("department_name");

				Manager tempManager = new Manager(employee, deptName);
				managers.add(tempManager);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return managers;
	}
	
}
