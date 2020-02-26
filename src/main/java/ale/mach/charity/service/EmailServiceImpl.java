package ale.mach.charity.service;

import ale.mach.charity.pojo.MessageDTO;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	private final String TO = "w.dobre.rece@gazeta.pl";
	private final String SUBJECT = "Wiadomość formularza kontaktowego";
	private final MailSender mailSender;
	private final SimpleMailMessage template;

	public EmailServiceImpl(MailSender mailSender, SimpleMailMessage template) {
		this.mailSender = mailSender;
		this.template = template;
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("w.dobre.rece@gazeta.pl");
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		mailSender.send(mailMessage);
	}

	public void sendSimpleTemplateMessage(MessageDTO messageDTO) {
		template.setFrom("w.dobre.rece@gazeta.pl");
		template.setTo(TO);
		template.setSubject(SUBJECT);
		template.setText(String.format(template.getText(), messageDTO.getName(), messageDTO.getSurname(), messageDTO.getMessage()));
		mailSender.send(template);
	}
}
