package gutenbergproject4cbu.demo.DTO;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDTO {

    @NotNull
    @Size(min = 2, max = 25, message = "min 2 characters max 25 characters should be ")
    @NotEmpty(message = "not okay name")
    private String username;

    @NotNull
    @Size(min = 2, max = 25, message = "min 2 characters max 25 characters should be ")
    @NotEmpty(message = "not okay name")
    private String userLastname;

    @NotNull
    @NotEmpty(message = "not okay")
    @Email(regexp = "^(.+)@(.+)$", message = "not okay email")
    private String email;

    @NotEmpty
    @Size(min = 6, max = 20, message = "min 6 characters max 20 characters should be")
    private String password;

    @NotEmpty
    @Size(min = 6, max = 20, message = "min 6 characters max 20 characters should be")
    private String repeatPassword;

    @AssertTrue(message = "not match")
    public boolean isPasswordMatch() {
        return password != null && password.equals(repeatPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
  
}
