package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

@Service
public class AvatarService {

    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar addAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public Avatar getAvatar(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }

    public Avatar editAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }

    // Пагинация: получаем аватарки постранично
    public Page<Avatar> getAllAvatars(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return avatarRepository.findAll(pageRequest);
    }
}