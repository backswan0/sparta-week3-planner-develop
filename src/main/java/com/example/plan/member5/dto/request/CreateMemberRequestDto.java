package com.example.plan.member5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Getter
public class CreateMemberRequestDto {
    // 속성
    @NotBlank(message = "사용자 이름은 필수입니다.")
    @Length(min = 2, max = 20)
    private final String username;

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            , message = "이메일 형식이 틀렸습니다. 다시 입력해 주세요."
    )
    private final String email;

    @Length(min = 8, max = 20)
    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private final String password;

    /**
     * 생성자
     *
     * @param username : 사용자 이름
     * @param email    : 사용자 이메일
     * @param password : 사용자 비밀번호
     */
    public CreateMemberRequestDto(
            String username
            , String email
            , String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}