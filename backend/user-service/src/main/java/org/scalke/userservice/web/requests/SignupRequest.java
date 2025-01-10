package org.scalke.userservice.web.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.scalke.userservice.web.constraints.ContactNumberConstraint;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "Lastname is required")
    private String lastname;

    @NotBlank(message = "Firstname is required")
    private String firstname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @ContactNumberConstraint
    private String contactNumber;

    @Override
    public String toString() {
        return "SignupRequest [name=" + lastname+"-"+firstname + ", email=" + email + ", password=" + password + ", contactNumber=" + contactNumber + "]";
    }
}
