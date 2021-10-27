package br.com.edubarbieri.whishlist.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserOutput {
    private String id;
    private String name;
    private String email;
}
