
package hieupt.dtos;

public class UserDTO {
    private String username, password, fullname, role;

    public UserDTO() {
    }

    public UserDTO(String fullname, String role) {
        this.fullname = fullname;
        this.role = role;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
