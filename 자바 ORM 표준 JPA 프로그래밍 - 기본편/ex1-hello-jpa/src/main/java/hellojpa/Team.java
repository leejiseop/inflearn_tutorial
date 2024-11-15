package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") // Member 와 연결되어있는 변수명
    // 이거는 조회만 되고 이거로 db 값 변경 못한다고 ???????????????????????
    // 읽기만 한다. 값 넣을때는 참조 안함
    private List<Member> members = new ArrayList<>(); // 널포인터 방지용으로 초기화 해두는것이 관례

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
