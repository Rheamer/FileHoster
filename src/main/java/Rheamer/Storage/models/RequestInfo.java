package Rheamer.Storage.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@Data
@Entity
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
public class RequestInfo {

    public static RequestInfo of(HttpServletRequest r){
        return new RequestInfo(r);
    }

    public RequestInfo(HttpServletRequest req){
        this.info = req.toString();
        this.sourceAddress = req.getRemoteAddr();
        this.requestUri = req.getRequestURI();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long requestInfo;

    @Column(nullable = false)
    private String sourceAddress;

    @Column(nullable = false)
    private String requestUri;

    @Column(nullable=false, length=500)
    private String info;

}
