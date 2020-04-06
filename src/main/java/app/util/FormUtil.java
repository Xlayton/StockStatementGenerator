package app.util;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import app.model.Account;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class FormUtil {

	public static Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

	public static void createFormsFromAccounts(ArrayList<Account> accounts, String saveDirectory) throws IOException, URISyntaxException, TemplateException {
		cfg.setDirectoryForTemplateLoading(new File(FormUtil.class.getClassLoader().getResource("templates/").getFile()));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		cfg.setFallbackOnNullLoopVariable(false);
		for (Account account : accounts) {
			String htmlContent = createHTMLString(account);
			try(OutputStream os = new FileOutputStream(saveDirectory + "\\" + account.getAccountNumber() + ".pdf")) {
				PdfRendererBuilder builder = new PdfRendererBuilder();
				builder.useFastMode();
				builder.withHtmlContent(htmlContent, "C:\\");
				builder.toStream(os);
				builder.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String createHTMLString(Account account) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Map<String, Object> stuff = new HashMap<String, Object>();
		stuff.put("user", account);
		Writer out = new CharArrayWriter();
		Template template = cfg.getTemplate("form_template.ftlh");
		template.process(stuff, out);
		String htmlContent = out.toString();
		out.close();
		return htmlContent;
	}
}
