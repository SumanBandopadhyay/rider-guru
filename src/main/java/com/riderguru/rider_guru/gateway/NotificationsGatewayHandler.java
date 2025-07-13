package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.notification.NotificationDto;
import com.riderguru.rider_guru.notification.NotificationsAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationsGatewayHandler {

    private final NotificationsAPI notificationsAPI;

    public NotificationsGatewayHandler(NotificationsAPI notificationsAPI) {
        this.notificationsAPI = notificationsAPI;
    }

    @GetMapping("/otp/send")
    public ResponseEntity<String> sendOtp(@RequestParam("phone-number") String phoneNumber) {
        return notificationsAPI.sendOtp(phoneNumber);
    }

    @GetMapping("/otp/verify")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam("otp") String otp) {
        return notificationsAPI.verifyOtp(otp);
    }

    @GetMapping("/query")
    public ResponseEntity<List<NotificationDto>> queryNotifications(@RequestParam(required = false) Map<String, String> params) {
        return notificationsAPI.query(params);
    }
}
