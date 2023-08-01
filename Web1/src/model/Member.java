package model;

import java.sql.Connection;
import java.util.List;

public class Member {
    private String id;
    private String pw;
    private String name;
    private int point;
    private boolean isAdmin;
    
    public Member(String id, String pw, String name, int point, boolean isAdmin) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.point = point;
        this.isAdmin = isAdmin;
    }

    public Member(Connection connection) {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter 메서드들
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 추가적인 메서드들
    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                // 추가 필드들 정보도 출력...
                '}';
    }

	public int getPoint() {
		return 0;
	}

	public void setPoint(int i) {
		// TODO Auto-generated method stub
		
	}

	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}
}
