package com.lifepill.possystem.controller.secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lifepill/v1/admin")
@PreAuthorize("hasRole('OWNER')")
public class AdminController {

    @GetMapping("/owner")
   // @PreAuthorize("hasAuthority('owner:read')")
    public String TestOwner() {
        return "Secured Endpoint :: GET - Owner controller";
    }

    @PostMapping("/post")
  //  @PreAuthorize("hasAuthority('owner:create')")
    public String TestPostOwner() {
        return "Secured Endpoint :: POST - Owner controller";
    }
}
