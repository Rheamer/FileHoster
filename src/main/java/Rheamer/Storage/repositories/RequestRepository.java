package Rheamer.Storage.repositories;

import Rheamer.Storage.models.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestInfo, Long> {
    @Query("SELECT r FROM RequestInfo r " +
            "WHERE r.requestUri = ?1")
    public RequestInfo findByRequestUri(String requestUri);

    public List<RequestInfo> findAllByRequestUri(String requestUri);
}
