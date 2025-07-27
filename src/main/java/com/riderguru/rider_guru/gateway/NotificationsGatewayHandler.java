package com.riderguru.rider_guru.gateway;

import com.riderguru.rider_guru.notification.NotificationDto;
import com.riderguru.rider_guru.notification.NotificationsAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Gateway controller for notification-related endpoints.
 *
 * <p>This handler delegates to the {@link NotificationsAPI} but also adds
 * application-level logging so it’s easy to trace the flow of incoming
 * requests.  Each endpoint logs the incoming parameters and the fact that
 * the call is being forwarded to the underlying API.</p>
 */
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationsGatewayHandler {

    private static final Logger logger = LoggerFactory.getLogger(NotificationsGatewayHandler.class);

    private final NotificationsAPI notificationsAPI;

    public NotificationsGatewayHandler(NotificationsAPI notificationsAPI) {
        this.notificationsAPI = notificationsAPI;
    }

    /**
     * Endpoint for sending a one-time password (OTP) to a phone number.
     *
     * @param phoneNumber The phone number to which the OTP should be sent.
     * @return A {@link ResponseEntity} wrapping the downstream response.
     */
    @GetMapping("/otp/send")
    public ResponseEntity<String> sendOtp(@RequestParam("phone-number") String phoneNumber) {
        logger.info("Received request to send OTP for phone number: {}", phoneNumber);
        ResponseEntity<String> response = notificationsAPI.sendOtp(phoneNumber);
        logger.info("Completed sendOtp call for phone number: {} with status {}", phoneNumber, response.getStatusCode());
        return response;
    }

    /**
     * Endpoint for verifying a previously issued OTP.
     *
     * @param otp The OTP value supplied by the client.
     * @return A {@link ResponseEntity} wrapping the downstream response.
     */
    @GetMapping("/otp/verify")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam("otp") String otp) {
        logger.info("Received request to verify OTP: {}", otp);
        ResponseEntity<Boolean> response = notificationsAPI.verifyOtp(otp);
        logger.info("Completed verifyOtp call for OTP: {} with status {}", otp, response.getStatusCode());
        return response;
    }

    /**
     * Endpoint for querying notifications.
     *
     * @param params Optional query parameters used to filter notifications.
     * @return A list of matching {@link NotificationDto} objects wrapped in a {@link ResponseEntity}.
     */
    @GetMapping("/query")
    public ResponseEntity<List<NotificationDto>> queryNotifications(@RequestParam(required = false) Map<String, String> params) {
        logger.info("Received request to query notifications with params: {}", params);
        ResponseEntity<List<NotificationDto>> response = notificationsAPI.query(params);
        logger.info("Completed queryNotifications call with status {} and result count {}", response.getStatusCode(),
                response.getBody() != null ? response.getBody().size() : 0);
        return response;
    }
}
