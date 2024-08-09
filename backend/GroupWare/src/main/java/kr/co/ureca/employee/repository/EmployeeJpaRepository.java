package kr.co.ureca.employee.repository;

import kr.co.ureca.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee,Long> {
}
