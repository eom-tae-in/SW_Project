package com.example.sheetmusiclist.dto.sign;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel(value = "회원가입 요청")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해주세요.", required = true, example = "username123")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @ApiModelProperty(value = "패스워드", notes = "패스워드를 입력해주세요.", required = true, example = "password123!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    @NotBlank(message = "패스워드를 입력해주세요. (비밀번호는 최소 8자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.)")
    private String password;

    @ApiModelProperty(value = "이메일", notes = "이메일을 입력해주세요", required = true, example = "member@email.com")
    @Email(message = "유효한 이메일을 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

}
