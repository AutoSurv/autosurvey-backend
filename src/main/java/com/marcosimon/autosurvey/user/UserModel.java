package com.marcosimon.autosurvey.user;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserModel {

    @MongoId(value = FieldType.OBJECT_ID)
    private String userId;
    private String username;
    private String password;
    private String email;
    private String roles;
    private String status;
    private String orgId;
    private List<String> surveysIds;

    public UserModel(String userId, String username, String password, String email, String roles, String status, String orgId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.status = status;
        this.orgId = orgId;
        this.surveysIds = new ArrayList<>();
    }
}
