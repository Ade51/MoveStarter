package com.example.MovieStarter.services;

/*import at.favre.lib.crypto.bcrypt.BCrypt;*/
import org.springframework.security.crypto.bcrypt.BCrypt;
import com.example.MovieStarter.DTO.LoginUser;
import com.example.MovieStarter.DTO.SignUpUser;
import com.example.MovieStarter.Errors.ErrorCodes;
import com.example.MovieStarter.Errors.ErrorMessage;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.Responses.ServiceResponseT;
import com.example.MovieStarter.entities.User;
import com.example.MovieStarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

    public ServiceResponse signUp(SignUpUser user) {
        List<User> existing_users = userRepository.findAllByEmail(user.getEmail());

        if (existing_users.isEmpty()) {
            User new_user = new User();
            new_user.setEmail(user.getEmail());
            new_user.setIs_admin(false);
            new_user.setFirstName(user.getFirstName());
            new_user.setLastName(user.getLastName());
            new_user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(new_user);
            return ServiceResponse.forSuccess();

        } else {
            return ServiceResponse.fromError(new ErrorMessage(HttpStatus.CONFLICT, "The user already exists!", ErrorCodes.UserAlreadyExists));
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public ServiceResponseT<LoginUser> correctUser(LoginUser loginUser) {
        User existing_user = userRepository.findByEmail(loginUser.getEmail());

        if (existing_user != null) {
            if ((BCrypt.checkpw(loginUser.getPassword(), existing_user.getPassword()))) {
                return ServiceResponseT.forSuccess(loginUser);
            }
            else {
                return ServiceResponseT.createError((new ErrorMessage(HttpStatus.BAD_REQUEST, "Wrong password!", ErrorCodes.WrongPassword)));
            }
        }
        return ServiceResponseT.createError((new ErrorMessage(HttpStatus.NOT_FOUND, "User not found!", ErrorCodes.EntityNotFound)));
    }
}
