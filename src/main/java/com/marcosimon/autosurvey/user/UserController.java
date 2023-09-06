package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.UserOrgResponseDTO;
import com.marcosimon.autosurvey.models.UserStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome, this endpoint is not secure");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<List<UserOrgResponseDTO>> getAllUsers() {
        List<UserOrgResponseDTO> dtoList = userService.getAllUsers();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "{name}")
    public ResponseEntity<UserOrgResponseDTO> getUser(@PathVariable String name) {

        UserOrgResponseDTO userDto = userService.getUserDtoByName(name);
        if (userDto == null) return ResponseEntity.notFound().build();

        return  ResponseEntity.ok(userDto);
    }

    @PostMapping("/new")
    public ResponseEntity<UserOrgResponseDTO> createUser(@RequestBody UserModel userModel) {
        UserModel user = userService.createUser(userModel);

        if (user == null) return  ResponseEntity.status(409).body(null);

        UserOrgResponseDTO dto = new UserOrgResponseDTO(userModel.getUserId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getRoles(),
                userModel.getStatus());

        return ResponseEntity.status(201).body(dto);
    }

    @PatchMapping(path = "{name}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    ResponseEntity<UserModel> patchUser(@RequestBody UserStatusDTO dto, @PathVariable String name) {
        UserModel user = userService.editStatus(name, dto.status());
        if(user == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.accepted().body(user);
    }
}
