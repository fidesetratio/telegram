package com.app.telegram.listener;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface Listener {
	public void onReceive(Update update,TelegramLongPollingBot bot);
}
