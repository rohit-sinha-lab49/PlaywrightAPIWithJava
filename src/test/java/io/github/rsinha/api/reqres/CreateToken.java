package io.github.rsinha.api.reqres;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateToken {

    private String username;
    private String password;
}
