package bg.softuni.usermodule.models.dto.gson;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ConsumerImportDTO implements Serializable {

    @Expose
    @NotNull
    private String username;

    @Expose
    @NotNull
    private String email;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
