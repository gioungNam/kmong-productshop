package data;

public class User {
    private String id;
    private String name;
    // 추가적으로 필요한 필드들을 선언할 수 있습니다.

    // 생성자
    public User() {
    }

    // ID에 대한 getter 및 setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // 이름에 대한 getter 및 setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
