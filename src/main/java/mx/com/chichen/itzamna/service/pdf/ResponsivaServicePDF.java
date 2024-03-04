package mx.com.chichen.itzamna.service.pdf;

import mx.com.chichen.itzamna.model.dto.ResponsivaDTO;
import mx.com.chichen.itzamna.repositories.IResponsivaRepository;
import mx.com.chichen.itzamna.util.MapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@Service
public class ResponsivaServicePDF {

    private static final String PDF_RESOURCES = "/pdf-resources/";

    @Autowired
    private SpringTemplateEngine sTE;

    @Autowired
    private IResponsivaRepository iResponsiva;

    @Autowired
    private MapperServiceImpl modelMapper;

    public File generateResponsivaPDF(Long idResponsiva) throws Exception {
        Context context = getContextPlaces(idResponsiva);
        String html = loadAndFillTemplate(context);
        String xhtml = convertToXhtml(html);
        return renderPlacesListPdf(xhtml,idResponsiva);
    }

    private String convertToXhtml(String html) {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentAttributes(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setQuiet(true);
        tidy.setTidyMark(true);

        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()),null);
        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM,out);
        return out.toString();
    }

    private File renderPlacesListPdf(String xhtml,long idResponsiva) throws  Exception{
        ResponsivaDTO responsivaBuscar = modelMapper.mapear(iResponsiva.findById(idResponsiva).orElseThrow(),ResponsivaDTO.class);
        File file = File.createTempFile("responsiva_"+responsivaBuscar.getPacienteModel().getNombrePaciente(),".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f /3f,20);
        renderer.setDocumentFromString(xhtml,new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();;
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }
    private String loadAndFillTemplate(Context context) {
        return sTE.process("responsiva",context);
    }

    private Context getContextPlaces(Long idResponsiva) {
        ResponsivaDTO responsivaBuscar = modelMapper.mapear(iResponsiva.findById(idResponsiva).orElseThrow(),ResponsivaDTO.class);
        Context context = new Context();
        context.setVariable("responsiva",responsivaBuscar);
        return context;
    }
}
