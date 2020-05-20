package pl.coderslab.swagger.examples;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserLogin {

    @ApiModelProperty(example = "admin")
    private String username;

    @ApiModelProperty(example = "admin")
    private String password;

}
