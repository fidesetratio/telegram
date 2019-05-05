package com.app.telegram;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.app.telegram.listener.Listener;

public class TelegramPooling extends TelegramLongPollingBot {
	
	private String username;
	private String token;
	private static Logger logger = Logger.getLogger(TelegramPooling.class);
	private Listener listener;
	
	public TelegramPooling(){
		super();
	}
	public TelegramPooling(String username,String token,Listener listener){
		super();
		this.username =username;
		this.token = token;
		this.listener = listener;
		logger.info("username:"+this.username);
		logger.info("token:"+this.token);

	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
	/*	return "SinarmasBotPatar";*/
		return this.username;
	}

	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		 if (update.hasMessage() && update.getMessage().hasText()) {
			 listener.onReceive(update, this);
			  /* String message_text = update.getMessage().getText();
	
		        long chat_id = update.getMessage().getChatId();
		        String firstname=update.getMessage().getFrom().getFirstName();

		        
		        System.out.println("chat id"+firstname);
		        SendMessage message = new SendMessage() // Create a message object object
		                .setChatId(chat_id)
		                .setText(firstname+" "+message_text);
		            try {
		                execute(message); // Sending our message object to user
		            } catch (TelegramApiException e) {
		                e.printStackTrace();
		            }*/
		 }

	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		/*return "590721958:AAE19Qao1N6pgAdr0bc6v4is2zBNj73OLgY";*/
		
		return this.token;
	}

}
