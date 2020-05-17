package in.gov.agentregistration.configuration;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import in.gov.agentregistration.constants.CommonConstants;
import in.gov.agentregistration.model.UserAccountModel;
import in.gov.agentregistration.services.AgentRestTemplateServices;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class Util {

	@Value(value = "${URL_CREATE_USER_ACC}")
	static String urlCreateUserAcc;

	String captchaString = "";

	public byte[] getCaptchaImage() {
		try {
			Color backgroundColor = Color.white;
			Color borderColor = Color.black;
			Color textColor = Color.black;
			Color circleColor = new Color(190, 160, 150);
			Font textFont = new Font("Verdana", Font.BOLD, 20);
			int charsToPrint = 6;
			int width = 160;
			int height = 50;
			int circlesToDraw = 25;
			float horizMargin = 10.0f;
			double rotationRange = 0.7;
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
			g.setColor(backgroundColor);
			g.fillRect(0, 0, width, height);
			g.setColor(circleColor);
			for (int i = 0; i < circlesToDraw; i++) {
				int L = (int) (Math.random() * height / 2.0);
				int X = (int) (Math.random() * width - L);
				int Y = (int) (Math.random() * height - L);
				g.draw3DRect(X, Y, L * 2, L * 2, true);
			}
			g.setColor(textColor);
			g.setFont(textFont);
			FontMetrics fontMetrics = g.getFontMetrics();
			int maxAdvance = fontMetrics.getMaxAdvance();
			int fontHeight = fontMetrics.getHeight();

			// i removed 1 and l and i because there are confusing to users...
			// Z, z, and N also get confusing when rotated
			// this should ideally be done for every language...
			// 0, O and o removed because there are confusing to users...
			// i like controlling the characters though because it helps prevent confusion
			String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789";
			char[] chars = elegibleChars.toCharArray();
			float spaceForLetters = -horizMargin * 2 + width;
			float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
			StringBuffer finalString = new StringBuffer();
			for (int i = 0; i < charsToPrint; i++) {
				double randomValue = Math.random();
				int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
				char characterToShow = chars[randomIndex];
				finalString.append(characterToShow);

				// this is a separate canvas used for the character so that
				// we can rotate it independently
				int charWidth = fontMetrics.charWidth(characterToShow);
				int charDim = Math.max(maxAdvance, fontHeight);
				int halfCharDim = (int) (charDim / 2);
				BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);

				Graphics2D charGraphics = charImage.createGraphics();
				charGraphics.translate(halfCharDim, halfCharDim);
				double angle = (Math.random() - 0.5) * rotationRange;
				charGraphics.transform(AffineTransform.getRotateInstance(angle));
				charGraphics.translate(-halfCharDim, -halfCharDim);
				charGraphics.setColor(textColor);
				charGraphics.setFont(textFont);
				int charX = (int) (0.5 * charDim - 0.5 * charWidth);
				charGraphics.drawString("" + characterToShow, charX,
						(int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
				float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
				int y = (int) ((height - charDim) / 2);
				g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
				charGraphics.dispose();
			}
			g.setColor(borderColor);
			g.drawRect(0, 0, width - 1, height - 1);
			g.dispose();
			captchaString = finalString.toString();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception ioe) {
			throw new RuntimeException("Unable to build image", ioe);
		}
	}

	public String getCaptchaString() {
		return captchaString;
	}

	public static String cDateTime() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("YYYY-mm-dd");
		ZonedDateTime zdt = ZonedDateTime.now();
		String zdtString = FOMATTER.format(zdt);
		// Date d=FOMATTER.(zdt);
		System.out.println(zdtString);
		return zdtString;
	}

	public static String getName(String fname, String mname, String lname) {
		StringBuffer b = new StringBuffer();
		if (Optional.ofNullable(fname).isPresent())
			b.append(fname);
		if (Optional.ofNullable(mname).isPresent())
			b.append(" " + mname);

		if (Optional.ofNullable(lname).isPresent())
			b.append(" " + lname);

		return b.toString();
	}

	public static void main(String args[]) {
		try {

			UserAccountModel entity = new UserAccountModel();

			/*
			 * entity.setEmail("test@gmail.com"); entity.setLoginId("45217");// memberShip
			 * number entity.setUserCategory(ReraConstants.EXTERNAL_USER_CATEGORY);
			 * entity.setUserName("Ram name");
			 * entity.setUserType(ReraConstants.ARCHITECT_REG); entity.setProfileId(16L);
			 * entity.setMobile("9638527410"); entity.setUserStatus("ACTIVE");
			 * System.out.println("................." +
			 * UserRestTemplateServices.createAccount(entity).getLoginId());
			 */

			entity.setEmail("sachin.abcd@gmail.com");
			entity.setLoginId("sachin.abcd@gmail.com");// memberShip
			entity.setUserCategory(CommonConstants.EXTERNAL_USER_CATEGORY);
			entity.setUserName("Sachin singh");
			entity.setUserType(CommonConstants.CITIZEN);
			entity.setProfileId(2L);
			entity.setMobile("9097979979");
			entity.setUserStatus("ACTIVE");

			AgentRestTemplateServices.createAccount(entity, urlCreateUserAcc).getLoginId();

			/*
			 * DmsModel dmsModel=new DmsModel(); dmsModel.setFolderId("1");
			 * dmsModel.setDocumentId(212L); dmsModel.setDocumentType("CA_PAN_DOC");
			 * System.out.println("------------"+UserRestTemplateServices.saveDoc(dmsModel).
			 * getDocumentName());
			 */

			// DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at'
			// hh:mm a z");
			DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("YYYY-mm-dd");
			ZonedDateTime zdt = ZonedDateTime.now();
			String zdtString = FOMATTER.format(zdt);
			System.out.println(zdtString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
