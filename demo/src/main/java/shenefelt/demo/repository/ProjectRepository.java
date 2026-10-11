package shenefelt.demo.repository;

import shenefelt.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCustomerId(Long customerId);
    List<Project> findByUserId(Long userId);
    List<Project> findByCustomerIdAndUserId(Long customerId, Long userId);
}