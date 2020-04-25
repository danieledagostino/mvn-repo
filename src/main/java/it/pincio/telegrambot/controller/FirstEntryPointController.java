package it.pincio.telegrambot.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class FirstEntryPointController extends TelegramLongPollingBot {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstEntryPointController.class);
	
	@Value("${PINCIO_BOT_TOKEN}")
	private String token;

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(update.getMessage().getChatId())
	                .setText("hai inviato questo messaggio"+update.getMessage().getText());
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
		
	}

	@Override
	public String getBotUsername() {
		return "NewPincioBot";
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@PostConstruct
	public void start() {
		logger.info("token: {}", token);
	}
}
