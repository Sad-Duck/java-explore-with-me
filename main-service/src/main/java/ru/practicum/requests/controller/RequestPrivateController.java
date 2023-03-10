package ru.practicum.requests.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.requests.dto.EventRequestStatusUpdateRequest;
import ru.practicum.requests.service.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/users/{userId}")
@Validated
@RequiredArgsConstructor
public class RequestPrivateController {
    private final RequestService requestService;

    @PostMapping("/requests")
    public ResponseEntity<Object> addRequest(@PathVariable Long userId, @RequestParam Long eventId) {
        return new ResponseEntity<>(requestService.addRequest(eventId, userId), HttpStatus.CREATED);
    }

    @GetMapping("/requests")
    public ResponseEntity<Object> allUserRequests(@PathVariable Long userId) {
        return new ResponseEntity<>(requestService.allUserRequests(userId), HttpStatus.OK);
    }

    @PatchMapping("/requests/{requestId}/cancel")
    public ResponseEntity<Object> cancelRequest(@PathVariable Long userId, @PathVariable Long requestId) {
        return new ResponseEntity<>(requestService.cancelRequest(requestId, userId), HttpStatus.OK);
    }

    @GetMapping("/requests/{eventId}/cancel")
    public ResponseEntity<Object> getEventRequest(@PathVariable Long userId, @PathVariable Long eventId) {
        return new ResponseEntity<>(requestService.getEventRequest(eventId, userId), HttpStatus.OK);
    }

    @PatchMapping("/requests/{eventId}/cancel")
    public ResponseEntity<Object> update(@PathVariable Long userId, @PathVariable Long eventId,
                                         @RequestBody @NotNull @Valid EventRequestStatusUpdateRequest request) {
        return new ResponseEntity<>(requestService.update(eventId, userId, request), HttpStatus.OK);
    }
}
