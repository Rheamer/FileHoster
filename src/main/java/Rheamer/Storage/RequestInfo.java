package Rheamer.Storage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.servlet.http.HttpServlet;
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
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long requestInfo;

    @Column(nullable=false, length=500)
    private String info;
}
