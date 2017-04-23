package pl.sternik.mg.services;

import java.util.List;

import pl.sternik.mg.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}