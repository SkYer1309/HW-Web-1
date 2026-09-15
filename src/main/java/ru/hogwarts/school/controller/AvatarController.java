package ru.hogwarts.school.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public Long createAvatar(@RequestBody Avatar avatar) {
        return avatarService.addAvatar(avatar).getId();
    }

    @GetMapping("/{id}")
    public Avatar getAvatar(@PathVariable Long id) {
        return avatarService.getAvatar(id);
    }

    @GetMapping
    public Page<Avatar> getAllAvatars(
            @RequestParam int page,
            @RequestParam int size) {
        return avatarService.getAllAvatars(page, size);
    }

    @PutMapping
    public Avatar updateAvatar(@RequestBody Avatar avatar) {
        return avatarService.editAvatar(avatar);
    }

    @DeleteMapping("/{id}")
    public void deleteAvatar(@PathVariable Long id) {
        avatarService.deleteAvatar(id);
    }
}