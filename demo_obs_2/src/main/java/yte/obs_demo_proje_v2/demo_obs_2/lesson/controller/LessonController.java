package yte.obs_demo_proje_v2.demo_obs_2.lesson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.MessageResponse;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.controller.request.AddLessonRequest;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.controller.request.UpdateLessonRequest;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.controller.response.LessonQueryModel;
import yte.obs_demo_proje_v2.demo_obs_2.lesson.service.LessonService;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/Lessons")
@RequiredArgsConstructor
@Validated
public class LessonController {
    private final LessonService LessonService;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse addLesson(@Valid @RequestBody AddLessonRequest addLessonRequest) {
        return LessonService.addLesson(addLessonRequest.toDomainEntity());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','ACADEMICIAN', 'ASSISTANT')")
    public List<LessonQueryModel> getAllLessons() {
        return LessonService.getAllLessons()
                .stream()
                .map(Lesson -> new LessonQueryModel(Lesson))
                .toList();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','ACADEMICIAN', 'ASSISTANT')")
    public LessonQueryModel getById(@NotNull @PathVariable Long id) {
        return new LessonQueryModel(LessonService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse deleteLessonById(@PathVariable @NotNull Long id) {
        return LessonService.deleteLessonById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateLesson(@Valid @RequestBody UpdateLessonRequest request, @PathVariable Long id) {
        return LessonService.updateLesson(id, request.toDomainEntity());
    }
}
