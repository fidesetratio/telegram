package com.app.telegram;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.app.services.MemberServices;
import com.app.telegram.listener.Listener;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	public static MemberServices memberServices;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ApiContextInitializer.init();
		 initLog4J();
		 initServices();
		 logger.info("start bot chat..");
		 	TelegramBotsApi botsApi = new TelegramBotsApi();
	        try {
	            botsApi.registerBot(new TelegramPooling("SIAP2UHELPDESKBOT","614327304:AAHk9z6DuMxpY0ubUfoZS-YJjgENAc52QQ0",new Listener() {
					@Override
					public void onReceive(Update update, TelegramLongPollingBot bot) {
						  long chat_id = update.getMessage().getChatId();
						  String message_text = update.getMessage().getText();
						
						  SendMessage message = new SendMessage();
						  logger.info("firstname:"+  update.getMessage().getFrom().getFirstName());
						  if(!isAuthorized(update)){
							  // Create a message object object
						                message.setChatId(chat_id);
						                message.setText(isNotAuthorizedMessage());
						                
						  }else{
							  String command = message_text.toLowerCase();
							  message.setChatId(chat_id);
							  if(command.equals("help")){
								 
								  message.setText(showHelp());
							  }else{
								  if (command.startsWith("member")){
									  String m = processMember(command);
									  message.setText(m);
								  }else{
									  message.setText("Maaf kami tidak menyediakan layanan yang dimaksud");
								  }
							  }
							  
							  
						  }
						 
						  	try {
				                bot.execute(message); // Sending our message object to user
				            } catch (TelegramApiException e) {
				                e.printStackTrace();
				            }
						  
						  logger.info("message text.....:"+message_text);
						
						
						
					}

					
				
				}));
	        
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	};

	private static void initServices(){
		memberServices = new MemberServices();
	};
	private static void initLog4J(){
		PropertyConfigurator.configure("conf/log4j.properties");;
	}
	
	private static String showHelp() {
		// TODO Auto-generated method stub
		return "Please type member update status";
	}
	
	
	private static String isNotAuthorizedMessage(){
			return "Mohon maaf sepertinya anda belum dapat menggunakan layanan ini";
	}
	
	private static Boolean isAuthorized(Update update){
		if(update.getMessage().getFrom().getFirstName().equals("Patar Timotius")){
			return true;
		}
		return false;
	}
	
	private static String processMember(String command) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		String parameter[] = command.split(" ");
		if(parameter.length!=2){
			buffer.append("Mohon pastikan bahwa anda menggunakan format yang benar");
			buffer.append("\n");
			buffer.append("Format: member <noid>");
			buffer.append("\n");
			buffer.append("Contoh : member 202020");
			
			
		}else{
		
			String noid=parameter[1];
			List<Map<String,Object>> m = memberServices.getMemberStatus(noid);
			if(m.size()<=0){
				buffer.append("Maaf  member dengan noid="+noid+" tidak ditemukan");
			}else{
			
			for(Map<String,Object> d:m){
			buffer.append("member id:"+d.get("NOID"));
			buffer.append("\n");
			buffer.append("no va:"+d.get("NO_VA"));
			buffer.append("\n");
			String s = "NOT YET";
			if(d.get("FLAG_PAYMENT") instanceof BigDecimal){
				if(((BigDecimal)d.get("FLAG_PAYMENT")).intValue()==1){
					s = "DONE";
				}

			}
				
			buffer.append("payment:"+s);
			buffer.append("\n");
			s = "NOT YET";
			
			if(d.get("FLAG_SUBMIT") instanceof BigDecimal){
				if(((BigDecimal)d.get("FLAG_SUBMIT")).intValue()==1){
					s = "DONE";
				}

			}
			
			buffer.append("submit spaj:"+s);
			buffer.append("\n");
			s = "NOT YET";
			if(d.get("FLAG_PRODUCTION") instanceof BigDecimal){
				if(((BigDecimal)d.get("FLAG_PRODUCTION")).intValue()==1){
					s = "DONE";
				}

			}
			buffer.append("production:"+s);
			};
			};
		};
		
		return buffer.toString();
	}

}
