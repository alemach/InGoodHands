package ale.mach.charity.service;

import ale.mach.charity.pojo.MessageDTO;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);

	void sendSimpleTemplateMessage(MessageDTO messageDTO);
}
