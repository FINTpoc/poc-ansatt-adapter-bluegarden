package no.fk.fint.adapter.bluegarden;

import no.fk.Ansatt;
import no.skate.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Bluegarden {
    private final Map<Identifikator, Ansatt> employees = new HashMap<>();

    @PostConstruct
    public void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
        Date fodselsdato = null;
        try {
            fodselsdato = dateFormat.parse("27/12-1971");
        } catch (ParseException e) {
            throw new RuntimeException();
        }

        Ansatt ole = new Ansatt(new Personnavn("Ole", "Olsen"), Kjonn.MANN, Landkode.NO, fodselsdato, Sivilstand.ENKE_ELLER_ENKEMANN);
        ole.addIdentifikator(new Identifikator("fodselsnummer", "12345678901"));
        ole.setKontaktinformasjon(new Kontaktinformasjon("ole.olsen@gmail.com", "90909090", "90909090"));
        ole.setAvdeling("IKT avdelingen");
        employees.put(ole.getIdentifikatorer().get(0), ole);

        Ansatt mari = new Ansatt(new Personnavn("Mari", "Hansen"), Kjonn.KVINNE, Landkode.NO, fodselsdato, Sivilstand.GIFT);
        mari.addIdentifikator(new Identifikator("ansattnummer", "123"));
        mari.setAvdeling("Personalavdelingen");
        employees.put(mari.getIdentifikatorer().get(0), mari);

        Ansatt trine = new Ansatt(new Personnavn("Trine", "Johansen"), Kjonn.KVINNE, Landkode.SE, fodselsdato, Sivilstand.GJENLEVENDE_PARTNER);
        trine.addIdentifikator(new Identifikator("fodselsnummer", "23456789012"));
        trine.setAvdeling("Skeisvang videregående skole");
        employees.put(trine.getIdentifikatorer().get(0), trine);

        Ansatt line = new Ansatt(new Personnavn("Line", "Svendsen"), Kjonn.KVINNE, Landkode.NO, fodselsdato, Sivilstand.SKILT);
        line.addIdentifikator(new Identifikator("fodselsnummer", "34567890123"));
        line.setAvdeling("Seksjon for kvalitet, analyse og dimensjonering");
        employees.put(line.getIdentifikatorer().get(0), line);

        Ansatt pal = new Ansatt(new Personnavn("Pål", "Persen"), Kjonn.MANN, Landkode.NO, fodselsdato, Sivilstand.GIFT);
        pal.setAvdeling("Rådmannens stab");
        pal.setKontaktinformasjon(new Kontaktinformasjon("pal.persen@online.no", "123456789", "123456789"));
        pal.addIdentifikator(new Identifikator("fodselsnummer", "45678901234"));
        employees.put(pal.getIdentifikatorer().get(0), pal);

    }

    public List<Ansatt> getEmployees() {
        return new ArrayList<>(employees.values());
    }
}
