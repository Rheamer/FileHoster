package Rheamer.Storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

@Repository
public interface RequestRepository extends JpaRepository<HttpServletRequest, Long> {

}
